package testWeather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather {

	private String key = "d32b6c72e22c803a3bff1dfdf9afbd6a";
	String address;
//	public static void main(String[] args) {
//		Weather aw = new Weather();
//		System.out.println("天气信息:"+aw.getWeather(110101));
//
//	}
	
	public String getWeather(int cityCode){
	    // 我们需要进行请求的地址：
	    String aurl = "https://restapi.amap.com/v3/weather/weatherInfo?city="+cityCode+"&key="+this.key;
	    try {
	        // 1.URL类封装了大量复杂的实现细节，这里将一个字符串构造成一个URL对象
	        URL url = new URL(aurl);
	        // 2.获取HttpURRLConnection对象
	        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	        // 3.调用connect方法连接远程资源
	        connection.connect();
	        // 4.访问资源数据，使用getInputStream方法获取一个输入流用以读取信息
	        BufferedReader bReader = new BufferedReader(
	                    new InputStreamReader(connection.getInputStream(), "UTF-8"));

	        // 对数据进行访问
	        String line = null;
	        StringBuilder stringBuilder = new StringBuilder();
	        while ((line = bReader.readLine()) != null) {
	            stringBuilder.append(line);
	        }

	        // 关闭流
	        bReader.close();
	        // 关闭链接
	        connection.disconnect();
	        // 打印获取的结果
//	        System.out.println(stringBuilder.toString());
	        JSONObject jsonObj = new JSONObject(stringBuilder.toString());
//	        int age = jsonObj.getInt("age");
//			StringBuilder lives = jsonObj.get("lives");
	        String jsonO = stringBuilder.toString();
	        jsonO = jsonO.replaceAll("\"lives\":\\[\\{", "");
	        jsonO = jsonO.replaceAll("}]", "");
//	        System.out.println(jsonO);
			JSONObject jsonObj2 = new JSONObject(jsonO);
			
//			String dayweather = (String)jsonObj2.get("weather");
//			String daytemp = (String)jsonObj2.get("temperature");
//////	        String request = (String) jsonObj.get("request");
//	        System.out.println(dayweather);
//	        System.out.println(daytemp);
	        return jsonObj2.toString();

	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        }catch (IOException e){
	        	 e.printStackTrace();
	    	}catch(JSONException e) {
	    		e.printStackTrace();
	    	}
	    
	    	return "-1";
	    }



}

