import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FgAuditOrder {
    public static void auditOrder(WebDriver driver , String domain,boolean status) throws InterruptedException {
        /**
        前台代理商审核下级订单，并按提供的参数点同意或拒绝
         */
        driver.get(domain+"/m/agent/admin/order/subOrderUnCheckList?limit=10&start=0");//访问待审核下级订单列表页面
        Thread.sleep(2000);
        List<WebElement> links=driver.findElements(By.linkText("审核"));//获取所有审核链接
        links.get(0).click();//点击第一个的审核
        Thread.sleep(2000);
//        driver.navigate().refresh();
        if (status) {
            driver.findElement(By.xpath(".//*[@id='page']/div[2]/div/div[6]/div/div/a[1]/span")).click();//如果是true就点同意
            driver.findElement(By.xpath("html/body/div[3]/div[3]/a[2]")).click();//点击确定操作
        }
        else {
            driver.findElement(By.xpath(".//*[@id='page']/div[2]/div/div[6]/div/div/a[2]/span")).click();//如果不是true就点拒绝
            driver.findElement(By.xpath("html/body/div[5]/div[3]/a[2]")).click();//点击确定操作
        }
    }
}