package Location;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import testWeather.Weather;;
/**
 * Servlet implementation class doWeather
 */
@WebServlet("/location/DoWeather")
public class DoWeather extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("查询天气");
		int cityCode = Integer.parseInt(request.getParameter("cityCode"));
		response.setCharacterEncoding("utf-8");
		Weather weather = new Weather();
		response.getOutputStream().write(weather.getWeather(cityCode).getBytes("utf-8"));
		
		
	}

}
