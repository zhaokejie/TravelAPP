package tools;

import Location.Coordinate;
import com.sun.xml.internal.bind.v2.runtime.Coordinator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class CreateData {
    public String[] cities = {
            "郑州","杭州","北京","武汉","上海","深圳","成都",
            "西宁","西安","重庆","青岛","南京","厦门",
            "大连","天津","三亚","济南","沈阳","苏州",
            "宁波","长沙","呼和浩特","乌鲁木齐",
            "长春", "哈尔滨","银川","桂林","无锡","太原"
    } ;
    public void CreateNewAccount(int acnum)
    {
        int city1 = (int)(Math.random()*29);
        int city2 = (int)(Math.random()*29);
        Coordinate[] cityP1 = getCitydata(cities[city1]);
        Coordinate[] cityP2 = getCitydata(cities[city2]);





        ArrayList<ArrayList<String>> alldata=new ArrayList<ArrayList<String>>();
        for(int i = 0;i<20;i++)
        {
            int test = (int)(Math.random()*20);
            if((test-1)*16>(i+1)*14)
            {
                int score = (int)(Math.random()*3)+3;
                alldata.add(new ArrayList<String>(Arrays.asList(String.valueOf(acnum),cityP1[i].typecode,score,new Date().getTime())));  //添加一行
            }
        }

        Array2CSV(alldata,"fakedata.csv");
    }


    //废弃的之前的尝试

    //----------------------------
//    //存储生成的到csv
//    public void addSaveDate(String aDate) throws IOException {
//        File date = new File("fakedata.csv");
////        FileWriter dOut = new FileWriter(date,true);
////        dOut.write(aDate);
//        FileOutputStream os = new FileOutputStream(date,true);
//        BufferedOutputStream buff = new BufferedOutputStream(os);
//        buff.write(aDate.getBytes("UTF-8"));
//        os.close();
//    }
    //------------------------------



    public static void main(String[] args) throws IOException {

        ArrayList<ArrayList<String>> alldata=new ArrayList<ArrayList<String>>();
        alldata.add(new ArrayList<String>(Arrays.asList("1","11","111")));  //添加一行
        alldata.add(new ArrayList<String>(Arrays.asList("2","22","222")));  //添加一行
        alldata.add(new ArrayList<String>(Arrays.asList("3","33","333")));  //添加一行
        Array2CSV(alldata,"fakedata.csv");

        System.out.println();

    }


    //将Arraylist类型的字符串数组存进csv
    public static void Array2CSV(ArrayList<ArrayList<String>> data, String path)
    {
        try {
            BufferedWriter out =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path,true),"UTF-8"));
            for (int i = 0; i < data.size(); i++)
            {
                ArrayList<String> onerow=data.get(i);
                for (int j = 0; j < onerow.size(); j++)
                {
                    out.write(DelQuota(onerow.get(j)));
                    out.write(",");
                }
                out.newLine();
            }
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //对csv文件的格式进行整理

    public static String DelQuota(String str)
    {
        String result = str;
        String[] strQuota = { "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "`", ";", "'", ",", ".", "/", ":", "/,", "<", ">", "?" };
        for (int i = 0; i < strQuota.length; i++)
        {
            if (result.indexOf(strQuota[i]) > -1)
                result = result.replace(strQuota[i], "");
        }
        return result;
    }

}



//        示例代码
//        ArrayList<ArrayList<String>> alldata=new ArrayList<ArrayList<String>>();
//        alldata.add(new ArrayList<String>(Arrays.asList("1","11","111")));  //添加一行
//        alldata.add(new ArrayList<String>(Arrays.asList("2","22","222")));  //添加一行
//        alldata.add(new ArrayList<String>(Arrays.asList("3","33","333")));  //添加一行
//        Array2CSV(alldata,"fakedata.csv");