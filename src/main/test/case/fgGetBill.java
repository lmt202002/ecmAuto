import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;

public class fgGetBill {
    public static List getBill(WebDriver driver , String domain) throws InterruptedException {
        /**
         访问前台订货商品列表，并订货，返回字符串类型的订单总额、第一款商品数量、订单号的List;
         */
        driver.get(domain+"/m/agent/admin/product/productList");//访问前台订货商品列表页面
//        driver.navigate().refresh();
        Thread.sleep(2000);
//        driver.findElement(By.id("productName")).sendKeys(goodsName); //输入商品名称
        driver.findElement(By.xpath(".//*[@id='J_ProductList']/ul/li[1]/a/div[1]")).click();//点击第一个商品，当前测试库为新商品0621
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='J_BtnCart']/span")).click();//点击加入进货单按键
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@id='div_sku_list']/div[1]/div/div/label[1]/span")).click();//选择一级第一个规格
        driver.findElement(By.xpath(".//*[@id='div_sku_list']/div[2]/div/div/label[1]/span")).click();//选择二级第一个规格
        driver.findElement(By.xpath(".//*[@id='J_ASSpec']/div[3]/div/div[3]/div[1]/label[2]/span")).click();//选择数量单位为个
        driver.findElement(By.id("quantity")).clear();//先清除值
        driver.findElement(By.id("quantity")).sendKeys("120");//再输入数量为120个
        driver.findElement(By.xpath(".//*[@id='J_ASSpec']/div[4]/div/div/a/span")).click();//点击确定
        Thread.sleep(2000);
        driver.get(domain+"/m/agent/admin/order/cart/show");//访问进货单页面
        Thread.sleep(2000);
        driver.findElement(By.id("J_CheckAll")).click();//全选
        driver.findElement(By.id("J_ButtonCash")).click();//结算
        Thread.sleep(2000);
//        String js="document.getElementsByName('filePath').value='/cmsstatic/agent_order_checkout/15907/1503308408918821.jpg')'";
//        ((JavascriptExecutor) driver).executeScript(js);
        driver.findElement(By.id("orderRemark")).sendKeys("打款人姓名");//填写打款人
        String money=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[7]/div/div[1]/div/div/span/em")).getText();//获取支付总额
        String goodsNum=driver.findElement(By.xpath(".//*[@id='page']/div[2]/div[2]/div/ul/li/p[2]")).getText();//获取第一款商品数量字符串
        goodsNum=goodsNum.substring(1,goodsNum.length());   //字符串处理，去掉x
        driver.findElement(By.id("submit_order_btn")).click();//提交订单
        Thread.sleep(1000);
//        System.out.println(money);
        String billNum=driver.findElement(By.tagName("strong")).getText().substring(4,19);//获取订单号
//        System.out.println(billNum);
        List<String> List=new ArrayList<String>();
        List.add(money);//总额
        List.add(goodsNum);//第一款数量
        List.add(billNum);//订单号
        System.out.println(List);
        return List;

    }
}
