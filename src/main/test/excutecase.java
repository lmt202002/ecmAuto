import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import common.*;

public class excutecase {
    public static void main(String[] args ) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        String domain="http://o2oagent.ecmaster.cn";
        Session.bgLogin(driver,domain);
//        bgAuditOrder.auditOrder(driver,domain,true);//后台总部审核操作
//        String bigCode=bgAddSecurityCode.addCode(driver,domain);//后台添加大码操作
//        bgBindCode.bindCode(driver,domain,bigCode);//后台绑定大码操作
//        bgSendGoods.sendGoods(driver,domain,bigCode);//后台发货操作




//        Session.bgLogout(driver,domain);
//        driver.close();
    }

}
