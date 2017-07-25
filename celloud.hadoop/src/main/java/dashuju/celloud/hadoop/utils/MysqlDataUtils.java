package dashuju.celloud.hadoop.utils;

import java.io.File;
import java.io.PrintWriter;

/**
 * insert into dept values('10029016','张爽','female','13036552145','北京市');
 * 	CREATE TABLE dept(
	dept_no INT (11) PRIMARY KEY,
	dept_name VARCHAR (60),
	dept_sex VARCHAR (30),
	dept_tel VARCHAR (90),
	dept_address VARCHAR (150)
	);
 * @author Administrator
 *
 */
public class MysqlDataUtils {
	public static void main(String[] args) {
		createDatas(1000000,"D:/yyy.sql");
	}
	
	private static void createDatas(int count, String out) {
		long t1 = System.currentTimeMillis();
		File file = new File(out);
		if (file.exists()) {
			file.delete();
		}
		try (PrintWriter writer = new PrintWriter(file, "UTF-8");) {
			writer.write("CREATE TABLE dept("+"\n");
			writer.write("dept_no INT (11) PRIMARY KEY,"+"\n");
			writer.write("dept_name VARCHAR (60),"+"\n");
			writer.write("dept_sex VARCHAR (30),"+"\n");
			writer.write("dept_tel VARCHAR (90),"+"\n");
			writer.write("dept_address VARCHAR (150)"+"\n");
			writer.write(");"+"\n");
			for (int i = 1; i <= count; i++) {
				String name = "\'"+RandomValue.getChineseName()+"\'";
				String sex = "\'"+RandomValue.getSex()+"\'";
				String tel = "\'"+RandomValue.getTel()+"\'";
				String addr = "\'"+RandomValue.getRoad()+"\'";
				writer.write("insert into dept values('"+i+"',"+name+","+sex+","+tel+","+addr+");" + "\n");
				if(i%10000==0){
					System.out.println("已经生产"+i/10000+"W条数据");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long t2 = System.currentTimeMillis();
		System.out.println(count+"条数据创建成功！耗时：" + (t2 - t1) + "毫秒。");
	}
}
