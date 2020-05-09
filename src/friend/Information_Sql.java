package friend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;




 interface Infor_Sql {
	//将下列用户的消息存入其数据库中的未传输表,返回0表示存储成功，1表示失败
	public int addInformationtoSql(String uname,String fname,Information f);
	//将该用户的所有未传输数据提取出来，并存入已传输表
	public ArrayList<Information> getInformationFromSql(String uname);
	//提取已传输表中该用户的某type类型的数据
	public ArrayList<Information> getInformationFromIsReadSql(String uname,String type);
	//
	//{
//		type:"";//目前有好友请求，聊天消息两种，日后可能增加
//		time:"";
//		text:"";//text一般为json格式的数据
//		//存储时将是否已读分别存入两个表
	//}
}
 public class Information_Sql implements Infor_Sql{

	@Override
	public int addInformationtoSql(String uname,String fname, Information f) {
		// TODO Auto-generated method stub
		Connection conn;
		PreparedStatement stmt1;
		Statement stmt;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		String sql="insert into unread_message(`self_id`,`self_name`,`from_id`,`from_name`,`time`,`text`,`type`) values(?,?,?,?,?,?,?)";
		int uid = 0;		//用户的id
		int fid = 0;		//朋友的id
		//获取uid和fid
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt= conn.createStatement();
			ResultSet rs=stmt.executeQuery("select * from shouzhang_user where user_name='"+uname+"'");
			while(rs.next()) {
				uid = rs.getInt("uid");				
			}
			stmt = conn.createStatement();
			ResultSet rs1=stmt.executeQuery("select *from shouzhang_user where user_name='"+fname+"'");
			while(rs1.next()) {
				fid = rs1.getInt("uid");				
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
		//存入聊天纪录
				try {
					Class.forName(driver);
					conn=DriverManager.getConnection(url, mysql_user, mysql_password);
					stmt1=(PreparedStatement)conn.prepareStatement(sql);
					stmt1.setInt(1, uid);
					stmt1.setString(2, uname);
					stmt1.setInt(3, fid);
					stmt1.setString(4, fname);
					stmt1.setString(5, f.time);
					stmt1.setString(6, f.text);
					stmt1.setString(7, f.type);
					stmt1.executeUpdate();
					conn.close();
					return 0;
				}catch(ClassNotFoundException e) {
					e.printStackTrace();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				return 1;
		
	}

	@Override
	public ArrayList<Information> getInformationFromSql(String uname) {
		
		Connection conn;
		Statement stmt1;
		PreparedStatement stmt2;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		ArrayList<Information> li=new ArrayList<Information>();
		Information inf=new Information();
		String sql="insert into read_message(`self_id`,`self_name`,`from_id`,`from_name`,`time`,`text`,`type`) values(?,?,?,?,?,?,?)";
		ArrayList<Integer> list_self_id;
		ArrayList<Integer> list_from_id;
		
		// 提取未读纪录存入已读表
		try {
			list_self_id = new ArrayList<Integer>();
			list_from_id = new ArrayList<Integer>();
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt1= conn.createStatement();
			ResultSet rs=stmt1.executeQuery("select *from unread_message where self_name='"+uname+"'");
			while(rs.next()) {
				inf.uname=rs.getString("self_name");
				list_self_id.add(rs.getInt("self_id"));
				inf.fname=rs.getString("from_name");
				list_from_id.add(rs.getInt("from_id"));
				inf.time=rs.getString("time");
				inf.text=rs.getString("text");
				inf.type=rs.getString("type");			
				li.add(inf);				
			}
			stmt1.executeUpdate("delete from unread_message where self_name='"+uname+"'");
			stmt1.close();
			stmt2=(PreparedStatement)conn.prepareStatement(sql);
			for(int i=0;i<li.size();i++)
			{
				stmt2.setInt(1, list_self_id.get(i));
				stmt2.setString(2, li.get(i).uname);
				stmt2.setInt(3, list_from_id.get(i));
				stmt2.setString(4, li.get(i).fname);
				stmt2.setString(5, li.get(i).time);
				stmt2.setString(6, li.get(i).text);
				stmt2.setString(7, li.get(i).type);
				stmt2.executeUpdate();
			}						
			conn.close();
			return li;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return li;
		
	}

	@Override
	public ArrayList<Information> getInformationFromIsReadSql(String uname, String type) {
		// TODO Auto-generated method stub
		Connection conn;
		Statement stmt;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		ArrayList<Information> li=new ArrayList<Information>();
		Information inf=new Information();
		ArrayList<Integer> list_self_id;
		ArrayList<Integer> list_from_id;
		
		//按照type提取已读表中的信息
		try {
			list_self_id = new ArrayList<Integer>();
			list_from_id = new ArrayList<Integer>();
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt= conn.createStatement();
			ResultSet rs=stmt.executeQuery("select *from read_message where uname='"+uname+"' and type='"+type+"'");					
			while(rs.next()) {
				inf.uname=rs.getString("self_name");
				list_self_id.add(rs.getInt("self_id"));
				inf.fname=rs.getString("from_name");
				list_from_id.add(rs.getInt("from_id"));
				inf.time=rs.getString("time");
				inf.text=rs.getString("text");
				inf.type=rs.getString("type");			
				li.add(inf);							
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
	
//	public int saveReadInformation(String username, String time) {
//		// TODO Auto-generated method stub
//		
//		Connection conn;
//		Statement stmt1;
//		PreparedStatement stmt2;
//		String driver="com.mysql.jdbc.Driver";
//		String url="jdbc:mysql://47.103.66.24:3306/user"+username+"?useUnicode=true&characterEncoding=utf8";
//		String mysql_user="DaiMa";
//		String mysql_password="daima123456A";
//		String sql="insert into read_information(`time`,`text`,`type`) values(?,?,?)";
//		Information inf=new Information();
//		// 提取未读信息
//		try {
//			Class.forName(driver);
//			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
//			 stmt1= conn.createStatement();
//			ResultSet rs=stmt1.executeQuery("select *from unread_information where time='"+time+"'");
//			while(rs.next()) {
//				inf.time=rs.getString("time");
//				inf.text=rs.getString("text");
//				inf.type=rs.getString("type");											
//			}
//			stmt1.close();
//			conn.close();
//			
//		}catch(ClassNotFoundException e) {
//			e.printStackTrace();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		//存入已读表
//		try {
//			Class.forName(driver);
//			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
//			stmt2=(PreparedStatement)conn.prepareStatement(sql);
//			stmt2.setString(1, inf.time);
//			stmt2.setString(2, inf.text);
//			stmt2.setString(3, inf.type);
//			stmt2.executeUpdate();
//			stmt2.close();
//			conn.close();
//		}catch(ClassNotFoundException e) {
//			e.printStackTrace();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		//删除未读表的信息
//		try {
//		Class.forName(driver);
//		conn=DriverManager.getConnection(url, mysql_user, mysql_password);
//		stmt1= conn.createStatement();
//	    stmt1.executeUpdate("delete from unread_information where time='"+time+"'");	
//	    stmt1.close();
//		conn.close();
//	}catch(ClassNotFoundException e) {
//		e.printStackTrace();
//	}catch(SQLException e) {
//		e.printStackTrace();
//	}
//		
//		return 0;
//	} 
	public static void main(String[] args) {
//		// TODO Auto-generated method stub
		Information_Sql is=new Information_Sql();
		Information inf=new Information();
		inf.uname="yhj";
		inf.fname="zgy";
		inf.time="2019-01-01-12-12-01";
		inf.text="?????????";
		inf.type="文字";
		
		System.out.print(is.addInformationtoSql("yhj","zgy",inf));
		System.out.print(is.getInformationFromSql("yhj"));
		
		
	} 
 }



