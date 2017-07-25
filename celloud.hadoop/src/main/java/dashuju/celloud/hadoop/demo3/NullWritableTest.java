package dashuju.celloud.hadoop.demo3;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
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
public class NullWritableTest {
    private MapDriver mapDriver;
    private ReduceDriver reduceDriver;
    private MapReduceDriver mapReduceDriver;
    
    @Before
    public void setUp(){
    	NullValueMapper mapper = new NullValueMapper();
        mapDriver = MapDriver.newMapDriver(mapper);
        
        //NullValueReducer reducer = new NullValueReducer();
        //reduceDriver = ReduceDriver.newReduceDriver(reducer);
        //mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
    }
    
    @Test
    public void testMapper() throws IOException {
        Text text1 = new Text("2012-3-7 c");
        Text text2 = new Text("2012-3-1 a");
        Text text3 = new Text("2012-3-2 c");
        Text text4 = new Text("2012-1-7 d");
        Text text5 = new Text("2012-3-1 c");
        mapDriver
        .withInput(new LongWritable(), text1)
        .withInput(new LongWritable(), text2)
        .withInput(new LongWritable(), text3)
        .withInput(new LongWritable(), text4)
        .withInput(new LongWritable(), text5)
        ;
        mapDriver
        .withOutput(new Text("2012-3-7 c"), NullWritable.get())
        .withOutput(new Text("2012-3-1 a"), NullWritable.get())
        .withOutput(new Text("2012-3-2 c"), NullWritable.get())
        .withOutput(new Text("2012-1-7 d"), NullWritable.get())
        .withOutput(new Text("2012-3-1 c"), NullWritable.get())
        ;
        mapDriver.runTest();
        // 输出
        List<Pair> expectedOutputList = mapDriver.getExpectedOutputs();
        for(Pair pair : expectedOutputList){
            System.out.println(pair.getFirst() + " --- " + pair.getSecond()); 
        }
    }
    
    @Test
    public void testReducer() throws IOException {
        List<NullWritable> values = Lists.newArrayList();
        values.add(NullWritable.get());
        values.add(NullWritable.get());
        values.add(NullWritable.get());
        
        
        reduceDriver.withInput(new Text("2012-3-1 c"), values).
        withOutput(new Text("2012-3-1 c"), NullWritable.get()).
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
