import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BgAuditOrder {
    public static void auditOrder(WebDriver driver , String domain,boolean status) throws InterruptedException {
        /**
        点击总部所有订单列表的第一个审核链接，进入审核页面，并按提供的参数点同意或拒绝
         */
        driver.get(domain+"/admin/wechatAgent/order/topOrderList?tabIndex=0");//访问总部所有订单列表页面
        Thread.sleep(2000);
        List<WebElement> links=driver.findElements(By.linkText("审核"));//获取所有审核链接
        links.get(0).click();//点击第一个的审核
        Thread.sleep(2000);
//        driver.navigate().refresh();
        if (status) {
            driver.findElement(By.xpath(".//*[@id='orderInfoViewContainer']/em/li[2]/button")).click();//如果是true就点同意
            driver.findElement(By.xpath("html/body/div[3]/div[3]/button[1]")).click();//点击确定操作
        }
        else {
            driver.findElement(By.xpath(".//*[@id='orderInfoViewContainer']/em/li[3]/button")).click();//如果不是true就点拒绝
            driver.findElement(By.xpath("html/body/div[3]/div[3]/button[1]")).click();//点击确定操作
        }
    }
}