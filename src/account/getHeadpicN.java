package account;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class getHeadpicN
 */
@WebServlet("/account/getHeadpicN")
public class getHeadpicN extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String name = request.getHeader("uname");
		File pic = new File("C:\\TravelApp\\FileAc\\"+name+"\\headPic.jpg");
		
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
