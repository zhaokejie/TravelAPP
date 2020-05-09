package websocket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.User;

/**
 * Servlet implementation class getHeadPic
 */
@WebServlet("/websocket/getMesPic")
public class getMesPic extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession se = request.getSession(false);
		User aUser = (User)se.getAttribute("user");
		String picName = request.getHeader("picName");
		String name = aUser.getName();
		File pic = new File("C:\\TravelApp\\MesPic\\"+picName);
		
		FileInputStream fOut = new FileInputStream(pic); 
		int bytes;
		OutputStream resStream = response.getOutputStream();
		while((bytes = fOut.read())!=-1)
		{
			resStream.write(bytes);
		}
		fOut.close();
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession se = request.getSession(false);
		User aUser = (User)se.getAttribute("user");
		String picName = request.getHeader("picName");
		String name = aUser.getName();
		File pic = new File("C:\\TravelApp\\MesPic\\"+picName);
		
		FileInputStream fOut = new FileInputStream(pic); 
		int bytes;
		OutputStream resStream = response.getOutputStream();
		while((bytes = fOut.read())!=-1)
		{
			resStream.write(bytes);
		}
		fOut.close();
		
		
	}



}
