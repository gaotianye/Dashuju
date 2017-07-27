package dashuju.celloud.data.mysql;

import java.io.File;
import java.io.PrintWriter;

import com.sun.tools.javac.util.StringUtils;

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
public class DeptData {
	public static void main(String[] args) {
//		createDatas("1000000","D:/yyy.sql");
		if (args.length!=2) {
			System.err.println("Usage: DeptData <count W> <out xx/xx.sql>");
			System.exit(2);
		}
		int count = Integer.parseInt(args[0]);
		createDatas(count * 10000+"",args[1]);
	}
	
	private static void createDatas(String count, String out) {
		int c = Integer.parseInt(count);
		long t1 = System.currentTimeMillis();
		File file = new File(out);
		if (file.exists()) {
			file.delete();
		}
		try (PrintWriter writer = new PrintWriter(file, "UTF-8");) {
			writer.write("DROP TABLE IF EXISTS dept;"+"\n");
			writer.write("CREATE TABLE IF NOT EXISTS dept("+"\n");
			writer.write("dept_no INT (11) COMMENT '编号' DEFAULT '0'  PRIMARY KEY,"+"\n");
			writer.write("dept_name VARCHAR (60) COMMENT '姓名' DEFAULT 'xxx'  NOT NULL,"+"\n");
			writer.write("dept_sex VARCHAR (30) COMMENT '性别' DEFAULT 'xxx'  NOT NULL,"+"\n");
			writer.write("dept_tel VARCHAR (90) COMMENT '手机号' DEFAULT 'xxx'  NOT NULL,"+"\n");
			writer.write("dept_address VARCHAR (150) COMMENT '家庭住址' DEFAULT 'xxx'  NOT NULL"+"\n");
			writer.write(");"+"\n");
			for (int i = 1; i <= c; i++) {
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
		System.out.println(c+"条数据创建成功！耗时：" + (t2 - t1) + "毫秒。");
	}
}
