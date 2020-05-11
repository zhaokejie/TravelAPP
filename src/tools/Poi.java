package tools;
import Location.Coordinate;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Poi {

    private String key = "d32b6c72e22c803a3bff1dfdf9afbd6a";

    public void saveCitydata(List<String> cityname)
    {
        ArrayList<ArrayList<String>> alldata=new ArrayList<ArrayList<String>>();
        for (int i = 0;i<cityname.size();i++)
        {
            alldata.add(new ArrayList<String>(Collections.singleton(cityname.get(i))));
            List<Coordinate> locs = getCitydata(cityname.get(i));
            for (int j = 0;j<locs.size();j++)
            {
                Coordinate loc = locs.get(j);
                String[] locdata = {loc.longitude,loc.latitude,loc.typecode.substring(0,6),loc.typedes,loc.title,loc.address};
                ArrayList temp = new ArrayList<String>(Arrays.asList(locdata));
                //System.out.println(temp);
                alldata.add(temp);
            }
        }
        //System.out.println(alldata);
        CreateData.Array2CSV(alldata,"allCitydata.csv");
    }

    public List<Coordinate> getCitydata(String cityname)
    {
        String param = "key="+this.key+"&city="+cityname+"&types=110000|050000|060000|070000|080000|100000|120000";
        String aurl = "https://restapi.amap.com/v3/place/text?"+param;
        ArrayList<Coordinate> locs = new ArrayList<Coordinate>();

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

            System.out.println(stringBuilder.toString());
            //JSONObject jsonObj = new JSONObject(stringBuilder.toString());
            JSONObject jsonObj = new JSONObject(stringBuilder.toString());
            JSONArray jsonArray = jsonObj.getJSONArray("pois");

            for(int i = 0 ; i<jsonArray.length() ;i++)
            {
                JSONObject temp = (JSONObject) jsonArray.get(i);

                String title = temp.getString("name");
                String typedes = temp.getString("type");
                String typecode = temp.getString("typecode");
                String address = temp.getString("address");
//                String location = temp.getString("location");
//                String[] locnum = location.split("//,");
//                String lon = locnum[0];
//                String lat = locnum[1];

                System.out.print(title+" "+typedes+" "+typecode+" "+address+"\n");

                locs.add(new Coordinate("","","",title,address,typedes,typecode));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch(JSONException e) {
            e.printStackTrace();
        }

        return locs;
    }

    public static void main(String[] args) {
        Poi p = new Poi();
//        p.getCitydata("杭州");
        String[] cities = {
                "石家庄","沈阳","哈尔滨","太原","长春",
                "南京","杭州","合肥","福州","南昌","济南","郑州",
                "广州","长沙","武汉","海口","成都","贵阳","昆明",
                "西安","兰州","西宁","台北","呼和浩特","南宁","拉萨",
                "银川","乌鲁木齐","北京","上海","重庆","香港","澳门",
                "三亚","苏州","敦煌","呼伦贝尔","厦门","青岛","桂林",
                "天津","丽江","桂林","上饶","九江","宁波","无锡","合肥",
                "深圳","大连","洛阳"
        } ;
//        String[] cities = {"郑州"};
        p.saveCitydata(Arrays.asList(cities));
    }
}