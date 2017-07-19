package dashuju.celloud.hadoop.demo2;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.output.NullWriter;
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

import dashuju.celloud.hadoop.demo1.WordCountMapper;
import dashuju.celloud.hadoop.demo1.WordCountReducer;

public class OssCleanTest {
    private MapDriver mapDriver;
    private ReduceDriver reduceDriver;
    private MapReduceDriver mapReduceDriver;
    
    @Before
    public void setUp(){
    	OssCleanMapper mapper = new OssCleanMapper();
        mapDriver = MapDriver.newMapDriver(mapper);
//        mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, null);
    }
    
    @Test
    public void testMapper() throws IOException {
    	String remoteIp = "47.93.36.59";
		String temp1 = "-";
		String temp2 = "-";
		String time = "06/Mar/2017:15:13:32 +0800";
		String requestUri = "HEAD /logs_%24folder%24 HTTP/1.1";
		String httpStatus = "404";
		String sentBytes = "0";
		String requestTime = "1";
		String referer = "-";
		String userAgent = "aliyun-sdk-http/1.0()/ossfs1.79.8";
		String hostName = "bioinfo-cluster-dev.oss-cn-beijing.aliyuncs.com";
		String requestId = "58BD0C1C483412961E8C9FB3";
		String loggingFlag = "true";
		String aliyunId = "1295372657422098";
		String operation = "HeadObject";
		String bucket = "bioinfo-cluster-dev";
		String key = "logs_%24folder%24";
		String objectSize = "-";
		String serverCostTime = "1";
		String errorCode = "NoSuchKey";
		String requestLength = "254";
		String userId = "1295372657422098";
		String deltaDatasize = "-";
		String syncRequest = "-";
		String temp3 = "-";
		
        Text text = new Text(" 47.93.36.59 - - [06/Mar/2017:15:13:32 +0800] "
        		+ "\"HEAD /logs_%24folder%24 HTTP/1.1\" 404 0 1 \"-\" "
        		+ "\"aliyun-sdk-http/1.0()/ossfs1.79.8\" "
        		+ "\"bioinfo-cluster-dev.oss-cn-beijing.aliyuncs.com\" "
        		+ "\"58BD0C1C483412961E8C9FB3\" \"true\" \"1295372657422098\" "
        		+ "\"HeadObject\" \"bioinfo-cluster-dev\" \"logs_%24folder%24\" - 1 "
        		+ "\"NoSuchKey\" 254 \"1295372657422098\" - \"-\" \"-\"");
        mapDriver
        .withInput(new LongWritable(),text)
        .withOutput(NullWritable.get(),new OssLogWritable(remoteIp, temp1, temp2, time, requestUri, httpStatus, 
				sentBytes, requestTime, referer, userAgent, hostName, requestId, 
				loggingFlag, aliyunId, operation, bucket, key, objectSize,serverCostTime, 
				errorCode, requestLength, userId, deltaDatasize, syncRequest, temp3))
        .runTest();
        // 输出
        List<Pair> expectedOutputList = mapDriver.getExpectedOutputs();
        for(Pair pair : expectedOutputList){
            System.out.println(pair.getFirst() + " --- " + pair.getSecond()); 
        }
    }
}
