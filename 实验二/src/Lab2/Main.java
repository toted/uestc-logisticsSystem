package Lab2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
/*
 * Operate类中含有与数据库相关的函数
 * MD5含有哈希算法
 * GUI为实验二使用的图形界面
 * Operate类log()函数使用的驱动为GaussDB，如果没有GaussDB此处换成SQL的即可
 * */
	public static void main(String argString[]) throws SQLException, ParseException {
		GUI gui=new GUI();
//		User user=new User();
//		user.setCode("1793");
//		user.setEmail("445");
//		user.setPassword("0130");
//		user.setStatus(false);
//		user.setUserId("1578");
//		user.setVip();
		
		
//		String time=Operate.timeString();
//		String num=MD5.stringToMD5(time);
//		object ob=new object("衣服", num, time, "北京", "西安", 10, false, "最短时间", "12345");
//		wuliu.object obj=new wuliu.object("衣服", Long.parseLong(time), "北京", "西安", 10, false);
//		wuliu.dijkstra dij=new wuliu.dijkstra();
//		if(obj.getvip()) {
//			System.out.println("您的货物将尽可能空运");
//			dij.print.add("您的货物将尽可能空运");
//			 dij.dijkstra1(obj.gets(),obj.getdes(),obj.gettime(),wuliu.zuiyouwuliu.flycost,wuliu.zuiyouwuliu.money2);
//			 }
//		else {
//			dij.dijkstra1(obj.gets(),obj.getdes(),obj.gettime(),wuliu.zuiyouwuliu.cost,wuliu.zuiyouwuliu.money1);
//			}
//		System.out.println();
//		for(String a:dij.print)
//		{
//			System.out.println(a);
//		}
		
		
//		dij.dijkstra1("北京", "西安", Long.parseLong(time+"L"), Matrix, imoney);
		System.out.println();
//		user.insert(user);
//		boolean a=user.update(user, true, "9999");
//		User a=user.select(user);
//		System.out.println(user.selectCount(user));
		
//		Statement stmt=Operate.Log();
//		System.out.println("cg");
//		ResultSet rs=null;
//		Operate.getTable(stmt, rs, "1234");
//		Operate.ReleaseSignal("read", stmt, rs);
//		Operate.ApplyAndUseSignal("read", stmt, rs);
//		
//		Operate.createTable(stmt, rs, "13456");
//		Operate.ReleaseSignal("read", stmt, rs);
//		Operate.ReleaseSignal("write", stmt, rs);
//		Operate.ReleaseSignal("read", stmt, rs);
//		Operate.ReleaseSignal("read", stmt, rs);
//		Operate.ReleaseSignal("read", stmt, rs);
//		Operate.ReleaseSignal("read", stmt, rs);
//		String aString=Operate.getTable(stmt, rs, "13456");
//		System.out.println("a="+aString);
//		boolean read=Operate.canUse(stmt, rs,"read");
//		System.out.println(Operate.ApplyAndUseSignal("read", stmt, rs));
//		read=Operate.canUse(stmt, rs,"read");
//		Operate.ReleaseSignal("read", stmt, rs);
//		System.out.println(read);
//		String password="12345";
//		String mail="1234@qq.com";
//		String phone="13892919103";
//		int i=Operate.Registe(stmt, rs, password, mail, phone);
////		System.out.println(i);
//		object a=new object("衣服","832749821","2020060102080907","北京","成都",3, true);
//		boolean i=Operate.write(stmt, rs, a);
//		System.out.println(i);
		
//		dijkstra graph=new dijkstra();
//		graph.dijkstra1("北京", "成都",graph.cost);
//		System.out.println(Operate.isVip(stmt, rs, "13892919136"));
//		String time=Operate.timeString();
//		System.out.println(time);
//		object a=new object("1",MD5.stringToMD5(time), time, "2","2", 2, true, "2", "2");
//		Operate.write(stmt, rs, a);
//		Date date=new Date();
//		SimpleDateFormat dFormat=new SimpleDateFormat("yyyyMMddHHmmss");
//		String now=dFormat.format(date);
//		System.out.println(now);
//		 dFormat=new SimpleDateFormat("yyyy-HH-dd HH:mm:ss");
//		 
//		now=MD5.stringToMD5(now);
//		now=now.substring(0, 15);
//		System.out.println(now);
//		Operate.createTable(stmt, rs, "e183ad5b225c3c09bfbc90837dc241f9");
	}

}
