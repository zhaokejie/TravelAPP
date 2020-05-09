package account;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.mail.util.MailSSLSocketFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DoRegister
 */

@WebServlet("/account/DoEmail")
public class DoEmail extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Register_Sql register = new Register_Sql();
		String anEmail = request.getParameter("anEmail");
		request.setCharacterEncoding("utf-8");
		if(register.ifUsedEmail(anEmail) == 0)
		{
			System.out.println("发送成功");
			String result = sendEmail(anEmail);
			response.getOutputStream().write(result.getBytes("UTF-8"));
		}
		else
		{
			System.out.println("邮箱已注册");
			String result = "已注册";
			response.getOutputStream().write(result.getBytes("UTF-8"));
		}
			
    }
	
	public static String sendEmail(String anEmail)
	{
		String result="";
        try {
        	
        	for(int i=0;i<6;i++){
	        	//生成97-122的int型的整型
	        	int intValue=(int)(Math.random()*26+97);
	        	//将intValue强制转化成char类型后接到result后面
	        	result=result+(char)intValue;
        	}
            Properties props = new Properties();
 
            // 开启debug调
//            props.setProperty("mail.debug", "true");
            // 发送服务器需要身份验证
            props.setProperty("mail.smtp.auth", "true");
            // 设置邮件服务器主机名
            props.setProperty("mail.host", "smtp.qq.com");
            // props.setProperty("mail.port", "465");
            // 发送邮件协议名称
            props.setProperty("mail.transport.protocol", "smtp");
 
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.ssl.socketFactory", sf);
 
            Session session = Session.getInstance(props);
 
            Message msg = new MimeMessage(session);
            msg.setSubject("请确认您的操作");
            StringBuilder builder = new StringBuilder();
            //            builder.append("url = " + "http://blog.csdn.net/never_cxb/article/details/50524571");
//            builder.append("这是一条来自travel手账app的验证消息由于您正在执行某些操作，我们需要验证是否为您本人操作,您的验证码为："+result);
//            builder.append("\n时间 " + new Date());
//            msg.setText("您的数字为："+result);
            String text = "<!DOCTYPE html>\r\n" + 
            		"<html>\r\n" + 
            		"<head>\r\n" + 
            		"    <mata charset=\"utf-8\">\r\n" + 
            		"<style>\r\n" + 
            		"\r\n" + 
            		"</style>\r\n" + 
            		"</head>\r\n" + 
            		"<body>\r\n" + 
            		"    <table style=\"width: 538px;\r\n" + 
            		"    background-color: #393836;\" \r\n" + 
            		"    align=\"center\" \r\n" + 
            		"    cellspacing=\"0\" \r\n" + 
            		"    cellpadding=\"0\">\r\n" + 
            		"    <tbody>\r\n" + 
            		"        <tr>\r\n" + 
            		"            <td style=\"height: 65px;\r\n" + 
            		"            background-color:#000000;\r\n" + 
            		"            border-bottom: 1px solid #4d4b48;\">\r\n" + 
            		"            <p style=\"color: white;\r\n" + 
            		"            font-size: 20px;\r\n" + 
            		"            font-weight: bold;\r\n" + 
            		"            padding-left: 40px;\">旅行手账</p>\r\n" + 
            		"            </td>\r\n" + 
            		"        </tr>\r\n" + 
            		"        <tr>\r\n" + 
            		"            <td bgcolor=\"#17212e\">\r\n" + 
            		"                <table width=\"470\"\r\n" + 
            		"                border=\"0\"\r\n" + 
            		"                align=\"center\"\r\n" + 
            		"                cellpadding=\"0\"\r\n" + 
            		"                cellspacing=\"0\"\r\n" + 
            		"                style=\"padding-left: 5px;\r\n" + 
            		"                padding-right:5px;\r\n" + 
            		"                padding-bottom:10px;\">\r\n" + 
            		"                <tbody>\r\n" + 
            		"                    <tr bgcolor=\"#17212e\">\r\n" + 
            		"                        <td style=\"padding-top: 32px;\">\r\n" + 
            		"                            <span style=\"padding-top: 16px; \r\n" + 
            		"                            padding-bottom: 16px; \r\n" + 
            		"                            font-size: 24px; \r\n" + 
            		"                            color: #66c0f4; \r\n" + 
            		"                            font-family: Arial, Helvetica, sans-serif; \r\n" + 
            		"                            font-weight: bold;\">\r\n" + 
            		"                            您好，<br><br>\r\n" + 
            		"                            即将完成！\r\n" + 
            		"                            </span>  \r\n" + 
            		"                        <br>     \r\n" + 
            		"                        </td>\r\n" + 
            		"                    </tr>\r\n" + 
            		"                    <tr>\r\n" + 
            		"                        <td style=\"padding-top: 12px;\">\r\n" + 
            		"                        <span style=\"font-size: 17px; \r\n" + 
            		"                        color: #c6d4df; \r\n" + 
            		"                        font-family: Arial, Helvetica, sans-serif; \r\n" + 
            		"                        font-weight: bold;\">\r\n" + 
            		"                        <p>\r\n" + 
            		"                            以下是您注册账号所需的验证码："+result+
            		"                       </p>\r\n" + 
            		"                        </span>\r\n" + 
            		"                    </td>\r\n" + 
            		"                    </tr>\r\n" + 
            		"                    <tr>\r\n" + 
            		"                        <td>\r\n" + 
            		"                            <div>\r\n" + 
            		"                                <span style=\"font-size: 24px; \r\n" + 
            		"                                color: #66c0f4; \r\n" + 
            		"                                font-family: Arial, Helvetica, sans-serif; \r\n" + 
            		"                                font-weight: bold;\">\r\n" + 
            		"                                \r\n" + 
            		"                                </span>\r\n" + 
            		"                            </div>\r\n" + 
            		"                        </td>\r\n" + 
            		"                    </tr>\r\n" + 
            		"                    <tr bgcolor=\"#121a25\">\r\n" + 
            		"                        <td style=\"padding: 20px; \r\n" + 
            		"                        font-size: 12px; \r\n" + 
            		"                        line-height: 17px; \r\n" + 
            		"                        color: #c6d4df; \r\n" + 
            		"                        font-family: Arial, Helvetica, sans-serif;\">\r\n" + 
            		"                        <p style=\"padding-bottom: 10px; \r\n" + 
            		"                        color: #c6d4df;\">\r\n" + 
            		"                        这封邮件由旅行手账官方发送，这是注册的最后一步。\r\n" + 
            		"                        </p>\r\n" + 
            		"                        <p style=\"padding-bottom: 10px; \r\n" + 
            		"                        color: #c6d4df;\">\r\n" + 
            		"                        若您未注册过，请无视此封邮件，并确保您邮箱的安全性。\r\n" + 
            		"                        </p>\r\n" + 
            		"                        <p style=\"padding-bottom: 10px; \r\n" + 
            		"                        color: #c6d4df;\">\r\n" + 
            		"                        旅行手账欢迎您的加入。\r\n" + 
            		"                        </p>\r\n" + 
            		"                        <p style=\"padding-bottom: 10px; \r\n" + 
            		"                        color: #c6d4df;\"\r\n" + 
            		"                        align=\"right\">\r\n" + 
            		"                        -旅行手账团队\r\n" + 
            		"                        </p>\r\n" + 
            		"                        </td>\r\n" + 
            		"                    </tr>\r\n" + 
            		"                </tbody>\r\n" + 
            		"                </table>\r\n" + 
            		"            </td>\r\n" + 
            		"        </tr>\r\n" + 
            		"        <tr>\r\n" + 
            		"                <td bgcolor=\"#000000\">\r\n" + 
            		"                    <table width=\"460\"\r\n" + 
            		"                    height=\"55\"\r\n" + 
            		"                    border=\"0\"\r\n" + 
            		"                    align=\"center\"\r\n" + 
            		"                    cellpadding=\"0\"\r\n" + 
            		"                    cellspacing=\"0\">\r\n" + 
            		"                    <tbody>\r\n" + 
            		"                        <tr valign=\"top\">\r\n" + 
            		"                            <td width=\"350\">\r\n" + 
            		"                        <span style=\"color: #999999; \r\n" + 
            		"                        font-size: 9px;\r\n" + 
            		"                        font-family: Verdana, Arial, Helvetica, sans-serif;\"\r\n" + 
            		"                        align=\"center\">\r\n" + 
            		"                        旅行手账官方保留软件所有权。\r\n" + 
            		"                        </span></td>\r\n" + 
            		"                        </tr>\r\n" + 
            		"                    </tbody>\r\n" + 
            		"                </table>\r\n" + 
            		"                </td>\r\n" + 
            		"            </tr>\r\n" + 
            		"    </tbody>\r\n" + 
            		"</body>\r\n" + 
            		"</html>";
//            msg.setText(text);
            msg.setContent(text,"text/html;charset=utf-8");
            msg.setFrom(new InternetAddress("2793118771@qq.com"));
    
            Transport transport = session.getTransport();
            transport.connect("smtp.qq.com", "2793118771@qq.com", "jqgtsbkkidrsddij");
//            System.out.println("hehe"+anEmail);
            transport.sendMessage(msg, new Address[] { new InternetAddress(anEmail) });
            System.out.println("hehheeh");
            transport.close();
            
            
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return result;
 
		
	}

}


