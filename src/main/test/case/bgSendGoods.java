import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class bgSendGoods {
    public static void sendGoods(WebDriver driver , String domain,String bigCode) throws InterruptedException {
        driver.get(domain+"/admin/wechatAgent/order/topOrderList?tabIndex=0");//访问防伪码数据管理页面
//        driver.navigate().refresh();
//        Thread.sleep(3000);
//        WebElement iframe=driver.findElement(By.tagName("iframe"));
//        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));//跳进框架页
        //点击第一行发货
//        Thread.sleep(10000);
        List<WebElement> links=driver.findElements(By.linkText("发货"));
        List<WebElement> sendlinks=null;
        links.get(0).click();//点击第一个的发货
        driver.findElement(By.xpath("html/body/div[10]/div[2]/form/div[7]/div/div/a")).click();//点击录入链接
        driver.findElement(By.id("code")).sendKeys(bigCode);//输入大码
        driver.findElement(By.id("BtnAddCode")).click();
//        int k=links.size();
//        System.out.println(k);
//        for (int i=0;i<links.size();i++){
//            if (links.get(i).getText()=="发货")
//                 sendlinks.add(links.get(i));
//            System.out.println(links.get(i).getText());
//        }
//        for (int j=0;j<sendlinks.size();j++) {
//            System.out.println(sendlinks.get(j));
//
//        }
//        String bigCode=bigCodeList.get(0).getText();
//        return bigCode;
    }
}
