import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import common.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class excutecase {
    public static void main(String[] args ) throws InterruptedException, IOException {

        WebDriver driver=new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, 3);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//       WebDriverWait  wait=new WebDriverWait(driver,5);
//        driver.manage().window().maximize();
        String domain="http://o2oagent.ecmaster.cn";
        String wechat="atyj4";
        String name="at一级4";
        String phone="18444440003";
        String userFilePath="E:\\github\\company\\src\\main\\test\\case\\common\\UserData.json";
        Session.login(driver,domain,phone);
        List<String> userlist=new ArrayList<String>();  //存储用户姓名和微信号
        userlist.add(name);
        userlist.add(wechat);
        FgStep.catchAchRebateGiven(driver,domain,userlist);



//        Session.bgLogout(driver,domain);//后台退出
//        driver.close();
    }

}
