package account;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.sun.mail.handlers.text_html;

import friend.Friend_Sql;

interface JudgeLogin {
	public User getUser(String name,String passwrd);
	//通过用户名密码返回用户对象
	public boolean checkUser(String name);
	//查找用户是否存在
	public User getUser(String name);
	//通过用户名返回用户对象

}
public class Login_sql implements JudgeLogin{
	

//	public static void main(String[] args) throws UnsupportedEncodingException
//	{
////		System.out.print(judge("12345678","11111111").name);
//		TestMysql2 t=new TestMysql2();
//	//t.insertUser("12345678abc","11111111");
//		//System.out.print(t.checkUser("1234567", "11111111"));
//	//System.out.print( t.getuser("12345678","11111111").password);
//		User user1=t.getUser("12345678abc", "11111111");
//		if(user1!=null) {//闂囷拷鐟曚礁鍨介弬顓烆嚠鐠炩剝妲搁崥锔胯礋缁岋拷
//			System.out.print("閻€劍鍩涚�涙ê婀�");
//			System.out.print(user1.name+" "+user1.password);
//		}
//		else
//			System.out.print("閻€劍鍩涙稉宥呯摠閸︼拷");
//		
//		
//	}
//	public int insertUser(String username, String password, String aEmail, SimpleDateFormat SimpleDateFormat) {
//		// TODO Auto-generated method stub
//		Connection conn;
//		PreparedStatement stmt;
//		String driver="com.mysql.jdbc.Driver";
//		String url="jdbc:mysql://localhost:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
//		String mysql_user="root";
//		String mysql_password="12138";
//		String sql="insert into shouzhang_user  values(?,?,?,?)";
//		String date=SimpleDateFormat.format(new Date(0));
//		
//		try {
//			Class.forName(driver);
//			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
//			stmt=(PreparedStatement)conn.prepareStatement(sql);
//			stmt.setString(1, username);
//			stmt.setString(2, password);
//			stmt.setString(3, aEmail);
//			stmt.setString(4, date);
//			stmt.executeUpdate();
//			return 1;
//		}catch(ClassNotFoundException e) {
//			e.printStackTrace();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}

	public User getUser(String username,String password)
	{
		Connection conn;
		Statement stmt;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		User us;
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt= conn.createStatement();
			ResultSet rs=stmt.executeQuery("select *from shouzhang_user where user_name='"+username+"'");			
			while(rs.next()) {
				//System.out.print(rs.getString("password"));
				if(rs.getString("user_password").equals(password))
				{
					us=new User(rs.getString("user_name"),rs.getString("uid"),rs.getString("headPicSrc"),null);
//					System.out.println(rs.getString("headPicSrc"));
					Friend_Sql fsql = new Friend_Sql();
					ArrayList<String> friends = fsql.getFriend(rs.getString("user_name"));
					System.out.println(friends);
					if(!friends.isEmpty())
						us.addAllFriend(friends);
					conn.close();
					return us;
				}
				else
				{
					us = new User("-1","-1", "-1", null);
					conn.close();
					return us;
				}
					
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	public User getUser(String username)
	{
		Connection conn;
		Statement stmt;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		User us;
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt= conn.createStatement();
			ResultSet rs=stmt.executeQuery("select *from shouzhang_user where user_name='"+username+"'");			
			while(rs.next()) {
				//System.out.print(rs.getString("password"));

					us=new User(rs.getString("user_name"),rs.getString("uid"),rs.getString("headPicSrc"),null);
					Friend_Sql fsql = new Friend_Sql();
					us.addAllFriend(fsql.getFriend(rs.getString("user_name")));
					conn.close();
					return us;

					
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	public boolean checkUser(String username) {
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
				rs.getString("user_name");
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login_sql r=new Login_sql();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
		System.out.println(r.checkUser("yhj"));
		System.out.println(r.checkUser("不存在"));
	}
}
	