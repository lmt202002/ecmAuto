import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import common.*;
/**
 * Created by lvshr on 2017/3/23.
 */

public class test1 {
    public static void main(String[] args ) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        String domain="http://o2oagent.ecmaster.cn";
        session.login(driver,domain);
//        driver.close();
        Thread.sleep(3000);
        session.logout(driver,domain);
    }
}
