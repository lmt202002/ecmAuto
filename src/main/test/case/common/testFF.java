package common;

import com.thoughtworks.selenium.condition.Not;
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
        public static void setParameter(WebDriver driver , String domain) throws InterruptedException {
        /**设置后台各项返利参数
         */
        //设置代理商等级
                driver.get(domain+"/admin/agentLevel/page");//访问代理商等级页面
                for (int i=0;i<4;i++) {
                        int percent=i*10+30;
                        driver.findElement(By.id("priceRatio_"+i)).clear();
                        driver.findElement(By.id("priceRatio_"+i)).sendKeys(""+percent);//设置各级拿货价比例，分别为30、40、50、60
                }
                for (int i=0;i<4;i++) {
                        int money=5-i;
                        driver.findElement(By.id("thresholdMoneyId"+i)).clear();
                        driver.findElement(By.id("thresholdMoneyId"+i)).sendKeys(""+money);//设置各级首次拿货门槛，分别为4、3、2、1
                }
                for (int i=2;i<6;i++) {
                        if (! driver.findElement(By.id("teamPerformanceState_"+i)).isSelected())
                                 driver.findElement(By.id("teamPerformanceState_"+i)).click();//设置各级团队业绩奖为启用
                }
                for (int i=3;i<6;i++) {
                        if (driver.findElement(By.id("recommendAwardState_"+i)).isSelected())
                                driver.findElement(By.id("recommendAwardState_" + i)).click();//设置各级一次性推荐奖为禁用

                }
                driver.findElement(By.id("J_Form")).submit(); //保存提交表单

          //设置推荐奖励
                driver.get("/admin/agentRebateSetting/list");
                driver.findElement(By.id("agentRebateLevel1")).clear();
                driver.findElement(By.id("agentRebateLevel1")).sendKeys("24");//设置直接推荐奖励为24%
                driver.findElement(By.id("agentRebateLevel2")).clear();
                driver.findElement(By.id("agentRebateLevel2")).sendKeys("12");//设置间接推荐奖励为12%
                driver.findElement(By.id("award")).click(); //保存提交表单
                driver.findElement(By.xpath(".//*[@id='tab']/ul/li[2]/span")).click();//切换到平级推荐一次性奖励设置页面
                driver.findElement(By.id("onceRecommend")).clear();
                driver.findElement(By.id("onceRecommend")).sendKeys("30");//设置平级推荐一次性奖励为30%
                driver.findElement(By.id("once")).click(); //保存提交表单
                driver.findElement(By.xpath(".//*[@id='tab']/ul/li[3]/span")).click();//切换到推荐上级一次性奖设置页面
                driver.findElement(By.id("subRecommend")).clear();
                driver.findElement(By.id("subRecommend")).sendKeys("9");//设置平级推荐一次性奖励为9%
                driver.findElement(By.id("sub")).click(); //保存提交表单
          //业绩返点设置
                driver.get("/admin/agentPerformanceSetting/page");
                for (int i=2;i<5;i++) {
                        int achMoney=i*1000-1000;
                        driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr["+i+"]/td[2]/div/span")).click();
                        driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr["+i+"]/td[2]/div/span")).clear();
                        driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr["+i+"]/td[2]/div/span")).sendKeys(""+achMoney);//设置各级业绩分别为满1000、2000、3000
                }
                for (int i=2;i<5;i++) {
                        int percent = i * 10 - 10;
                        driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr["+i+"]/td[3]/div/span")).click();
                        driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr["+i+"]/td[3]/div/span")).clear();
                        driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr["+i+"]/td[3]/div/span")).sendKeys(""+percent);//设置各级返点比例分别为10%、20%、30%
                }
                driver.findElement(By.id("saveOrUpdateBtn")).click();//点击保存
          }


}
