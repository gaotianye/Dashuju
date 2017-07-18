package dashuju.celloud.hadoop.demo1;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * input:
 * hello	you
 * 
 * output:
 * hello	1
 * you	1
 * @author Administrator
 *
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
	private Text word ;
	private LongWritable one ;
	
	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		System.out.println("mapper set up........");
		word = new Text();
		one = new LongWritable(1);
	}
	
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		StringTokenizer words = new StringTokenizer(line);
		while(words.hasMoreTokens()){
			word.set(words.nextToken());
			System.out.println(String.format("mapper输出<key,value>=<%s,%d>", word,1));
			context.write(word, one);
		}
	}
	
	@Override
	protected void cleanup(Context context)
			throws IOException, InterruptedException {
		System.out.println("mapper clean up........");
	}
}
