import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;
import java.util.List;

public class bgBindCode {
    public static void bindCode(WebDriver driver , String domain,String bigCode) throws InterruptedException {
        driver.get(domain+"/admin/agentProduct/index");//访问防伪码数据管理页面
//        driver.navigate().refresh();
        Thread.sleep(2000);
         WebElement manageCodes=driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr[2]/td[2]/div/span/a"));
        manageCodes.click();      //点击第一个的防伪码管理--新商品0621
        Thread.sleep(1000);
        driver.findElement(By.id("security_code_mgr_1550-1552")).click();//点击小红规格的防伪码管理链接
        Thread.sleep(1000);
        driver.findElement(By.xpath(".//*[@id='bar-item-button6']/button")).click();//点击防伪码绑定按键
        Thread.sleep(1000);
        driver.findElement(By.id("codes")).sendKeys(bigCode); //输入大码
        driver.findElement(By.xpath("html/body/div[7]/div[3]/button[1]")).click();//确定
    }
}
