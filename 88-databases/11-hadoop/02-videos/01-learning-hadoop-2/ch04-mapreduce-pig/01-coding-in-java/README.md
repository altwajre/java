# Coding "Word Count" in MapReduce Java

```
[cloudera@quickstart ~]$ which java
/usr/java/jdk1.7.0_67-cloudera/bin/java
```

**Eclipse**

- `File`, `New`, `Java Project`, Project Name: `WordCount`, `Finish`

- right click on the project, `Properties`

- `Java Build Path`, `Libraries`, `Add External JARs...`, `Search`, select all jars at `/usr/lib/hadoop`, `OK`

- select all jars at `/usr/lib/hadoop/client`, `OK`, `OK`

- `New`, `Class`, Name: `WordCount`, `Finish`

- copy following code to the WordCount.java and save it

https://github.com/randalscottking/LearningHadoop2/blob/master/WordCount

```
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.util.GenericOptionsParser;

public class WordCount extends Configured implements Tool{

 public static class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line);
        while (tokenizer.hasMoreTokens()) {
            word.set(tokenizer.nextToken());
            context.write(word, one);
        }
    }

  public void run (Context context) throws IOException, InterruptedException {
        setup(context);
        while (context.nextKeyValue()) {
              map(context.getCurrentKey(), context.getCurrentValue(), context);
            }
        cleanup(context);
  }
 }

 public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {

    public void reduce(Text key, Iterable<IntWritable> values, Context context) 
      throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        context.write(key, new IntWritable(sum));
    }
 }

public int run(String[] args) throws Exception {

    Job job = Job.getInstance(new Configuration());

    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);

    job.setMapperClass(Map.class);
    job.setReducerClass(Reduce.class);

    job.setInputFormatClass(TextInputFormat.class);
    job.setOutputFormatClass(TextOutputFormat.class);

    FileInputFormat.setInputPaths(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    job.setJarByClass(WordCount.class);

    job.submit();
    return 0;
    }

 public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
    ToolRunner.run(new WordCount(), otherArgs);
 }
}
```

- right the project, `Export...`

- on the Export dialog, expand `Java`, select `JAR file`

- on the JAR Export dialog, JAR file: `/home/cloudera/WordCount.jar`, `Finish`

- `$ hadoop jar /home/cloudera/WordCount.jar WordCount Names.txt output`

*cloudera*

- `Job Browser`

- `File Browser` - /user/cloudera/output/part-r-00000

```
Aaron	4
Laura	1
Meredith	3
Sarah	3
```
