package friend;

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


import account.User;

/**
 * Servlet implementation class DoGetFriend
 */
@WebServlet("/DoGetFriend")
public class DoGetFriend extends HttpServlet {
	
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
			JSONArray jsonArray = new JSONArray(aUser.getAllFriend());
			response.setHeader("status", "200");
			System.out.println(jsonArray.toString());
			response.getOutputStream().write(jsonArray.toString().getBytes("UTF-8"));
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se = request.getSession();
		User aUser = (User)se.getAttribute("user");
		if(aUser == null)
		{
//			response.getWriter().write("".getBytes("UTF-8"));
			response.setHeader("status", "-1");
		}
		else
		{	
			JSONArray jsonArray = new JSONArray(aUser.getAllFriend());
			response.setHeader("status", "200");
			System.out.println(jsonArray.toString());
			response.getOutputStream().write(jsonArray.toString().getBytes("UTF-8"));
		}
	}

}
