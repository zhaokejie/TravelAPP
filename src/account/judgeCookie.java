package account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tools.JiaMi;
import websocket.WebSocket;

/**
 * Servlet implementation class testGetCookie
 */
@WebServlet("/account/judgeCookie")
public class judgeCookie extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//设置请求头编码格式
				req.setCharacterEncoding("utf-8");
				//设置相应编码格式
				resp.setContentType("text/html,charset=utf-8");
				//获取请求头信息
					//获取Cookie信息
					System.out.println("有人获取cookie");
						Cookie[] cks= req.getCookies();
//						 try {
//				             byte[] decryResult = JiaMi.decrypt(, password);
//				             System.out.println("解密后："+new String(decryResult));
//						 	} catch (Exception e1) {
//				             e1.printStackTrace();
//				     }
						if(cks==null)
							resp.setHeader("ifLogin", "-1");
						if(cks!=null)
						{
							
							
							User auser;
							for(Cookie c:cks)
							{
								JudgeLogin aLogin = new Login_sql();
								String uname = c.getValue();
								if((auser = aLogin.getUser(uname))!=null)
									
								{
									resp.setHeader("uname", uname);
									SessionsManager.removeSession(uname);
									HttpSession hs = req.getSession();
									SessionsManager.addSession(uname, hs);
									resp.setHeader("ifLogin", "200");
									System.out.println(hs.getId());
									hs.setMaxInactiveInterval(1*60*60);
									if(WebSocket.getClients().containsKey(uname))
									{
										WebSocket.closeClient(uname);
										WebSocket.getClients().remove(uname);
									}
									System.out.println("现在的连接"+WebSocket.getClients());
										
							        
									hs.setAttribute("user", auser);
									System.out.println(SessionsManager.viewSessions());
									break;
								}
									
							}
						}
				//处理请求信息
				//响应处理结果
	}

}