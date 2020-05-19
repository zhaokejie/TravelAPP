package Location;

import account.User;
import tools.ControlPython;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "getRecommend",value = "/location/getRecommend")
public class getRecommend extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置编码格式
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //从session 中拿用户的名字
        HttpSession se = request.getSession(false);
        User aUser = (User)se.getAttribute("user");

        System.out.println("推荐的人是:"+aUser.getName());

        //从服务器获取该用户的打卡记录
        ArrayList<Coordinate> list = new Coordinate_Sql().showCoordinate(aUser.getName());

        //生成用户的推荐所需参数
        ControlPython pyTools = new ControlPython();
        String args = pyTools.buildArgs(aUser.getName(), list);

        //调用python 程序进行推荐

        String recommends = pyTools.getPythonDemo("C:\\TravelApp\\recommended\\ItemCF\\Recommend.py",args);

        response.getOutputStream().write(recommends.getBytes("UTF-8"));




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //从session 中拿用户的名字
        HttpSession se = request.getSession(false);
        User aUser = (User)se.getAttribute("user");

        System.out.println("推荐的人是:"+aUser.getName());

        //从服务器获取该用户的打卡记录
        ArrayList<Coordinate> list = new Coordinate_Sql().showCoordinate(aUser.getName());

        //生成用户的推荐所需参数
        ControlPython pyTools = new ControlPython();
        String args = pyTools.buildArgs(aUser.getName(), list);

        //调用python 程序进行推荐

        String recommends = pyTools.getPythonDemo("C:\\TravelApp\\recommended\\ItemCF\\Recommend.py",args);

        response.getOutputStream().write(recommends.getBytes("UTF-8"));

    }
}
