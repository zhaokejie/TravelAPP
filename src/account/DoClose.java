package account;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import websocket.WebSocket;

/**
 * Servlet implementation class DoClose
 */
@WebServlet("/account/DoClose")
public class DoClose extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getHeader("uname");
		System.out.println(uname);
		if(SessionsManager.haveSession(uname))
			SessionsManager.removeSession(uname);
		
		System.out.println("现在的websocket连接:"+WebSocket.getClients());
		System.out.println("现在在的session连接："+SessionsManager.viewSessions());
		
		response.getOutputStream().write("123".getBytes("UTF-8"));
		
	}



}
