import common.FgStep;
import common.RWuserData;
import common.Session;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class fgAuditOrder {
    public static void main(String[] args ) throws InterruptedException, IOException {

        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String domain="http://o2oagent.ecmaster.cn";
        String billDataPath="E:\\github\\company\\src\\main\\test\\case\\testdata\\billdata.json";
        String userFilePath="E:\\github\\company\\src\\main\\test\\case\\common\\UserData.json";
        String wechat=RWuserData.readTestData(billDataPath,"wechat").toString();//从订单数据中取出提交订单人的微信号
        String parentWechat=RWuserData.readUserData(userFilePath,wechat).toMap().get("parent").toString();//根据微信号取出上级微信号
        String parentPhone=RWuserData.readUserData(userFilePath,parentWechat).toMap().get("phone").toString();//根据上级微信号找出对应的手机号码

        Session.login(driver,domain,parentPhone);//上级在前台登录
        FgStep.auditOrder(driver,domain,true);//审核时，点同意
        Session.logout(driver,domain);//退出登录
        driver.close();//结束浏览器
    }

}
