package Location;

import java.io.IOException;
import account.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class addCooridinate
 */
@WebServlet("/location/addCooridinate")
public class addCooridinate extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession se = request.getSession(false);
		System.out.println("session是"+se);
		
		User aUser = (User)se.getAttribute("user");
		
		System.out.println("打卡的人是:"+se.getId());
		if(aUser == null)
		{
//			response.getWriter().write("".getBytes("UTF-8"));
			response.setHeader("status", "-1");
		}
		else
		{	
			
			Coordinate aCoordinate = new Coordinate();
			aCoordinate.latitude = request.getParameter("latitude");
			aCoordinate.longitude = request.getParameter("longitude");
			aCoordinate.time = request.getParameter("time");

			aCoordinate.title = request.getParameter("title");
			aCoordinate.address = request.getParameter("address");
			aCoordinate.typedes = request.getParameter("typedes");
			aCoordinate.typecode = request.getParameter("typecode");
			System.out.println(aCoordinate.latitude+aCoordinate.longitude+":"+aCoordinate.time);
			
			Coordinate_Sql c_sql = new Coordinate_Sql();
			c_sql.addACoordinate(aUser.getName(), aCoordinate);
			response.setHeader("status", "200");
		}
		
	}
	
	

}
