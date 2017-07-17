package dashuju.celloud.hadoop.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils  {
	public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * 去掉首尾方括号
	 * @param str
	 * @return
	 */
	public static String removeBracket(String str) {
		return str.replaceAll("\\[", "").replaceAll("\\]", "");
	}
	
	/**
	 * 判断一个时间是否在另一个时间之前
	 * @param time1 第一个时间
	 * @param time2 第二个时间
	 * @return 判断结果
	 */
	public static boolean before(String time1, String time2) {
		try {
			Date dateTime1 = TIME_FORMAT.parse(time1);
			Date dateTime2 = TIME_FORMAT.parse(time2);
			
			if(dateTime1.before(dateTime2)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Date parse2Date(String parse,String time){
//		SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MMM/yy:HH:mm:ss", Locale.ENGLISH);
		SimpleDateFormat inputFormat = new SimpleDateFormat(parse, Locale.ENGLISH);
		Date parse2 = null;
		try {
			parse2 = inputFormat.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse2;
	}
	
	public static void main(String[] args) {
		String str = "06/Mar/2017:11:05:16 +0800";
		Date parse2Date = parse2Date("dd/MMM/yy:HH:mm:ss",str);
		System.out.println(parse2Date);
	}
}
