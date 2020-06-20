package Lab2;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.imageio.spi.RegisterableService;
import javax.swing.JLabel;
import javax.xml.crypto.Data;

public class Operate {

	public static boolean canUse(Statement stmt,ResultSet rs,String way) throws SQLException {//查询信号量是否可用，way表示用途
		int read=0;
		boolean use=false;
//		System.out.println("chaxunduxhaoliang");
		rs=stmt.executeQuery("SELECT * from WULIU1 WHERE Title='yes'");
		int write=0;
		if(rs.next()) 
		{
			write=rs.getInt("write");
			read=rs.getInt("read");
			System.out.println("write"+write);
			System.out.println("read"+read);
			if(way.equals("read"))
			{
				if(write==0)
				{
					use=true;
				}
			}
			else if(way.equals("write"))
			{
				if((write==0)&&(read==0))
				{
					use=true;
				}
			}
		}
		return use;
	}
	public static boolean ApplyAndUseSignal(String way,Statement stmt,ResultSet rs) throws SQLException {//申请信号量
		boolean success=false;
		try {
			success=canUse(stmt, rs, way);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(success)
		{
			String sql="UPDATE WULIU1 SET "+way+"="+way+"+1 where title='yes'";
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
		}
		return success;
	}
	public static void ReleaseSignal(String way,Statement stmt,ResultSet rs) throws SQLException {
		rs=stmt.executeQuery("SELECT * from WULIU1 WHERE Title='yes'");
		int i=0;
		if(rs.next())
		{
			i=rs.getInt(way);
			if(i>0)
			{
//				System.out.println("keshifang");
				String sql="UPDATE WULIU1 SET "+way+"="+way+"-1 where title='yes'";
				System.out.println(sql);
				stmt.executeQuery(sql);
			}
			else {
				System.out.println("can not release "+way);
			}
		}
	}
	public static Statement Log() throws SQLException {//创建与数据库的连接
		Connection con=null;
		try {
			Class.forName("com.huawei.gauss.jdbc.ZenithDriver");
			String url ="jdbc:zenith:@192.168.245.136:1888?useSSL=true";
			String username ="joseph";
			String password ="pam__109";
			con = DriverManager.getConnection(url,username,password);
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement stmt=null;
		stmt=con.createStatement();
		return stmt;
	}
	public static String getTable(Statement stmt,ResultSet rs,String number) throws SQLException {//获取某物品具体物流信息表名
		String tableNameString=null;
		String sql="SELECT * from WULIU1 WHERE NUM='"+number+"'";
		System.out.println(sql);
		rs=stmt.executeQuery(sql);
		if(rs.next())
		{
			tableNameString=rs.getString("DETAIL_TABLE");
			System.out.println("table:"+tableNameString);
		}
		return tableNameString;
	}
	public static void createTable(Statement stmt,ResultSet rs,String number) throws SQLException {
		System.out.println(number);
		String tableName=getTable(stmt, rs, number);
		System.out.println(tableName);
		String none="none";
		if(tableName.equals(none))
		{
			System.out.println("jiangyaojianbiao");
			String sql="CREATE TABLE detail_"+number+"\r\n" + 
					"(\r\n" + 
					"num VARCHAR2(30),\r\n" + 
					"now_city VARCHAR2(30),\r\n" + 
					"time VARCHAR2(30)\r\n" + 
					")" + 
					"TABLESPACE WULIU";
			System.out.println(sql);
			rs=stmt.executeQuery(sql);
			stmt.executeQuery("UPDATE WULIU1 set detail_table='detail_"+number+"' where num='"+number+"'");
		}
		else {
			System.out.println("have table");
		}
	}
	public static ArrayList<object> read(Statement stmt,ResultSet rs) throws SQLException {//无条件查询，返回对象列表
		String sql="select * from wuliu1 order by arrival";
		rs=stmt.executeQuery(sql);
		int price=0;
		String time=null;
		String num=null;
		String destination=null;
		String departure=null;
		String scheme=null;
		String state=null;
		Boolean vip=false;
		String name=null;
		String phone=null;
		int weight;
//		int i=rs.getRow();
		ArrayList<object>arrayList=new ArrayList<object>();
		while(rs.next())
		{
			String title=rs.getString("title");
			if(title.equals("no"))
			{
				num=rs.getString("NUM");
			destination=rs.getString("destination");
			departure=rs.getString("departure");
			scheme=rs.getString("scheme");
			state=rs.getString("state");
			time=rs.getString("arrival");
			weight=rs.getInt("weight");
			phone=rs.getString("phone");
			name=rs.getString("name");
			vip=vip.parseBoolean(rs.getString("vip"));
			//name="食品";
//			System.out.println(num+" "+departure+" "+scheme+" "+destination+" "+state+" "+time+" "+weight+" "+vip);
			object temObject=new object(name, num, time, departure, destination, weight, vip, scheme,phone);
			arrayList.add(temObject);			
			}
		}
//		System.out.println(i);
		return arrayList;
	}
	public static ArrayList<object> read(Statement stmt,ResultSet rs,String key,String Constrain) throws SQLException {//有条件查询，需要传入数学值和约束条件
		String sql="select * from wuliu1 where "+Constrain+" ='"+key+"'";
		rs=stmt.executeQuery(sql);
		int price=0;
		String time=null;
		String num=null;
		String destination=null;
		String departure=null;
		String scheme=null;
		String state=null;
		Boolean vip=false;
		String name=null;
		String phone=null;
		int weight;
		ArrayList<object>arrayList=new ArrayList<object>();
		while(rs.next())
		{
			String title=rs.getString("title");
			if(title.equals("no"))
			{
				num=rs.getString("NUM");
			destination=rs.getString("destination");
			departure=rs.getString("departure");
			scheme=rs.getString("scheme");
			state=rs.getString("state");
			time=rs.getString("arrival");
			weight=rs.getInt("weight");
			phone=rs.getString("phone");
			name=rs.getString("name");
			vip=vip.parseBoolean(rs.getString("vip"));
			object temObject=new object(name, num, time, departure, destination, weight, vip, scheme,phone);
			arrayList.add(temObject);			
			}
		}
		return arrayList;
	}
	public static int UserLog(Statement stmt,ResultSet rs,String password,String mail,String phone) throws SQLException {
		String sql="select * from users where phone='"+phone+"'";
		rs=stmt.executeQuery(sql);
		if(rs.next()) {
			String Qpassword=rs.getString("password");
			String Qmail=rs.getString("mail");
				if(Qpassword.equals(password))
				{
					return 0;
				}
				else {
					return 2;
				}
		}
		else {
			return 1;
		}
	}
	public  static int Registe(Statement stmt,ResultSet rs,String password,String mail,String phone) throws SQLException {//注册？可能没用
		String sql="select * from users where phone='"+phone+"'";
		rs=stmt.executeQuery(sql);
		if(rs.next())
		{
			return 2;
		}
		else 
		{
			sql="SELECT max(num)+1 num from USERS";
			rs=stmt.executeQuery(sql);
			String numString;
			if(rs.next())
			{
				numString=rs.getString("num");
				System.out.println(numString);
				sql="INSERT INTO users(vip,MAIL,NUM,PASSWD,PHONE) VALUES (FALSE,'"+mail+"','"+numString+"','"+password+"','"+phone+"')";
				System.out.println(sql);
				int i=stmt.executeUpdate(sql);
				if(i>0)
				{
					return 0;
				}
				else
				{
					return 3;
				}
			}
			else
			{
				return 3;
			}
		}
	}
	public static String timeString() throws ParseException {//用当前时间生成字符串
		Date date=new Date();
		String arrival=null;
	    SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddHHmmss"); 
	    return arrival=formatter.format(date);
	}
	public static boolean write(Statement stmt,ResultSet rs,object a) throws ParseException, SQLException {//写入，即寄件
		String num=a.num;
		String arrival=a.time;
		int weight=a.weight;
		String departure=a.s;
		System.out.println(arrival);
	    Date date=null;
	    SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddHHmmss"); 
	    date=formatter.parse(arrival); 
	    String phone=a.phone;
	    arrival=formatter.format(date);
	    formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    arrival=formatter.format(date);
	    System.out.println(arrival);
		String dest=a.des;
		String state="等待发货";
		String scheme=a.scheme;
		String name=a.name;
		boolean vip=a.vip;
		if(vip)
		{
			scheme="最短时间";
		}
		String Qsql="select * from wuliu1 where num='"+num+"'";
		rs=stmt.executeQuery(Qsql);
		if(!rs.next())
		{
			String sql="insert into wuliu1(num,arrival,weight,departure,destination,state,vip,scheme,phone,name) values "+"('"+num+"','"+arrival+"',"+weight+",'"+departure+"','"+dest+"','"+state+"',"+vip+",'"+scheme+"','"+phone+"','"+name+"')";
		 System.out.println(sql);
		int i=stmt.executeUpdate(sql);
		if(i>0)
		{
			createTable(stmt, rs, num);
			return true;
		}
		else {
			return false;
		}
		}
		else {
			return false;
		}
	}
	public static boolean isVip(Statement stmt,ResultSet rs,String phone) throws SQLException {//查询是否是VIP
		boolean a=false;
		String sql="select * from users where phone='"+phone+"'";
		rs=stmt.executeQuery(sql);
		while ( rs.next()) {
			a=Boolean.parseBoolean(rs.getString("vip"));
		}
		return a;
	}
	public static boolean isExist(Statement stmt,ResultSet rs,String phone) throws SQLException {
		boolean a=false;
		String sql="select * from user_ where phone='"+phone+"'";
		rs=stmt.executeQuery(sql);
		if(rs.next())
		{
			a=true;
		}
		return a;
	}
	public static ArrayList<String[]> status(Statement stmt,ResultSet rs,String num) throws SQLException {
		String sql="select* from detail_"+num;
		ArrayList<String[]>reArrayList=new ArrayList<String[]>();
		rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			String[]temp=new String[3];
			temp[0]=rs.getString("");
			temp[1]=rs.getString("");
			temp[2]=rs.getString("");
			reArrayList.add(temp);
		}
		return reArrayList;
	}
	public static boolean sendGood(Statement stmt,ResultSet rs,String num,String departure,String time) throws SQLException {
		String sql="update wuliu1 set status='已发货' where num='"+num+"'";
		int i=stmt.executeUpdate(sql);
		sql="insert into detail_"+num+"(num,now_city,time) values ('"+num+"','"+departure+"','"+time+"')";
		int j=stmt.executeUpdate(sql);
		if(i>0)
		{
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean arrive(Statement stmt,ResultSet rs,String num) throws SQLException {
		String sql="update wuliu1 set status='已送达' where num='"+num+"'";
		String statuString=GetStatus(stmt, rs, num);
		int i=0;
		if(statuString.equals("已发货"))
		{
			i=stmt.executeUpdate(sql);
		}
		if(i>0)
			return true;
		else {
			return false;
		}
	}
	public static boolean cancel(Statement stmt,ResultSet rs,String num) throws SQLException {
		String status=GetStatus(stmt, rs, num);
		if(!status.equals("已发货"))
		{
			String sql="delete from wuliu1 where num='"+num+"'";
			int i=stmt.executeUpdate(sql);
			sql="drop table detail_"+num;
			int j=stmt.executeUpdate(sql);
			if((i>0))
				return true;
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	public static String GetStatus(Statement stmt,ResultSet rs,String num) throws SQLException {
		String sql="select *from wuliu1 where num='"+num+"'";
		rs=stmt.executeQuery(sql);
		String status=null;
		while(rs.next())
		{
			status=rs.getString("status");
		}
			return status;
	}
}
