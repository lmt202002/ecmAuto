import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import common.*;
/**
 * Created by lvshr on 2017/3/23.
 */

public class excutecase {
    public static void main(String[] args ) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        String domain="http://o2oagent.ecmaster.cn";
        Session.suLogin(driver,domain);

        String bigCode="8103211325146607";
        //        String bigCode=bgAddSecurityCode.addCode(driver,domain);
        bgBindCode.bindCode(driver,domain,bgAddSecurityCode.addCode(driver,domain));
//        bgSendGoods.sendGoods(driver,domain,bigCode);
//        Thread.sleep(3000);
//        Session.suLogout(driver,domain);
//        driver.close();
    }

}
