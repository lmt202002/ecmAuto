package common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class testFF {
    public static boolean changeRelations(WebDriver driver , String domain, List<String> list) throws InterruptedException {
        /**
         根据给的微商关系List，设置指定微商的等级、上级、推荐人，并返回是否成功
         */
        try {
            driver.get(domain+"/admin/wechatAgent/list");//访问总部微商列表页面
            driver.findElement(By.id("short_cut_like_orderNumber_")).sendKeys(list.get(0));// 输入微信号
            driver.findElement(By.xpath("html/body/div[1]/div[2]/div[1]/div[1]/button")).click();//点击搜索
            driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr[2]/td[2]/div/span/a[1]")).click();//点击编辑
            driver.findElement(By.xpath(".//*[@id='select2-angetChlidLv-container']")).click();//点击上级搜索下拉列表
            driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys(list.get(2));//在上级搜索框输入上级姓名
            driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);//搜索框ENTER键选择


        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
