package account;

import java.time.LocalDate;
import java.util.Date;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

interface doRegisters
{
	public boolean haveUser(String username);

	public int ifUsedEmail(String aEmail);

	public int insertUser(String username,String password,String aEmail,String headPicSrc);

	public int createUserDatabase(String username);
	
}
public class Register_Sql implements doRegisters{

	@Override
	public boolean haveUser(String username) {
		// TODO Auto-generated method stub
		Connection conn;
		Statement stmt;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt= conn.createStatement();
			ResultSet rs=stmt.executeQuery("select *from shouzhang_user where user_name='"+username+"'");
			while(rs.next()) {
				System.out.print(rs.getString("user_name"));
				conn.close();
				return true;
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public int ifUsedEmail(String aEmail) {
		// TODO Auto-generated method stub
		Connection conn;
		Statement stmt;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt= conn.createStatement();
			ResultSet rs=stmt.executeQuery("select *from shouzhang_user where user_email='"+aEmail+"'");
			while(rs.next()) {
				System.out.print(rs.getString("user_email"));
				conn.close();
				return 1;
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int insertUser(String username, String password, String aEmail,String headPicSrc) {
		// TODO Auto-generated method stub
		Connection conn;
		PreparedStatement stmt;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		String sql="insert into shouzhang_user(`user_name`,`user_password`,`user_email`,`time`,`headPicSrc`)  values(?,?,?,?,?)";
		
		SimpleDateFormat SimpleDateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
		String date=SimpleDateFormat.format(new Date());
		System.out.println(date);
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			stmt=(PreparedStatement)conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, aEmail);
			stmt.setString(4, date);
//			stmt.setString(5, uid);
			stmt.setString(5, headPicSrc);
			stmt.executeUpdate();
//			createUserDatabase(username);
			conn.close();
			return 1;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int createUserDatabase(String username) {
		// TODO Auto-generated method stub
		Connection conn1,conn2;
		Statement stmt1,stmt2;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		//打开一个已有的数据库
		try {
			Class.forName(driver);
			conn1=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt1= conn1.createStatement();
				System.out.print("未成功创建数据库");
			stmt1.executeUpdate("create database if not exists user"+username);	
			System.out.print("成功创建数据库");
			stmt1.close();
			conn1.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//打开创建的数据库继续创建表
		String url1="jdbc:mysql://localhost:3306/user"+username+"?useUnicode=true&characterEncoding=utf8";
		try {
			Class.forName(driver);
			conn2=DriverManager.getConnection(url1, mysql_user, mysql_password);
			stmt2= conn2.createStatement();
			stmt2.executeUpdate("create table if not exists friend(friend_name varchar(50))");
			stmt2.executeUpdate("create table if not exists read_information(time varchar(50),text varchar(1000),type varchar(50))");
			stmt2.executeUpdate("create table if not exists unread_information(time varchar(50),text varchar(1000),type varchar(50))");
			
			stmt2.close();
			conn2.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Register_Sql r=new Register_Sql();
//		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
		r.insertUser("1234567abc", "11111111", "123456789@qq.com","sGdhjffd");
		System.out.print(r.ifUsedEmail("214456@qq.com"));
		System.out.print(r.haveUser("1234567abc"));
	}
}
