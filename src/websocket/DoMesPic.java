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
		System.out.println("�洢��Ϣ��Ƭ");
		HttpSession se = request.getSession(false);
		
		User aUser = (User)se.getAttribute("user");
		
		String name = aUser.getName();
		System.out.println(name+"������Ϣ��Ƭ");
		String picName = request.getHeader("picName");
//		File FilePic = new File("C:\\TravelApp\\FileAc\\"+name);
		File pic = new File("C:\\TravelApp\\MesPic\\"+picName);
		

//		FilePic.mkdirs();
		if(!pic.exists())
		{
			System.out.println("�ļ��������Ѵ���");
			pic.createNewFile();
		}

		FileOutputStream fOut = new FileOutputStream(pic); 
		int bytes;
		System.out.println("�洢��Ϣ��Ƭ");
		while((bytes = request.getInputStream().read())!=-1)
		{
			fOut.write(bytes);
		}
		
		fOut.close();
		
		
	}

}
