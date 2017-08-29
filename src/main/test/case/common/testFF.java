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
        public void main(String[] args){

        }
        public static List onceRebate(WebDriver driver , String domain, String wechat) throws InterruptedException {
        /**
         根据给的微商微信号，搜索返利记录，并过滤出记录，返回最近的统计中，明细页面最新子订单记录的订单总额、返利比例、返利总额，是否正常，1为正常，0为不正常
         */
            float orderMoney=0;
            float percent=0;
            float rebate=0;
            float status=1;
            List<Float> resultList=new ArrayList<Float>();
        try {
                driver.get(domain+"/admin/agentOnceRecommend/index");//访问团队业绩返利报表页面
                new Select(driver.findElement(By.id("month"))).selectByVisibleText("7");//选择7月
                driver.findElement(By.name("agentName")).sendKeys(wechat);// 输入微信号
                driver.findElement(By.id("btnSearch")).click();//点击搜索
                Thread.sleep(1000);
                List<WebElement> trlist=new ArrayList<WebElement>();
                trlist=driver.findElements(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr")); //获取表格区tr元素对象list
                int size=trlist.size();
                driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr["+size+"]/td[6]/div/span/a")).click();//点击搜索出的微商最近的统计记录的查看明细链接
                orderMoney=Float.parseFloat(driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr[1]/td[6]")).getText().substring(1));//取最新子订单总额字符串,再截取数值部分，再转为Fload型
                percent=Float.parseFloat(driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr[1]/td[7]")).getText().split("%")[0]);//取最新子订单返点比例字符串,再截取数值部分，再转为Fload型
                rebate=Float.parseFloat(driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr[1]/td[8]")).getText().substring(1));//取最新子订单返利总额字符串,再截取数值部分，再转为Fload型
                System.out.println(rebate);
                resultList.add(orderMoney);
                resultList.add(percent);
                resultList.add(rebate);
                resultList.add(status);
        }
        catch (Exception e){
                status=0;
                resultList.add(orderMoney);
                resultList.add(percent);
                resultList.add(rebate);
                resultList.add(status);
                System.out.println(resultList);
                return resultList;
        }
        System.out.println("最新子订单总额："+orderMoney+"\n返点比例："+percent+"\n返利金额："+rebate+"\n是否成功："+status);
        return resultList;
    }


}
