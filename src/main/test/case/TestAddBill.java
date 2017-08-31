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
        /**前台微商采购下单
         * 并把返回的订单总额、商品数量、订单号写入TestAddBill01.json文件
         */
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
        list1.add(wechat);

        RWuserData.writeTestJSON(list1,"TestAddBill01");//写结果数据到TestAddBill01.json

        Session.logout(driver,domain);//前台退出
        driver.close();
    }

}
