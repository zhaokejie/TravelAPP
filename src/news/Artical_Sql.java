package news;
import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.HashMap;
import java.sql.Timestamp;

class Artical{
	String time;//文章发布时间	
	String title;//文章标题	
	String descrption;//文章类型	
	String picUrl;//标题图片url	
	String url;//正文url
public Artical(String time,String t,String d,String p,String u)
{
	this.descrption=d;
	this.picUrl=p;
	this.time=time;
	this.title=t;
	this.url=u;
}		
}
public class Artical_Sql {

	
	
	public Artical[] get_Artical(int n)//获取n条新闻
	{
		Connection conn;
		Statement stmt;
		String driver="com.mysql.jdbc.Driver";
		String sql="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		Artical[] art = new Artical[n];
		int i=0;
		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(sql, mysql_user, mysql_password);
			 stmt= conn.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT DISTINCT `time`,`title`,`description`,`picUrl`,`url` FROM `artical` ORDER BY RAND() LIMIT "+n);			
			while(rs.next()) {				
					art[i]=new Artical(rs.getTimestamp("time").toLocaleString(),rs.getString("title"),rs.getString("description"),rs.getString("picUrl"),rs.getString("url"));
					++i;
				}
			conn.close();
			return art;
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
//	public Map[] trans_Map(Artical[] art,int n)
//	{
//		Map<String,String>[] map=new HashMap<String,String>[];
//		for(int i=0;i<n;i++)
//		{
//			
//		}
//		return null;
		
	
	public int insert_Artical(Artical art) throws ParseException//插入一条art新闻
	{
		Connection conn;
		PreparedStatement stmt;
		Statement stmt1;
		String driver="com.mysql.jdbc.Driver";
		String sql="jdbc:mysql://47.103.66.24:3306/shouzhang?useUnicode=true&characterEncoding=utf8";
		String mysql_user="DaiMa";
		String mysql_password="daima123456A";
		String sql1="insert into artical  values(?,?,?,?,?)";
		Timestamp times=Timestamp.valueOf(art.time);//将字符串转换成时间类
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(sql, mysql_user, mysql_password);
			 stmt1= conn.createStatement();
			ResultSet rs=stmt1.executeQuery("select *from artical where title='"+art.title+"'");			
			while(rs.next()) {
				//System.out.print(rs.getString("password"));
				if(rs.getString("url").equals(art.url))
				{
					art=new Artical(rs.getString("time"),rs.getString("title"),rs.getString("description"),rs.getString("picUrl"),rs.getString("url"));
					return 2;
				}
			}
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(sql, mysql_user, mysql_password);
			stmt=(PreparedStatement)conn.prepareStatement(sql1);
			stmt.setTimestamp(1, times);
			stmt.setString(2, art.title);
			stmt.setString(3, art.descrption);
			stmt.setString(4, art.picUrl);
			stmt.setString(5, art.url);
			stmt.executeUpdate();
			return 1;
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Artical_Sql a=new Artical_Sql();	
		for(int j=0;j<10;j++)
		{
			System.out.print(a.get_Artical(10)[j].title);
			System.out.print("\r\n");
		}
		

	}
}


