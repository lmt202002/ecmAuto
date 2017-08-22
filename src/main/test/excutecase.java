import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import common.*;

import java.util.ArrayList;
import java.util.List;

public class excutecase {
    public static void main(String[] args ) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, 3);
        driver.manage().window().maximize();
        String domain="http://o2oagent.ecmaster.cn";
        String user="13055203099";
        Session.login(driver,domain,user);
//        Session.bgLogin(driver,domain);
//        bgAuditOrder.auditOrder(driver,domain,true);//后台总部审核操作
//        String bigCode=bgAddSecurityCode.addCode(driver,domain);//后台添加大码操作
//        bgBindCode.bindCode(driver,domain,bigCode);//后台绑定大码操作
//        bgSendGoods.sendGoods(driver,domain,bigCode);//后台发货操作
//        bgCatchStock.catchStock(driver,domain,"新商品06");//获取指定商品库存
//采购下单及获取订单信息
//        List <String> List1 =fgGetBill.getBill(driver,domain);
//        float money=Float.parseFloat(List1.get(0));
//        int goodsnum=Integer.parseInt(List1.get(1));
//        String billnum=List1.get(2);
//        System.out.println("订单总额："+money+"\n订单第一款商品数量："+goodsnum+"\n订单号："+billnum);
//        fgAuditOrder.auditOrder(driver,domain,true);//前台审核下级订单
//        fgCatchStock.catchStock(driver,domain,"新商品06");//获取新商品06搜索结果的库存
//        fgCatchAch.catchAch(driver,domain);//获取我的团队业绩和当前返点比例
//        List<String> userList=new ArrayList<String>();
//        userList.add("啊水2");
//        userList.add("bruceloo2");
//        List<Float> List2=new ArrayList<Float>();
//        List2=fgCatchAchRebateGiven.catchAchRebateGiven(driver,domain,userList);
//        System.out.println("返利金额："+List2.get(0)+"\n订单总额："+List2.get(1));



//        Session.bgLogout(driver,domain);
//        driver.close();
    }

}
