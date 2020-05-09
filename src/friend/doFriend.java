package friend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.User;

/**
 * Servlet implementation class doFriend
 */
@WebServlet("/friend/doFriend")
public class doFriend extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ifAdd = request.getHeader("ifAdd");
		String who = request.getHeader("who");
		String time = request.getHeader("time");
		Information_Sql infSql = new Information_Sql();
		Friend_Sql friSql = new Friend_Sql();
		HttpSession se = request.getSession(false);
		User aUser = (User)se.getAttribute("user");
		System.out.println("doFriend");
		if(ifAdd.equals("1"))
		{
			friSql.addFriend(aUser.getName(), who);
			System.out.println(aUser.getName());
//			infSql.saveReadInformation(aUser.getName(), time);
			//存储已读的好友请求
		}
		else if(ifAdd.equals("0"))
		{
//			infSql.saveReadInformation(aUser.getName(), time);
			//存储已读的好友请求
		}
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ifAdd = request.getHeader("ifAdd");
		String who = request.getHeader("who");
		String time = request.getHeader("time");
		Information_Sql infSql = new Information_Sql();
		Friend_Sql friSql = new Friend_Sql();
		HttpSession se = request.getSession(false);
		User aUser = (User)se.getAttribute("user");
		System.out.println("doFriend");
		ifAdd = "1";
		who = "123";
		
		if(ifAdd.equals("1"))
		{
			friSql.addFriend(aUser.getName(), who);
			System.out.println(aUser.getName());
//			infSql.saveReadInformation(aUser.getName(), time);
			//存储已读的好友请求
		}
		else if(ifAdd.equals("0"))
		{
//			infSql.saveReadInformation(aUser.getName(), time);
			//存储已读的好友请求
		}
		
		
	}
}
