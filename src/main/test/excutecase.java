import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import common.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class excutecase {
    public static void main(String[] args ) throws InterruptedException, IOException {

        WebDriver driver=new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, 3);
        driver.manage().window().maximize();
        String domain="http://o2oagent.ecmaster.cn";
        String user="16111111111";
        Session.login(driver,domain,user);//前台登录
//        Session.bgLogin(driver,domain);//后台登录
        List<String> userList=new ArrayList<String>();
        userList.add("董事1");
        userList.add("ds1");
        FgCatchRecommendRebateGet.recommendRebateGet(driver,domain,userList);




//        Session.bgLogout(driver,domain);//后台退出
//        driver.close();
    }

}
