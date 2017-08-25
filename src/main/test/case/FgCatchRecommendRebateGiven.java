import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FgCatchRecommendRebateGiven {
    public static List recommendRebateGiven(WebDriver driver , String domain,List<String> userList) throws InterruptedException {
        /**
        访问我的应付推荐返利页面，获取我应付给目标(userlist)的推荐返利，返回Float类型List ，包含应付目标返利金额、子订单返利比例、子订单返利金额、子订单订单总额；
         */
        float rebate=0;//外部记录的返利金额
        float percent=0;//明细中订单记录的返利比例
        float subRebate=0;//明细中订单记录的返利金额
        float subtotal=0;//明细中订单记录的订单总额
        List<WebElement> nameElement=new ArrayList<WebElement>(); //定义列表所有对象List
        List<Float> List1=new ArrayList<Float>();//定义返回值List
        driver.get(domain+"/m/agent/admin/recommendRebate/subBillIndex?limit=100&start=0");//访问我应付的推荐返利页面
        Thread.sleep(1000);

        nameElement=driver.findElements(By.tagName("h3"));//获取列表所记录的姓名对象

        if (nameElement.size()==0){  //如果没有h3对象，证明列表没有记录，值都可以置为空，返回0值；
//            System.out.println("列表没有记录");
            List1.add(rebate);
            List1.add(percent);
            List1.add(subRebate);
            List1.add(subtotal);
            return List1;
            }
        int goalnum=0;//定义变量存储指定姓名的最后一条记录位置
        for (int i=0;i<nameElement.size();i++){
//            System.out.println(nameElement.get(i).getText());
            //比较字符串格式为：“董事1(ds1)”，如果一样，则取出目标位置，循环完，取得最后一条同名记录的位置
            if (nameElement.get(i).getText().equals(userList.get(0)+"("+userList.get(1)+")")) {
                goalnum = i;
//                System.out.println(goalnum);
            }
        }
        driver.get(domain+"/m/agent/admin/recommendRebate/subBillIndex?limit=1&start="+goalnum+"");//重新访问我应付的推荐返利页面，从第i条开始只显示1条，即只过滤出指定的记录，0为第一条
        Thread.sleep(1000);
        try {
            rebate=Float.parseFloat(driver.findElement(By.xpath(".//*[@id='grid']/ul/li[1]/div[2]/span/span/em")).getText());//获取返利金额字符串，并转为浮点型
//            totals=Float.parseFloat(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div/div/ul/li/div[2]/span[2]/span/em")).getText());//获取订单总额字符串，并转为浮点型
        }catch (Exception e){
            List1.add(rebate);
            List1.add(percent);
            List1.add(subRebate);
            List1.add(subtotal);
            return List1;
        }


        driver.findElement(By.xpath(".//*[@id='grid']/ul/li[1]/a/h3")).click();//进入明细页面
        percent=Float.parseFloat(driver.findElement(By.xpath(".//*[@id='grid']/div/ul/li/div[2]/div[2]")).getText().split("%")[0].split("：")[1]);//获取明细中最新一条订单的返利比例字符串后再截取出值，并转为浮点型
        subRebate=Float.parseFloat(driver.findElement(By.xpath(".//*[@id='grid']/div[1]/ul/li/div[3]/span[1]/span/em")).getText());//获取明细中最新一条订单的返利金额，并转为浮点型
        subtotal=Float.parseFloat(driver.findElement(By.xpath(".//*[@id='grid']/div[1]/ul/li/div[3]/span[2]/span/em")).getText());//获取明细中最新一条订单的订单总额，并转为浮点型
//        List1.add(totals);//订单总额
        List1.add(rebate);
        List1.add(percent);
        List1.add(subRebate);
        List1.add(subtotal);
        System.out.println("返利金额："+List1.get(0)+"\n子订单返利比例："+List1.get(1)+"\n子订单返利金额："+List1.get(2)+"\n子订单订单总额："+List1.get(3));
        return List1;

    }
}
