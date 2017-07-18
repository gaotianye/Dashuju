package dashuju.celloud.hadoop.demo2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class OssCleanTool extends Configured implements Tool {

	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://master:9000");
		
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		
		if (otherArgs.length < 2) {
			System.err.println("Usage: demo OssCleanTool <in> <out>");
			System.exit(2);
		}
		
		Job job = Job.getInstance(conf, OssCleanTool.class.getSimpleName());
		//指定jar包位置
		job.setJarByClass(OssCleanTool.class);
		//指定mapper
		job.setMapperClass(OssCleanMapper.class);
		
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(OssLogWritable.class);
		
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
			int res = ToolRunner.run(new OssCleanTool(), args);
			System.exit(res);
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
}
