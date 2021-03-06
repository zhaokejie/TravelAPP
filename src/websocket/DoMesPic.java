package websocket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import account.User;

/**
 * Servlet implementation class DoHeadPic
 */
@WebServlet("/websocket/DoMesPic")
public class DoMesPic extends HttpServlet {
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("存储消息照片");
		HttpSession se = request.getSession(false);
		
		User aUser = (User)se.getAttribute("user");
		
		String name = aUser.getName();
		System.out.println(name+"储存消息照片");
		String picName = request.getHeader("picName");
//		File FilePic = new File("C:\\TravelApp\\FileAc\\"+name);
		File pic = new File("C:\\TravelApp\\MesPic\\"+picName);
		

//		FilePic.mkdirs();
		if(!pic.exists())
		{
			System.out.println("文件不存在已创建");
			pic.createNewFile();
		}

		FileOutputStream fOut = new FileOutputStream(pic); 
		int bytes;
		System.out.println("存储消息照片");
		while((bytes = request.getInputStream().read())!=-1)
		{
			fOut.write(bytes);
		}
		
		fOut.close();
		
		
	}

}
