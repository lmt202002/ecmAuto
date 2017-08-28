import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import common.*;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        driver.manage().window().maximize();
        String domain="http://o2oagent.ecmaster.cn";
        String user="admin";
//        Session.login(driver,domain,user);//前台登录
        Session.bgLogin(driver,domain);//后台登录
        List<String> userList=new ArrayList<String>();
        userList.add("wxyj001");
        userList.add("梦想合伙人");
        userList.add("董事1");
        userList.add("梦想1(mx1)");
//        FgStep fgStep=new FgStep();

         testFF.getTeamArcRebate(driver,domain,"bruceloo");





//        Session.bgLogout(driver,domain);//后台退出
//        driver.close();
    }

}
