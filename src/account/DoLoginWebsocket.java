package account;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DoLogin
 */


@WebServlet("/account/DoLoginWebsocket")
public class DoLoginWebsocket extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("有人登陆");
		String name = req.getParameter("uname");
		String password = req.getParameter("pwd");
		resp.setCharacterEncoding("utf-8");
		JudgeLogin aLogin = new Login_sql();
		byte[] abyte;
		User auser = null;
//		auser = aLogin.getUser(name, password);
//		System.out.println(auser.name+"  : "+auser.password);
		if(aLogin.checkUser(name))
		{
			System.out.println("用户名找到了"); 
//			User auser = null;
			auser = aLogin.getUser(name, password);
//			System.out.println(auser.getName()+"  : "+auser.getPassword());
			if(auser.getName().equals("-1"))
			{
				abyte = ("密码错误").getBytes("UTF-8");
				
				
			}
			else
			{
				
				//转发到cookies
				abyte = ("登陆成功").getBytes("UTF-8");
				SessionsManager.removeSession(name);

//				System.out.println(SessionsManager.viewSessions());
				resp.sendRedirect("http://localhost:8080/TravelApp/socket2.jsp");
			}
			
		}
		else
		{
			abyte = ("用户未找到").getBytes("UTF-8");
		}
		

		resp.getOutputStream().write(abyte);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
}
