import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class FgCatchStock {
    public static List catchStock(WebDriver driver , String domain, String goodsName) throws InterruptedException {
        /**
        访问前台我的库存管理页面,并搜索商品goodsName，获取库存，返回值为List ，包含整数已收库存和待收库存；
         */
        driver.get(domain+"/m/agent/admin/product/myInventory");//访问我的库存页面
        Thread.sleep(2000);
        driver.findElement(By.id("query")).sendKeys(goodsName); //输入商品名称
        driver.findElement(By.id("queryBtn")).click();//点击查询
        Thread.sleep(2000);
        String stringStock=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/ul/li/div[2]/div[2]/span/em")).getText();//获取已收库存
        int nowstock=Integer.parseInt(stringStock);
        stringStock=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/ul/li/div[2]/div[3]/span/em")).getText();//获取待收库存
        int dueStock=Integer.parseInt(stringStock);
        List<Integer> List1=new ArrayList<Integer>();
        List1.add(nowstock);
        List1.add(dueStock);
//        System.out.println("已收库存为："+List1.get(0)+"\n待收库存为："+List1.get(1));
        return List1;

    }
}
