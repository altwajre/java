# Using while and until

## get ip address

```
[osboxes@osboxes ~]$ /sbin/ifconfig
eno16777736: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        ether 00:0c:29:5e:ab:04  txqueuelen 1000  (Ethernet)
        RX packets 25472  bytes 4291083 (4.0 MiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 0  bytes 0 (0.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

lo: flags=73<UP,LOOPBACK,RUNNING>  mtu 65536
        inet 127.0.0.1  netmask 255.0.0.0
        inet6 ::1  prefixlen 128  scopeid 0x10<host>
        loop  txqueuelen 0  (Local Loopback)
        RX packets 40  bytes 2856 (2.7 KiB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 40  bytes 2856 (2.7 KiB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

virbr0: flags=4099<UP,BROADCAST,MULTICAST>  mtu 1500
        inet 192.168.122.1  netmask 255.255.255.0  broadcast 192.168.122.255 <- use inet 192.168.122.1 as ip address
        ether 52:54:00:5d:15:96  txqueuelen 0  (Ethernet)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 0  bytes 0 (0.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
        
$ ssh root@192.168.122.1 <- ssh root to the machine
The authenticity of host '192.168.122.1 (192.168.122.1)' can't be established.
ECDSA key fingerprint is 16:ea:9e:ef:9b:eb:29:d7:49:f2:d6:04:c6:58:f1:92.
Are you sure you want to continue connecting (yes/no)? yes
Warning: Permanently added '192.168.122.1' (ECDSA) to the list of known hosts.
root@192.168.122.1's password:  <- enter password
Last login: Wed Nov 30 16:45:41 2016
[root@osboxes ~]#

[root@osboxes ~]# who <- see users osboxes and root
osboxes  :0           2016-11-24 06:01 (:0)
osboxes  pts/0        2016-11-26 05:36 (:0)
osboxes  pts/1        2016-11-27 07:08 (:0)
root     pts/2        2016-12-04 08:31 (192.168.122.1)
```
