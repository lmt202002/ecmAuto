import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FgCatchAchRebateGiven {
    public static List catchAchRebateGiven(WebDriver driver , String domain,List<String> userList) throws InterruptedException {
        /**
        访问我的应付团队返利页面，获取我应付给目标的团队返利，返回Float类型List ，包含返利金额、订单总额；
         */
        float rebate=0;
        float totals=0;
        List<WebElement> nameElement=new ArrayList<WebElement>(); //定义列表所有对象List
        List<Float> List1=new ArrayList<Float>();//定义返回值List
        driver.get(domain+"/m/agent/admin/performance/mySubBillList?limit=100&start=0");//访问我应付的团队返利页面
        Thread.sleep(1000);

        nameElement=driver.findElements(By.tagName("h3"));//获取列表所记录的姓名对象

        if (nameElement.size()==0){  //如果没有h3对象，证明列表没有记录，值都可以置为空，返回0值；
//            System.out.println("列表没有记录");
            List1.add(rebate);
            List1.add(totals);
            return List1;
            }
        int goalnum=0;//定义变量存储指定姓名的最后一条记录位置
        for (int i=0;i<nameElement.size();i++){
//            System.out.println(nameElement.get(i).getText());
            if (nameElement.get(i).getText().equals(userList.get(0)+"（"+userList.get(1)+"）")) {
                goalnum = i;
//                System.out.println(goalnum);
            }
        }
        driver.get(domain+"/m/agent/admin/performance/mySubBillList?limit=1&start="+goalnum+"");//重新访问我应付的团队返利页面，从第i条开始只显示1条，即只过滤出指定的记录，0为第一条
        Thread.sleep(1000);
        try {
            rebate=Float.parseFloat(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div/div/ul/li/div[2]/span[1]/span/em")).getText());//获取返利金额字符串，并转为浮点型
            totals=Float.parseFloat(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div/div/ul/li/div[2]/span[2]/span/em")).getText());//获取订单总额字符串，并转为浮点型
        }catch (Exception e){
            List1.add(rebate);
            List1.add(totals);
            return List1;
        }

        List1.add(rebate);//返利
        List1.add(totals);//订单总额
//        System.out.println("返利金额："+List1.get(0)+"\n订单总额："+List1.get(1));
        return List1;

    }
}
