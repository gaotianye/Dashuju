package dashuju.celloud.hadoop.demo3;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * MapReduce实现去重 NullWritable的使用
 * 参考：http://www.makaidong.com/%E5%8D%9A%E5%AE%A2%E5%9B%AD%E6%8E%92%E8%A1%8C/18828.shtml
 * 
 * 思路：在reducer端聚合，而仅仅是为了去重，所以不需要value输出信息。
 * @author Administrator
 *
 */
public class NullWritableDemo extends Configured implements Tool  {

	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://master:9000");
		
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		
		if (otherArgs.length < 2) {
			System.err.println("Usage: demo NullWritableDemo <in> <output>");
			System.exit(2);
		}
		
		Job job = Job.getInstance(conf, NullWritableDemo.class.getSimpleName());
		//指定jar包位置
		job.setJarByClass(NullWritableDemo.class);
		//指定mapper
		job.setMapperClass(NullValueMapper.class);
		//指定reducer
		job.setReducerClass(NullValueReducer.class);
		
		//reducer输出：
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		
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
			int res = ToolRunner.run(new NullWritableDemo(), args);
			System.exit(res);
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
}
class NullValueMapper extends Mapper<LongWritable, Text, Text, NullWritable>{
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		context.write(value, NullWritable.get());
	}
}
class NullValueReducer extends Reducer<Text, NullWritable, Text, NullWritable>{
	@Override
	protected void reduce(Text key, Iterable<NullWritable> values,Context context) 
			throws IOException, InterruptedException {
		context.write(key, NullWritable.get());
	}
}
