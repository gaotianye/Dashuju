package dashuju.celloud.hadoop.demo1;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * MRUnit测试
 * 参考：http://blog.csdn.net/sunnyyoona/article/details/53523652
 * @author Administrator
 *
 */
public class WCTest {
    private MapDriver mapDriver;
    private ReduceDriver reduceDriver;
    private MapReduceDriver mapReduceDriver;
    
    @Before
    public void setUp(){
    	WordCountMapper mapper = new WordCountMapper();
        mapDriver = MapDriver.newMapDriver(mapper);
        
        WordCountReducer reducer = new WordCountReducer();
        reduceDriver = ReduceDriver.newReduceDriver(reducer);
        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }
    
    @Test
    public void testMapper() throws IOException {
        Text text = new Text("good morning every body good afternoon every body");
        mapDriver.withInput(new LongWritable(), text);
        mapDriver.withOutput(new Text("good"), new LongWritable(1))
        .withOutput(new Text("morning"), new LongWritable(1))
        .withOutput(new Text("every"), new LongWritable(1))
        .withOutput(new Text("body"), new LongWritable(1))
        .withOutput(new Text("good"), new LongWritable(1))
        .withOutput(new Text("afternoon"), new LongWritable(1))
        .withOutput(new Text("every"), new LongWritable(1))
        .withOutput(new Text("body"), new LongWritable(1))
        ;
        mapDriver.runTest();
        // 输出
        List<Pair> expectedOutputList = mapDriver.getExpectedOutputs();
        for(Pair pair : expectedOutputList){
            System.out.println(pair.getFirst() + " --- " + pair.getSecond()); 
        }
    }
    
    /**
     * <good,[2,1]>===><good,3+>
     * @throws IOException
     */
    @Test
    public void testReducer() throws IOException {
        List<LongWritable> values = Lists.newArrayList();
        values.add(new LongWritable(2));
        values.add(new LongWritable(1));
        
        List<LongWritable> values2 = Lists.newArrayList();
        values2.add(new LongWritable(4));
        values2.add(new LongWritable(5));
        
        reduceDriver.withInput(new Text("good"), values).
        withInput(new Text("hello"), values2).
        withOutput(new Text("good"), new LongWritable(3)).
        withOutput(new Text("hello"), new LongWritable(9)).
        runTest();
        // 输出
        List<Pair> expectedOutputList = reduceDriver.getExpectedOutputs();
        for(Pair pair : expectedOutputList){
            System.out.println(pair.getFirst() + " --- " + pair.getSecond());
        }
    }
    
    /**
     * 按照字典排序
     * @throws IOException
     */
    @Test
    public void testMR() throws IOException{
        Text text1 = new Text("hello you hello me");
        Text text2 = new Text("how old are you");
        mapReduceDriver.withInput(new LongWritable(), text1).
        withInput(new LongWritable(), text2).
        withOutput(new Text("are"), new LongWritable(1)).
        withOutput(new Text("hello"), new LongWritable(2)).
        withOutput(new Text("how"), new LongWritable(1)).
        withOutput(new Text("me"), new LongWritable(1)).
        withOutput(new Text("old"), new LongWritable(1)).
        withOutput(new Text("you"), new LongWritable(2)).
        runTest();
        // 输出
        List<Pair> expectedOutputList = mapReduceDriver.getExpectedOutputs();
        for(Pair pair : expectedOutputList){
            System.out.println(pair.getFirst() + " --- " + pair.getSecond()); 
        }
    }
    
    
    
}
