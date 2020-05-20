package Location;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class Coordinate_Sql {
	int addACoordinate(String username,Coordinate c)//返回0表示存储成功，1表示失败
	{
		
		Connection conn;
		PreparedStatement stmt2;
		Statement stmt1;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		int user_id=0;
		String sql="insert into location_data(`uid`,`longitude`,`latitude`,`time`,`poiName`,`address`,`typedes`,`typecode`) values(?,?,?,?,?,?,?,?)";
		//从user表得到id
				try {
					Class.forName(driver);
					conn=DriverManager.getConnection(url, mysql_user, mysql_password);
					 stmt1= conn.createStatement();
					ResultSet rs=stmt1.executeQuery("select *from shouzhang_user where user_name='"+username+"'");					
					while(rs.next()) {
						user_id=rs.getInt("uid");			
					}
				}catch(ClassNotFoundException e) {
					e.printStackTrace();
				}catch(SQLException e) {
					e.printStackTrace();
				}
		//存入
				try {
					Class.forName(driver);
					conn=DriverManager.getConnection(url, mysql_user, mysql_password);
					stmt2=(PreparedStatement)conn.prepareStatement(sql);
					stmt2.setInt(1, user_id);
					stmt2.setString(2, c.longitude);
					stmt2.setString(3, c.latitude);
					stmt2.setString(4, c.time);
					stmt2.setString(5, c.title);
					stmt2.setString(6, c.address);
					stmt2.setString(7, c.typedes);
					stmt2.setString(8, c.typecode);
					stmt2.executeUpdate();
					return 0;
				}catch(ClassNotFoundException e) {
					e.printStackTrace();
				}catch(SQLException e) {
					e.printStackTrace();
				}
				return 1;
	}
	
	ArrayList<Coordinate> showCoordinate(String username)
	{
		Connection conn;
		Statement stmt;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		ArrayList<Coordinate> li=new ArrayList<Coordinate>();
		int user_id=0;
		
		//从user表中获取id
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt= conn.createStatement();
			ResultSet rs=stmt.executeQuery("select *from shouzhang_user where user_name='"+username+"'");					
			while(rs.next()) {
				user_id=rs.getInt("uid");		
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			 Class.forName(driver);
			 conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt= conn.createStatement();

			 ResultSet rs=stmt.executeQuery("select *from location_data where uid='"+user_id+"' order by time desc limit 0,9");
			 System.out.println("1-----------------");
			 while(rs.next()) {
				Coordinate coor=new Coordinate();
				 coor.longitude=rs.getString("longitude");
				 coor.latitude=rs.getString("latitude");
				 coor.time=rs.getString("time");
				 coor.title=rs.getString("poiName");
				 coor.address=rs.getString("address");
				 coor.rating=rs.getInt("rating");
				 coor.typedes=rs.getString("typedes");
				 coor.typecode=rs.getString("typecode");
				System.out.println(coor.time);
				li.add(coor);	
			}
			conn.close();
			System.out.println("2-----------------------------------");
			for(Coordinate c:li)
			{
				System.out.println(c.time);
			}
			System.out.println("3-----------------");
			return li;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}		

		return null;
	}
	
	ArrayList<Coordinate> showAllCoordinate()
	{
		Connection conn;
		Statement stmt;
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		ArrayList<Coordinate> li=new ArrayList<Coordinate>();
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, mysql_user, mysql_password);
			 stmt= conn.createStatement();
			
			 ResultSet rs=stmt.executeQuery("select *from location_data");
			 System.out.println("1-----------------");
			 while(rs.next()) {
				Coordinate coor=new Coordinate();
				 coor.longitude=rs.getString("longitude");
				 coor.latitude=rs.getString("latitude");
				 coor.time=rs.getString("time");
				 coor.title=rs.getString("poiName");
				 coor.address=rs.getString("address");
				 coor.rating=rs.getInt("rating");
				 coor.typedes=rs.getString("typedes");
				 coor.typecode=rs.getString("typecode");
				System.out.println(coor.time);
				li.add(coor);	
			}
			conn.close();
			System.out.println("2-----------------------------------\n\n\n");
			for(Coordinate c:li)
			{
				System.out.println(c.time);
			}
			System.out.println("3-----------------");
			return li;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Coordinate_Sql cs=new Coordinate_Sql();
////		Coordinate coor=new Coordinate("123","321","2019-01-01");
////		System.out.println(cs.addACoordinate("123", coor));
////		System.out.println(cs.showCoordinate("123").get(0).longitude);
////		System.out.println(cs.showCoordinate("123").get(1).longitude);
////		System.out.println(cs.showCoordinate("123").get(2).longitude);
//		ArrayList<Coordinate> li = cs.showAllCoordinate();
//		for(int i =0;i<li.size();i++)
//		{
//			System.out.println(li.get(i).time);
//		}
//	}

}
