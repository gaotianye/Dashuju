package dashuju.celloud.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参考：http://www.jb51.net/article/16829.htm
 * @author Administrator
 *
 */
public class RegexOssLog {
	public static void main(String[] args) {
//		String input = " 47.93.36.59 - - [06/Mar/2017:15:13:32 +0800] \"HEAD /logs_%24folder%24 HTTP/1.1\" 404 0 1 \"-\" \"aliyun-sdk-http/1.0()/ossfs1.79.8\" \"bioinfo-cluster-dev.oss-cn-beijing.aliyuncs.com\" \"58BD0C1C483412961E8C9FB3\" \"true\" \"1295372657422098\" \"HeadObject\" \"bioinfo-cluster-dev\" \"logs_%24folder%24\" - 1 \"NoSuchKey\" 254 \"1295372657422098\" - \"-\" \"-\"";
		String input = " 47.93.36.59 - - [06/Mar/2017:15:13:32 +0800] \"HEAD /logs_%24folder%24 HTTP/1.1\" 404 0 1 \"-\" \"aliyun-sdk-http/1.0()/ossfs1.79.8\" \"bioinfo-cluster-dev.oss-cn-beijing.aliyuncs.com\" \"58BD0C1C483412961E8C9FB3\" \"true\" \"1295372657422098\" \"HeadObject\" \"bioinfo-cluster-dev\" \"logs_%24folder%24\" - 1 \"NoSuchKey\" 254 \"1295372657422098\" - \"-\" \"-\"";
		String regex = "\\s(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\s(.+)\\s(.+)\\s\\[([^]]+)\\]\\s\"([^\"]+)\"\\s([0-9]+)\\s([0-9]+)\\s([0-9]+)\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s\"([^\"]+)\"\\s(.+)\\s(.+)\\s\"([^\"]+)\"\\s([0-9]+)\\s\"([^\"]+)\"\\s(.+)\\s\"([^\"]+)\"\\s\"([^\"]+)\"";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if(matcher.find()){
			int count = matcher.groupCount();
			for(int i =1;i<=count;i++){
				String group = matcher.group(i);
				System.out.println(i+"==>"+group);
			}
		}
		
	}
}
