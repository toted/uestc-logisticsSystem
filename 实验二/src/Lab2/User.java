package Lab2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {

		private String userId;//用户名
		private String password;//密码
		private String email;//邮箱
		private String code;//验证码
		private boolean isVip;//是否为vip
		private boolean status;//是否激活
		public boolean insert(User user) throws SQLException {
			Statement stmt=Operate.Log();
			ResultSet rs=null;
			String sql="insert into user (userid,password,email,code,isvip,status) values"+"('"+user.userId+"','"+user.password+"','"+user.email+"','"+user.code+"','"+user.isVip+"','"+user.status+"')";
			System.out.println(sql);
			int i=stmt.executeUpdate(sql);
			stmt.getConnection().close();
			if(i>0)
			{
				return true;
			}
			else {
				return false;
			}
		}
		public boolean delete(User user) throws SQLException {
			Statement stmt=Operate.Log();
			ResultSet rs=null;
			String sql="delete from user where userid = '"+user.userId+"'";
			int i=stmt.executeUpdate(sql);
			stmt.getConnection().close();
			if(i>0)
			{
				return true;
			}
			else {
				return false;
			}
		}
		public boolean update(User user,boolean status,String code) throws SQLException {
			Statement stmt=Operate.Log();
			ResultSet rs=null;
			String sql="update user set status = "+status+","+"code='"+code+"' "+"where userid = '"+user.userId+"'";
			System.out.println(sql);
			int i=stmt.executeUpdate(sql);
			stmt.getConnection().close();
			if(i>0)
			{
				return true;
			}
			else {
				return false;
			}
		}
		public User select(User user) throws SQLException {
			User newUser=new User();
			Statement stmt=Operate.Log();
			ResultSet rs=null;
			String sql="select * from user where userid = '"+user.userId+"' or email = '"+user.email+"'";
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				newUser.code=rs.getString("code");
				newUser.email=rs.getString("email");
				newUser.isVip=Boolean.parseBoolean(rs.getString("isVip"));
				newUser.status=Boolean.parseBoolean(rs.getString("status"));
				newUser.userId=rs.getString("userid");
				newUser.password=rs.getString("password");
			}
			stmt.getConnection().close();
			return newUser;
		}
		public int selectCount(User user) throws SQLException
		{
			Statement stmt=Operate.Log();
			ResultSet rs=null;
			int i=-1;
			String sql="select count(*) a from user where userid ='"+user.userId+"'or email = '"+user.email+"'";
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				i=rs.getInt("A");
			}
			stmt.getConnection().close();
			return i;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public boolean isVip() {
			return isVip;
		}
		public void setVip() {
			if(this.userId.contains("admin") == true) {
				this.isVip = true;
			}
			else this.isVip = false;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		
	}

