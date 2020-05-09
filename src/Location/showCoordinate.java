package Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import account.User;

/**
 * Servlet implementation class showCoordinate
 */
@WebServlet("/location/showCoordinate")
public class showCoordinate extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		HttpSession se = request.getSession();
		User aUser = (User)se.getAttribute("user");
		
		if(aUser == null)
		{
//			response.getWriter().write("".getBytes("UTF-8"));
			response.setHeader("status", "-1");
		}
		else
		{	
			Coordinate_Sql c_sql = new Coordinate_Sql();
			
			ArrayList<Coordinate> coordinates = c_sql.showCoordinate(aUser.getName());
			System.out.println(coordinates);
			JSONArray jsonArray = new JSONArray();
			for(Coordinate c : coordinates)
			{
				Map<String,String> map = new HashMap<String,String>();
				map.put("latitude", c.latitude);
				map.put("longitude",c.longitude);
				map.put("time", c.time);

				jsonArray.put(map);
			}
			
			
			response.setHeader("status", "200");
			System.out.println(jsonArray.toString());
			response.getOutputStream().write(jsonArray.toString().getBytes("UTF-8"));
		}
	}

}
