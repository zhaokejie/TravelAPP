package account;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoRegister
 */

@WebServlet("/account/DoRegister")
public class DoRegister extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("uname");
		String password = request.getParameter("pwd");
		String Email = request.getParameter("anEmail");
		request.setCharacterEncoding("utf-8");
		Register_Sql register = new Register_Sql();	
		if(register.haveUser(name))
		{
			System.out.println("用户名已使用");
			response.getOutputStream().write("用户名已使用".getBytes("UTF-8"));
		}
		else
		{
			String uid = java.util.UUID.randomUUID().toString();
			register.insertUser(name, password, Email,null);
			response.getOutputStream().write("注册成功".getBytes("UTF-8"));
			System.out.println("注册成功");
			
		}
	}

}
