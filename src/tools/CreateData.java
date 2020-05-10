package tools;

import java.io.*;

public class CreateData {
    public String[] cities = {
            "郑州","杭州","北京","武汉","上海","深圳","成都",
            "澳门","西安","重庆","青岛","南京","厦门",
            "大连","天津","三亚","济南","沈阳","苏州",
            "宁波","长沙","呼和浩特","乌鲁木齐",
            "长春", "哈尔滨"
    } ;
//    public String CreateNewAccount(int acnum)
//    {
//        int citie1 = (int)(Math.random()*25);
//        int citie2 = (int)(Math.random()*25);
//        String
//    }

    //存储生成的到csv
    public void addSaveDate(String aDate) throws IOException {
        File date = new File("fakedata.csv");
        FileWriter dOut = new FileWriter(date,true);
        dOut.write(aDate);

        dOut.close();
    }

    public static void main(String[] args) throws IOException {
        new CreateData().addSaveDate("234 杭州电子科技大学 4 9213822893\n");
    }
}
