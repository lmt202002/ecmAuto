package common;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class testFF {

    public static void getTeamArcRebate(WebDriver driver , String domain, String wechat) throws InterruptedException {
        /**
         根据给的微商微信号，搜索返利记录，并过滤出记录，返回最新记录的订单总额、返利比例、返利总额
         */
//        try {
            driver.get(domain+"/admin/teamPerformance/index");//访问团队业绩返利报表页面
            driver.findElement(By.name("agentName")).sendKeys(wechat);// 输入微信号
            driver.findElement(By.id("btnSearch")).click();//点击搜索
            List<WebElement> trlist=new ArrayList<WebElement>();
            trlist=driver.findElements(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr")); //获取表格区tr元素对象list
//            System.out.println(trlist.size());
//            driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr[2]/td[2]/div/span/a[1]")).click();//点击编辑
//            Select sel1=new Select(driver.findElement(By.id("angetLv")));
//            sel1.selectByVisibleText(list.get(1)); //选择会员等级
//            driver.findElement(By.xpath(".//*[@id='select2-angetChlidLv-container']")).click();//点击上级搜索下拉列表
//            driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys(list.get(2));//在上级搜索框输入上级姓名
//            driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);//搜索框ENTER键选择
//            Select sel2=new Select(driver.findElement(By.id("referrer")));
//            sel2.selectByVisibleText(list.get(3));//选择推荐人
//            Actions actions=new Actions(driver);
//            driver.findElement(By.xpath(".//*[@id='basicInfoForm']/div[1]/button")).click();
//        }
//        catch (Exception e){
//            return l;
//        }
//        return true;
    }


}
