package friend;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

//import account.JudgeLogin;
import account.Login_sql;
import account.User;;

/**
 * Servlet implementation class SearchUser
 */
@WebServlet("/friend/SearchUser")
public class SearchUser extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("uname");
//		String password = req.getParameter("pwd");
		resp.setCharacterEncoding("utf-8");
		Login_sql aLogin = new Login_sql();
		User user = aLogin.getUser(name);
		if(user!=null)
		{
			Map<String,String> u = new HashMap<String,String>();
			u.put("uname", user.getName());
			u.put("headpicUrl", user.getheadPicSrc());
//			u.put("friends", user.getAllFriend());
			JSONObject userJson = new JSONObject(u);
			resp.getOutputStream().write(userJson.toString().getBytes("UTF-8"));
		}
		else
			resp.getOutputStream().write("用户未找到".getBytes("UTF-8"));
	}
	

}
