import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class bgSendGoods {
    public static void sendGoods(WebDriver driver , String domain,String bigCode) throws InterruptedException {
        /*
        点击总部所有订单列表的第一个发货链接，问按给的大码发货
         */
        driver.get(domain+"/admin/wechatAgent/order/topOrderList?tabIndex=0");//访问防伪码数据管理页面
//        driver.navigate().refresh();
        List<WebElement> links=driver.findElements(By.linkText("发货"));//获取所有发货链接
        links.get(0).click();//点击第一个的发货
        Thread.sleep(1000);
        driver.findElement(By.linkText("录入")).click();//点击录入链接
        driver.findElement(By.id("code")).sendKeys(bigCode);//输入大码
        driver.findElement(By.id("J_BtnAddCode")).click(); //点击添加
        driver.findElement(By.xpath("html/body/div[11]/div[3]/button[1]")).click();//点击确定
        Thread.sleep(1000);
        driver.findElement(By.xpath("html/body/div[13]/div[3]/button")).click();//点提示成功提示框的确定
        Thread.sleep(1000);
        driver.findElement(By.xpath("html/body/div[11]/div[3]/button[1]")).click();//点确定发货
    }
}
