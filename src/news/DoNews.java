package news;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class DoNews
 */
@WebServlet("/news/DoNews")
public class DoNews extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//设置请求头编码格式
		request.setCharacterEncoding("utf-8");
		//设置相应编码格式
		response.setContentType("text/html,charset=utf-8");
		JSONArray jsonArray = new JSONArray();
		//新闻json数组
		Artical_Sql artical_Sql = new Artical_Sql();
		
		int n = 10;//一次获取新闻条数
		Artical[] art = artical_Sql.get_Artical(n);
		for(int i = 0;i<n;i++)
		{
			Map<String,String> map = new HashMap<String,String>();
			map.put("descrption", art[i].descrption);
			map.put("picUrl", art[i].picUrl);
			map.put("time", art[i].time);
			map.put("title", art[i].title);
			map.put("url", art[i].url);
			jsonArray.put(map);
		}
		response.getOutputStream().write(jsonArray.toString().getBytes("UTF-8"));
	}


}
