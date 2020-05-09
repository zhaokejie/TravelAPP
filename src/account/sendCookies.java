package account;

import java.io.IOException;
import tools.JiaMi;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.JiaMi;

/**
 * Servlet implementation class testCookies
 */
@WebServlet("/account/sendCookies")
public class sendCookies extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置请求头编码格式
		req.setCharacterEncoding("utf-8");
		//设置相应编码格式
		resp.setContentType("text/html,charset=utf-8");
		//获取请求头信息
		//处理请求信息
		//响应处理结果
			//使用cookie进行浏览器端的数据储存
				//创建cookie对象
				String uname = req.getParameter("uname");
//				String passkey = "9588028820109132570743325311898426347857298773549468758875018579537757772163084478873699447306034466200616411960574122434059469100235892702736860872901247123456";
//		
//				byte[] result = JiaMi.encrypt(uname.getBytes(),passkey);
//				System.out.println(new String(result));
				if(uname!=null)
				{
					Cookie c = new Cookie("uname",uname);

					//创建cookie对象
						//设置有效期
						c.setMaxAge(3600);
						//设置有效路径
						c.setPath("/TravelApp");
					//响应cookie对象
						resp.addCookie(c);
				}
				byte[] abyte = ("登陆成功").getBytes("UTF-8");
				resp.getOutputStream().write(abyte);
				
				
		//cookie特点：存储在服务器端，临时存储，浏览器关闭即消失
				
	}

}
