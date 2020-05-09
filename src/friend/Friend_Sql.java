package friend;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;






 interface myFriend_Sql {
	ArrayList<String> getFriend(String uname);
	//根据用户名得到该用户的好友用户名列表
	int sendAddFriend(String uname,String fname);
	//根据用户名以及好友用户名，在好友消息表记录好友请求，若已经是好友返回0，若记录好友请求成返回1
	int addFriend(String uname,String fname);
	//ͬ同意好友请求后将用户互相放到对方的好友列表中，且在好友添加请求列表中删除请求，成功返回1
	int removeAddfriend(String uname,String fname);
	//若不同意好友请求，直接在好友添加请求列表中删除请求
	int getState_num(String fname);
	//查询state_num，返回不是0，返回时0表示错误
	
}
 //数据库state_num若为1表示已是好友，2表示正在好友申请中，3表示好友申请被拒绝


 public class Friend_Sql implements myFriend_Sql
{

	@Override
	public ArrayList<String> getFriend(String uname) {
		Connection conn;
		Statement stmt;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		ArrayList<String> li=new ArrayList<String>();
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt= conn.createStatement();
			ResultSet rs=stmt.executeQuery("select *from user_friendship where self_name='"+uname+"'");
			while(rs.next()) {
				li.add(rs.getString("friend_name"));				
			}
			return li;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	
	@Override
	public int sendAddFriend(String uname, String fname) {
		Connection conn;
		PreparedStatement stmt2;
		Statement stmt1;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		String sql="insert into user_friendship(`self_id`,`self_name`,`friend_id`,`friend_name`,`state_num`) values(?,?,?,?,?)";
		ArrayList<String> friend_list;
		int uid = 0;		//用户的id
		int fid = 0;		//朋友的id
		//获取uid和fid
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt1= conn.createStatement();
			ResultSet rs=stmt1.executeQuery("select * from shouzhang_user where user_name='"+uname+"'");
			while(rs.next()) {
				uid = rs.getInt("uid");				
			}
			stmt1 = conn.createStatement();
			ResultSet rs1=stmt1.executeQuery("select *from shouzhang_user where user_name='"+fname+"'");
			while(rs1.next()) {
				fid = rs1.getInt("uid");				
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
		friend_list = this.getFriend(uname);
		if(!friend_list.contains(fname))
		{
			//将好友申请储存到friend表
			try {
				Class.forName(driver);
				conn=DriverManager.getConnection(url, mysql_user, mysql_password);
				stmt2=(PreparedStatement)conn.prepareStatement(sql);
				stmt2.setInt(1, uid);
				stmt2.setString(2, uname);
				stmt2.setInt(3, fid);
				stmt2.setString(4, fname);
				stmt2.setInt(5, 2);//state_num为2表示为好友申请
				stmt2.executeUpdate();
				conn.close();
				return 1;
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else 
		{
			return 0;
		}
		return 3;
	}

	@Override
	public int addFriend(String uname, String fname) {
		// TODO Auto-generated method stub
		Connection conn;
		PreparedStatement stmt3;
		PreparedStatement stmt2;
		Statement stmt1;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		String sql1="insert into user_friendship(`self_id`,`self_name`,`friend_id`,`friend_name`,`state_num`) values(?,?,?,?,?)";
		//String sql2="update user_friendship set state_num= '1' where self_name='"+uname+"'and friend_name='"+fname+"'";
		int uid = 0;		//用户的id
		int fid = 0;		//朋友的id
		//获取uid和fid
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt1= conn.createStatement();
			ResultSet rs=stmt1.executeQuery("select *from shouzhang_user where user_name='"+uname+"'");
			while(rs.next()) {
				uid = rs.getInt("uid");				
			}
			rs=stmt1.executeQuery("select *from shouzhang_user where user_name='"+fname+"'");
			while(rs.next()) {
				fid = rs.getInt("uid");				
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
		//更改状态码，并相应插入对应的friendship字段
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			stmt2=(PreparedStatement)conn.prepareStatement(sql1);
			stmt2.setInt(1, uid);
			stmt2.setString(2, uname);
			stmt2.setInt(3, fid);
			stmt2.setString(4, fname);
			stmt2.setInt(5, 1);//state_num为1表示成为好友
			stmt2.executeUpdate();
			stmt2.close();
			
			stmt3=(PreparedStatement)conn.prepareStatement(sql1);
			stmt3.setInt(1, fid);
			stmt3.setString(2, fname);
			stmt3.setInt(3, uid);
			stmt3.setString(4, uname);
			stmt3.setInt(5, 1);//state_num为1表示成为好友
			stmt3.executeUpdate();
			stmt3.close();
			conn.close();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int removeAddfriend(String uname, String fname) {
		// 将state_num改成3
		Connection conn;
		PreparedStatement stmt2;
		Statement stmt1;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		String sql="update user_friendship set state_num = '3' where self_name='"+uname+"' and friend_name='"+fname+"'";
		int uid = 0;		//用户的id
		int fid = 0;		//朋友的id
		//获取uid和fid
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt1= conn.createStatement();
			ResultSet rs=stmt1.executeQuery("select *from shouzhang_user where user_name='"+uname+"'");
			while(rs.next()) {
				uid = rs.getInt("uid");				
			}
			rs=stmt1.executeQuery("select *from shouzhang_user where user_name='"+fname+"'");
			while(rs.next()) {
				fid = rs.getInt("uid");				
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
		//改变state_num
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			stmt2=(PreparedStatement)conn.prepareStatement(sql);
			stmt2.executeUpdate();
			return 1;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int getState_num(String fname) {
		// 通过fname查询对应的state_num
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
			ResultSet rs=stmt.executeQuery("select *from user_friendship where friend_name='"+fname+"'");					
			while(rs.next()) {
				return rs.getInt("state_num");				
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return 0;
	}
	
	public static void main(String[] args)
	{
		Friend_Sql f = new Friend_Sql();
//		f.sendAddFriend("yhj", "zgy");
//		f.addFriend("yhj", "zgy");
		f.removeAddfriend("yhj", "zgy");
	}
	
}