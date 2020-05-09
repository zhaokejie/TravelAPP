package websocket;  
  
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;  
import javax.websocket.*;  
import javax.websocket.server.PathParam;  
import javax.websocket.server.ServerEndpoint;  
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject; 
import friend.*;
@ServerEndpoint("/websocket/{username}")  
public class WebSocket {  
  
    private static int onlineCount = 0;  
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();  
    private Session session;  
    private String username;  
      
    @OnOpen  
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {  
  
        this.username = username;  
        this.session = session;  
          
        addOnlineCount();  
        clients.put(username, this);  
        System.out.println("已连接"+username);  
//        this.onMessage("hello");
        Information_Sql infSql = new Information_Sql();
        
        ArrayList<Information> infs = infSql.getInformationFromSql(username);
        System.out.println("将好友请求提出数据库");
        if(!infs.isEmpty())
        {
        	for(Information infa : infs)
            {
            	
            	JSONObject mess;
    			try {
    				mess = new JSONObject();
    				mess.put("type", infa.type);
    				mess.put("time", infa.time);
    				JSONObject text = new JSONObject(infa.text);
    				mess.put("text", text);
//    				JSONObject text = new JSONObject(mess.getJSONObject("text").toString());
//    				String fUname = text.getString("from");
//    				String tUname = text.getString("to");
    				sendMessageToTong(mess.toString(),username);
//    	     		System.out.println(fUname+" add "+tUname);
    			} catch (JSONException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            	
            	
            }
        }
        System.out.println("结束for循环："+WebSocket.getClients());
       
    }  
  
    @OnClose  
    public void onClose() throws IOException {  
        clients.remove(username);  
        subOnlineCount();  
    }  
  
    @OnMessage  
    public void onMessage(String message) throws IOException {  
    	JSONObject mess;
    	System.out.println("实时消息发送");
		try {
			mess = new JSONObject(message);
			if(mess.getString("type").equals("好友请求"))
			{
				JSONObject text = mess.getJSONObject("text");
				String fUname = text.getString("from");
				String tUname = text.getString("to");
				if(clients.containsKey(tUname))
				{
					sendMessageTo(message,tUname);
					System.out.println(fUname+" add "+tUname);
				}
				else
				{
					Information inf = new Information();
					inf.text = mess.getString("text");
					inf.time = mess.getString("time");
					inf.type = "好友请求";
					inf.fname = fUname;
					inf.uname = tUname;
					Information_Sql infSql = new Information_Sql();
					System.out.println("将好友请求存入数据库");
					System.out.println(inf.text);
					infSql.addInformationtoSql(tUname,fUname, inf);
				}
			}
			else if(mess.getString("type").equals("好友消息")||mess.getString("type").equals("好友图片消息"))
			{
				JSONObject text = new JSONObject(mess.getJSONObject("text").toString());
				String fUname = text.getString("from");
				String tUname = text.getString("to");
				if(clients.containsKey(tUname))
				{
					sendMessageTo(message,tUname);
					System.out.println(fUname+" send msg to "+tUname);
				}
				else
				{
					Information inf = new Information();
					
					
//					String content = text.getString("content");
					inf.time = mess.getString("time");
					inf.text = text.toString();
					inf.type = "好友消息";
					inf.fname = fUname;
					inf.uname = tUname;
					Information_Sql infSql = new Information_Sql();
					System.out.println("将好友消息存入数据库");
					System.out.println(inf.text);
					infSql.addInformationtoSql(tUname,fUname, inf);
				}
			}
			
		System.out.println("现在的连接"+WebSocket.getClients());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
//        JSONObject jsonTo = JSONObject.fromObject(message);  
          
//        if (!jsonTo.get("To").equals("All")){  
//            sendMessageTo("给一个人", jsonTo.get("To").toString());  
//        }else{  
       
//        sendMessageTo("发送成功",username);  
//        sendMessageAll()
//        }  
    }  
  
    @OnError  
    public void onError(Session session, Throwable error) {  
        error.printStackTrace();  
    }  
  
    public void sendMessageTo(String message, String To) throws IOException {  
        // session.getBasicRemote().sendText(message);  
        //session.getAsyncRemote().sendText(message);  
        for (WebSocket item : clients.values()) {  
            if (item.username.equals(To) )  
                item.session.getAsyncRemote().sendText(message);  
            
        }  
    }  
    
    public void sendMessageToTong(String message, String To) throws IOException {  
        // session.getBasicRemote().sendText(message);  
        //session.getAsyncRemote().sendText(message);  
        for (WebSocket item : clients.values()) {  
            if (item.username.equals(To) )  
                item.session.getBasicRemote().sendText(message);  
            
        }  
    }  
    
    public static void closeClient(String uname)
    {
    	for (WebSocket item : clients.values()) {  
            if (item.username.equals(uname) )
				try {
					item.session.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
        }  
    }
      
    public void sendMessageAll(String message) throws IOException {  
        for (WebSocket item : clients.values()) {  
            item.session.getAsyncRemote().sendText(message);  
        }  
    }  
    
    public void sendMessageAllTong(String message) throws IOException {  
        for (WebSocket item : clients.values()) {  
            item.session.getBasicRemote().sendText(message);  
        }  
    }  
      
      
  
    public static synchronized int getOnlineCount() {  
        return onlineCount;  
    }  
  
    public static synchronized void addOnlineCount() {  
        WebSocket.onlineCount++;  
    }  
  
    public static synchronized void subOnlineCount() {  
        WebSocket.onlineCount--;  
    }  
  
    public static synchronized Map<String, WebSocket> getClients() {  
        return clients;  
    }  

}  


//{
//	type:"好友请求";
//	time:"";
//	text:{
//		from:"";
//		to:"";
//	}
//
//}