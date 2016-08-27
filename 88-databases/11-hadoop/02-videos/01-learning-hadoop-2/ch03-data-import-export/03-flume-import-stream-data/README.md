# Using Flume to Import Streaming Data

```
[cloudera@quickstart ~]$ more /etc/flume-ng/conf/spoolingToHDFS.properties
#This properties file will create an Agent that will move files from a local spooling directory into HDFS
#Create the Source, Channel, and Sink
A1.sources=S1
A1.sinks=H1
A1.channels=C1

#Bind the Source and Sink to the Channel
A1.sources.S1.channels=C1
A1.sinks.H1.channel=C1

#Specify the Source type and directory
A1.sources.S1.type=spooldir
A1.sources.S1.spoolDir=/tmp/spooldir

#Specify the Sink type, directory, and parameters
A1.sinks.H1.type=HDFS
A1.sinks.H1.hdfs.path=/tmp/flume-ng
A1.sinks.H1.hdfs.filePrefix=events
A1.sinks.H1.hdfs.fileSuffix=.log
A1.sinks.H1.hdfs.inUsePrefix=_
A1.sinks.H1.hdfs.fileType=DataStream

#Specify the Channel type (Memory vs File)
A1.channels.C1.type=file
```

- on terminal-1

```
[cloudera@quickstart ~]$ flume-ng agent \
> --conf-file /etc/flume-ng/conf/spoolingToHDFS.properties \
> --name A1
...
16/07/22 21:47:10 INFO instrumentation.MonitoredCounterGroup: Monitored counter group for type: SINK, name: H1: Successfully registered new MBean.
16/07/22 21:47:10 INFO instrumentation.MonitoredCounterGroup: Component type: SINK, name: H1 started
16/07/22 21:47:10 INFO instrumentation.MonitoredCounterGroup: Monitored counter group for type: SOURCE, name: S1: Successfully registered new MBean.
16/07/22 21:47:10 INFO instrumentation.MonitoredCounterGroup: Component type: SOURCE, name: S1 started
```

- on terminal-2

```
[cloudera@quickstart ~]$ hdfs dfs -ls /tmp/flume-ng
[cloudera@quickstart ~]$ sudo /tmp/spooldir/text1.txt
sudo: /tmp/spooldir/text1.txt: command not found
[cloudera@quickstart ~]$ sudo vi /tmp/spooldir/text1.txt
:qw!
```

- back to terminal-1 - following are appended

```
16/07/22 21:49:28 INFO avro.ReliableSpoolingFileEventReader: Preparing to move file /tmp/spooldir/text1.txt to /tmp/spooldir/text1.txt.COMPLETED
16/07/22 21:49:30 INFO hdfs.HDFSDataStream: Serializer = TEXT, UseRawLocalFileSystem = false
16/07/22 21:49:31 INFO hdfs.BucketWriter: Creating /tmp/flume-ng/_events.1469249370975.log.tmp
16/07/22 21:49:40 INFO file.EventQueueBackingStoreFile: Start checkpoint for /home/cloudera/.flume/file-channel/checkpoint/checkpoint, elements to sync = 1
16/07/22 21:49:40 INFO file.EventQueueBackingStoreFile: Updating checkpoint metadata: logWriteOrderID: 1469249230808, queueSize: 0, queueHead: 0
16/07/22 21:49:40 INFO file.Log: Updated checkpoint for file: /home/cloudera/.flume/file-channel/data/log-1 position: 168 logWriteOrderID: 1469249230808
16/07/22 21:50:03 INFO hdfs.BucketWriter: Closing /tmp/flume-ng/_events.1469249370975.log.tmp
16/07/22 21:50:03 INFO hdfs.BucketWriter: Renaming /tmp/flume-ng/_events.1469249370975.log.tmp to /tmp/flume-ng/events.1469249370975.log
16/07/22 21:50:03 INFO hdfs.HDFSEventSink: Writer callback called.
```

- back to terminal-2 - a .log it created at /tmp/flume-ng/

```
[cloudera@quickstart ~]$ hdfs dfs -ls /tmp/flume-ng
Found 1 items
-rw-r--r--   1 cloudera supergroup         12 2016-07-22 21:50 /tmp/flume-ng/events.1469249370975.log
```
