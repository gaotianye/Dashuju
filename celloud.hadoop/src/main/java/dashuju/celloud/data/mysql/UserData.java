package dashuju.celloud.data.mysql;

import java.io.File;
import java.io.PrintWriter;

/**
	create table user (
	user_no int (11) COMMENT '编号' DEFAULT '0' PRIMARY KEY,
	user_age int (11) COMMENT '年龄' DEFAULT '0' NOT NULL,
	user_job varchar (150) COMMENT '工作' DEFAULT '待就业' NOT NULL,
	user_sal double COMMENT '工资/月' DEFAULT '0.00' NOT NULL,
	user_sale double COMMENT '开销/月' DEFAULT '0.00' NOT NULL 
	); 
	insert into user (no, age, job, sal, sale) values('1','20','会计','2500','1500');
 * @author Administrator
 *
 */
public class UserData {
	public static void main(String[] args) {
		createDatas("10000","D:/ttt.sql");
		/*if (args.length!=2) {
			System.err.println("Usage: UserData <count W> <out xx/xx.sql>");
			System.exit(2);
		}
		int count = Integer.parseInt(args[0]);
		createDatas(count * 10000+"",args[1]);*/
	}
	
	private static void createDatas(String count, String out) {
		int c = Integer.parseInt(count);
		long t1 = System.currentTimeMillis();
		File file = new File(out);
		if (file.exists()) {
			file.delete();
		}
		try (PrintWriter writer = new PrintWriter(file, "UTF-8");) {
			writer.write("DROP TABLE IF EXISTS user;"+"\n");
			writer.write("CREATE TABLE IF NOT EXISTS user("+"\n");
			writer.write("user_no int (11) COMMENT '编号' DEFAULT '0' PRIMARY KEY,"+"\n");
			writer.write("user_age int (11) COMMENT '年龄' DEFAULT '0' NOT NULL,"+"\n");
			writer.write("user_job varchar (150) COMMENT '工作' DEFAULT '待就业' NOT NULL,"+"\n");
			writer.write("user_sal double COMMENT '工资/月' DEFAULT '0.00' NOT NULL,"+"\n");
			writer.write("user_sale double COMMENT '开销/月' DEFAULT '0.00' NOT NULL"+"\n");
			writer.write(");"+"\n");
			for (int i = 1; i <= c; i++) {
				String age = "\'"+RandomValue.getAge()+"\'";
				String job = "\'"+RandomValue.getJob()+"\'";
				String sal = "\'"+RandomValue.getSal()+"\'";
				String sale = "\'"+RandomValue.getSale()+"\'";
				writer.write("insert into user values('"+i+"',"+age+","+job+","+sal+","+sale+");" + "\n");
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
