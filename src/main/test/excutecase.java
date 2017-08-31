import common.testFF;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import common.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class excutecase {
    public static void main(String[] args ) throws InterruptedException, IOException {

//        WebDriver driver=new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, 3);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//       WebDriverWait  wait=new WebDriverWait(driver,5);
//        driver.manage().window().maximize();
        String domain="http://o2oagent.ecmaster.cn";
        String wechat="atyj4";
        String level="梦想合伙人";
        String parent="at总裁1";
        String reference="atzc3";
        String userFilePath="E:\\github\\company\\src\\main\\test\\case\\common\\UserData.json";
//        File file = new File("E:\\github\\company\\src\\main\\test\\case\\common\\UserData.json");
//        String content = FileUtils.readFileToString(file, "UTF-8");//读取所有json数据为字符串
//        JSONObject jsonObject = new JSONObject(content);//把字符串转为json对象
//        String contenttmp = jsonObject.getJSONObject(wechat).toString();//取出指定人数据字符串
//        JSONObject jsontemp = new JSONObject(contenttmp);//把指定人数据转为JSON对象
//       System.out.println(jsontemp.getJSONObject(wechat).toString());
        RWuserData.writeUserData(userFilePath,wechat,reference);

//        jsontemp.put("level", level);//给对象的当前级别key赋值
//        jsontemp.put("parent", parent);//给对象的上级key赋值
//        jsontemp.put("reference", reference);//给对象的推荐人key赋值
//        jsonObject.put(wechat, jsontemp);   //把指定对象的JSON数据再写入所有人的JSON数据中
//        System.out.println(jsontemp.toString());
//        FileWriter fw = new FileWriter("E:\\github\\company\\src\\main\\test\\case\\common\\UserData1.json");//指定写入的文件路径
//        PrintWriter out = new PrintWriter(fw); //创建写对象
//        out.write(jsonObject.toString()); //把修改后的所有人JSON数据写到文件中
//        out.println();
//        fw.close();
//        out.close();






//        Session.bgLogout(driver,domain);//后台退出
//        driver.close();
    }

}
