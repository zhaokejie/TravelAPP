package Location;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import account.User;

/**
 * Servlet implementation class addCooridinateH
 */
@WebServlet("/location/addCooridinateH")
public class addCooridinateH extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		HttpSession se = request.getSession(false);
//		System.out.println("session是"+se);
//		
//		User aUser = (User)se.getAttribute("user");
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uname = request.getHeader("uname");
		System.out.println("打卡的人是:"+uname);
		
		
		//测试部分
		byte[] test = new byte[1000];
		request.getInputStream().read(test);
		String jsonS = new String (test,"utf-8");
		System.out.println("流是："+jsonS);
		jsonS = jsonS.replaceAll("<", "{").replaceAll(">", "}");
		
		
		
		
		
		if(uname == null)
		{
//			response.getWriter().write("".getBytes("UTF-8"));
			response.setHeader("status", "-1");
		}
		else
		{	
			Coordinate aCoordinate = new Coordinate();
			JSONObject mess;
			try {
				mess = new JSONObject(jsonS);
				aCoordinate.address = mess.getString("address");
				aCoordinate.latitude = mess.getString("latitude");
				aCoordinate.longitude = mess.getString("longitude");
				aCoordinate.title = mess.getString("title");
				aCoordinate.typedes = mess.getString("typedes");
				aCoordinate.typecode = mess.getString("typecode");
						
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
//			aCoordinate.time = request.getParameter("time");
//			System.out.println(request.getParameter("time"));
//			Long time = new Long(request.getParameter("time"));
//			System.out.println(time);
			
			
			//现行版本中使用的是服务器生成时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
			String times = sdf.format(new Date());
			System.out.println("now is "+times);
			
			aCoordinate.time = times;

			System.out.println(aCoordinate.latitude+aCoordinate.longitude+":"+aCoordinate.time);
			
			
			
			Coordinate_Sql c_sql = new Coordinate_Sql();
			c_sql.addACoordinate(uname, aCoordinate);
			response.setHeader("status", "200");
		}
		
	}
	

}
