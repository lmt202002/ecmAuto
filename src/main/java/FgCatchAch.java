import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class FgCatchAch {
    public static List catchAch(WebDriver driver , String domain) throws InterruptedException {
        /**
        访问前台首页,我的团队业绩、当前返点比例，返回Float类型型List ，包含团队业绩、当前返点比例；
         */
        driver.get(domain+"/m/agent/admin/");//访问我首页
        Thread.sleep(1000);
        Float archieve=Float.parseFloat(driver.findElement(By.xpath(".//*[@id='page']/div[1]/div[2]/a[1]/div/div[1]/div[2]/span/em")).getText());//获取我的团队业绩，并转换为浮点
//        System.out.println(archieve);
        String per=driver.findElement(By.xpath(".//*[@id='page']/div[1]/div[2]/a[1]/div/div[3]/div[2]/span/em")).getText();//获取当前返点比例百分比
        Float percent=Float.parseFloat(per.substring(0,per.length()-1));//去掉右边的百分号并转为整型
        List<Float> List1=new ArrayList<Float>();
        List1.add(archieve);
        List1.add(percent);
//        System.out.println("我的团队业绩总额："+List1.get(0)+"\n当前返点比例："+List1.get(1));
        return List1;

    }
}
