package dashuju.celloud.hadoop.demo1;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
	private LongWritable sum;

	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		System.out.println("reducer set up.........");
		sum = new LongWritable();
	}
	
	@Override
	protected void reduce(Text word, Iterable<LongWritable> values,Context context) throws IOException, InterruptedException {
		long count = 0;
		for (LongWritable value : values) {
			count +=value.get();
		}
		sum.set(count);
		System.out.println(String.format("reducer的输出是<key,value>=<%s,%d>", word,count));
		context.write(word,sum);
	}
	
	@Override
	protected void cleanup(Context context)
			throws IOException, InterruptedException {
		System.out.println("reducer clean up.........");
	}
}
