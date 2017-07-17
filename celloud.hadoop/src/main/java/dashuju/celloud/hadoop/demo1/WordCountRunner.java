package dashuju.celloud.hadoop.demo1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCountRunner extends Configured implements Tool{
	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://master:9000");
		
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		
		if (otherArgs.length < 2) {
			System.err.println("Usage: demo wordcount <in> <out>");
			System.exit(2);
		}
		
		Job job = Job.getInstance(conf, WordCountRunner.class.getSimpleName());
		//指定jar包位置
		job.setJarByClass(WordCountRunner.class);
		//指定mapper
		job.setMapperClass(WordCountMapper.class);
		//指定reducer
		job.setReducerClass(WordCountReducer.class);
		job.setInputFormatClass(SequenceFileInputFormat.class);
		
		//指定mapper的输入：用默认的TextInputFormat
		/*
		 * 执行mapper输出：setMapOutputKeyClass和setMapOutputValueClass
		 * 如果mapper的输出和reducer的输出一致，那么只需要提供reducer的输出即可。
		 * 即，setOutputKeyClass和setOutputValueClass
		 */
		//reducer输入：mapper的输出
		//reducer输出：
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		//首先判断input目录是否已经存在，如果存在，先删除
		boolean exit = FileSystem.get(conf).exists(new Path(otherArgs[1]));
		if(exit){
			FileSystem.get(conf).delete(new Path(otherArgs[1]), true);
			System.out.println("delete:"+otherArgs[1]);
		}
		
		/**
		 * 指定input位置：
		 * 如果有多个input，可以for (int i = 0; i < otherArgs.length - 1; ++i) 
		 */
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		//指定output位置
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		//提交到yarn，一直等程序结束运行
        return job.waitForCompletion(true) ? 0 : 1;
	}
	public static void main(String[] args) {
		try {
			int res = ToolRunner.run(new WordCountRunner(), args);
			System.exit(res);
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}

}
