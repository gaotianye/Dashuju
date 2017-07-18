package dashuju.celloud.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dashuju.celloud.hadoop.demo2.OssLogWritable;

/**
 * 参考：http://www.jb51.net/article/16829.htm
 * @author Administrator
 *
 */
public class RegexOssLog {
	public static void main(String[] args) {
		OssLogWritable osslog = new OssLogWritable();
//		String input = " 47.93.36.59 - - [06/Mar/2017:15:13:32 +0800] \"HEAD /logs_%24folder%24 HTTP/1.1\" 404 0 1 \"-\" \"aliyun-sdk-http/1.0()/ossfs1.79.8\" \"bioinfo-cluster-dev.oss-cn-beijing.aliyuncs.com\" \"58BD0C1C483412961E8C9FB3\" \"true\" \"1295372657422098\" \"HeadObject\" \"bioinfo-cluster-dev\" \"logs_%24folder%24\" - 1 \"NoSuchKey\" 254 \"1295372657422098\" - \"-\" \"-\"";
		String input = " 47.93.36.59 - - [06/Mar/2017:15:13:32 +0800] \"HEAD /logs_%24folder%24 HTTP/1.1\" 404 0 1 \"-\" \"aliyun-sdk-http/1.0()/ossfs1.79.8\" \"bioinfo-cluster-dev.oss-cn-beijing.aliyuncs.com\" \"58BD0C1C483412961E8C9FB3\" \"true\" \"1295372657422098\" \"HeadObject\" \"bioinfo-cluster-dev\" \"logs_%24folder%24\" - 1 \"NoSuchKey\" 254 \"1295372657422098\" - \"-\" \"-\"";
		String regex = "\\s(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\s(.+)\\s(.+)\\s\\[([^]]+)\\]\\s\"([^\"]+)\"\\s([0-9]+)\\s([0-9]+)\\s([0-9]+)\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s(.+)\\s(.+)\\s\"([^\"]+)\"\\s([0-9]+)\\s\"([^\"]+)\"\\s(.+)\\s\"([^\"]+)\"\\s\"([^\"]+)\"";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if(matcher.find()){
			osslog = add2OssLog(matcher,osslog);
			System.out.println(osslog);
		}
		
	}

	private static OssLogWritable add2OssLog(Matcher matcher, OssLogWritable osslog) {

		osslog.setRemoteIp(matcher.group(1));
		osslog.setTemp1(matcher.group(2));
		osslog.setTemp2(matcher.group(3));
		osslog.setTime(matcher.group(4));
		osslog.setRequestUri(matcher.group(5));
		
		osslog.setHttpStatus(matcher.group(6));
		osslog.setSentBytes(matcher.group(7));
		osslog.setRequestTime(matcher.group(8));
		osslog.setReferer(matcher.group(9));
		osslog.setUserAgent(matcher.group(10));
		
		osslog.setHostName(matcher.group(11));
		osslog.setRequestId(matcher.group(12));
		osslog.setLoggingFlag(matcher.group(13));
		osslog.setAliyunId(matcher.group(14));
		osslog.setOperation(matcher.group(15));
		
		osslog.setBucket(matcher.group(16));
		osslog.setKey(matcher.group(17));
		osslog.setObjectSize(matcher.group(18));
		osslog.setServerCostTime(matcher.group(19));
		osslog.setErrorCode(matcher.group(20));
		
		osslog.setRequestLength(matcher.group(21));
		osslog.setUserId(matcher.group(22));
		osslog.setDeltaDatasize(matcher.group(23));
		osslog.setSyncRequest(matcher.group(24));
		osslog.setTemp3(matcher.group(25));
		return osslog;
	}
}
