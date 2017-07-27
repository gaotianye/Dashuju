package dashuju.celloud.data.mysql;

import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

/**
create table colleague (
colleague_no int (11) COMMENT '编号' DEFAULT '0' PRIMARY KEY,
colleague_worker_no int (11) COMMENT '同事编号' DEFAULT '0' NOT NULL
); 
insert into user values('1','20');
* @author Administrator
*
*/
public class ColleagueData {
	public static void main(String[] args) {
//		createDatas("1000","D:/ttt.sql");
		if (args.length!=2) {
			System.err.println("Usage: ColleagueData <count W> <out xx/xx.sql>");
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
		Random r = new Random();
		try (PrintWriter writer = new PrintWriter(file, "UTF-8");) {
			writer.write("DROP TABLE IF EXISTS colleague;"+"\n");
			writer.write("CREATE TABLE IF NOT EXISTS colleague("+"\n");
			writer.write("colleague_no int (11) COMMENT '编号' DEFAULT '0' PRIMARY KEY,"+"\n");
			writer.write("colleague_worker_no int (11) COMMENT '同事编号' DEFAULT '0' NOT NULL"+"\n");
			writer.write(");"+"\n");
			for (int i = 1; i <= c; i++) {
				writer.write("insert into colleague values('"+i+"','"+r.nextInt(c)+"');" + "\n");
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
