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

        WebDriver driver=new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, 3);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//       WebDriverWait  wait=new WebDriverWait(driver,5);
//        driver.manage().window().maximize();
        String domain="http://o2oagent.ecmaster.cn";
        String wechat="atds1";
        String level="梦想合伙人";
        String parent="at总裁1";
        String reference="atzc3";
        String phone="18111110001";
        String code="8103211325147762";
        String userFilePath="E:\\github\\company\\src\\main\\test\\case\\common\\UserData.json";
        Session.login(driver,domain,phone);
        FgStep.sendGoods(driver,domain,code);
//       System.out.println( FgStep.receiveGoods(driver,domain));




//        Session.bgLogout(driver,domain);//后台退出
//        driver.close();
    }

}
