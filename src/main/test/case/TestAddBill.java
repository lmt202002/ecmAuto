import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import common.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestAddBill {
    public static void main(String[] args ) throws InterruptedException, IOException {

        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String domain="http://o2oagent.ecmaster.cn";
        String wechat="atyj4";
        String userFilePath="E:\\github\\company\\src\\main\\test\\case\\common\\UserData.json";
        Map map=RWuserData.readUserData(userFilePath,wechat).toMap();//取出指定用户的数据为Map
        String userPhone=map.get("phone").toString();//从map中取出用户手机号
        Session.login(driver,domain,userPhone);//前台登录
//        List<String> userList=new ArrayList<String>();
        List<String>list1=FgStep.getBill(driver,domain);//下采购单
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("wechat",wechat);
        jsonObject.put("totalNoney",list1.get(0));
        jsonObject.put("number",list1.get(1));
        jsonObject.put("orderNumber",list1.get(2));
        FileWriter fw = new FileWriter("E:\\github\\company\\src\\main\\test\\case\\testdata\\billdata.json");//指定写入的文件路径
        PrintWriter out = new PrintWriter(fw); //创建写对象
        out.write(jsonObject.toString()); //把修改后的所有人JSON数据写到文件中
        out.println();
        fw.close();
        out.close();

        Session.logout(driver,domain);//前台退出
        driver.close();
    }

}
