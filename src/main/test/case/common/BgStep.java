package common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class BgStep {
    public static String addCode(WebDriver driver , String domain) throws InterruptedException {
        /**
         添加1个规格为60的大码，并获取大码列表没绑定的第一个规格为60的大码，返回大码字符串参数bigCode
         */
        driver.get(domain+"/admin/securityCode/list");//访问防伪码数据管理页面
//        driver.navigate().refresh();
        Thread.sleep(2000);
        //点击生成防伪码按键
        driver.findElement(By.xpath(".//*[@id='bar-item-button7']/button")).click();
        // 设置要生成的大码数量
        driver.findElement(By.id("bigCount")).sendKeys("1");
        driver.findElement(By.id("smallCount")).sendKeys("60");//设置一个大码有多少个小码
        driver.findElement(By.xpath("html/body/div[7]/div[3]/button[1]")).click();
        Thread.sleep(2000);
        //点出查询框
        WebElement searchDiv=driver.findElement(By.id("codeStr"));
        Actions action=new Actions(driver);   //调用鼠标点击操作点出查询框
        action.click(searchDiv).perform();
        //设置过滤条件为查询60个小码的大码，未绑定，生成
        Thread.sleep(2000);
        driver.findElement(By.xpath(".//*[@name='nums']")).sendKeys("60");
        Select sel=new Select(driver.findElement(By.xpath(".//*[@id='sourceType']")));
        sel.selectByValue("GENERATE");//选择条件为生成
        Select sel2=new Select(driver.findElement(By.xpath(".//*[@id='status']")));
        sel2.selectByVisibleText("未绑定");//选择条件为未绑定
        driver.findElement(By.id("btnSearch")).click();//点击搜索
        Thread.sleep(2000);
        List<WebElement> bigCodeList=driver.findElements(By.className("bui-grid-cell-text"));
        String bigCode=bigCodeList.get(0).getText();
        return bigCode;
    }
    public static void auditOrder(WebDriver driver , String domain,boolean status) throws InterruptedException {
        /**
         点击总部所有订单列表的第一个审核链接，进入审核页面，并按提供的参数点同意或拒绝
         */
        driver.get(domain+"/admin/wechatAgent/order/topOrderList?tabIndex=0");//访问总部所有订单列表页面
        Thread.sleep(2000);
        List<WebElement> links=driver.findElements(By.linkText("审核"));//获取所有审核链接
        links.get(0).click();//点击第一个的审核
        Thread.sleep(2000);
//        driver.navigate().refresh();
        if (status) {
            driver.findElement(By.xpath(".//*[@id='orderInfoViewContainer']/em/li[2]/button")).click();//如果是true就点同意
            driver.findElement(By.xpath("html/body/div[3]/div[3]/button[1]")).click();//点击确定操作
        }
        else {
            driver.findElement(By.xpath(".//*[@id='orderInfoViewContainer']/em/li[3]/button")).click();//如果不是true就点拒绝
            driver.findElement(By.xpath("html/body/div[3]/div[3]/button[1]")).click();//点击确定操作
        }
    }
    public static void bindCode(WebDriver driver , String domain,String bigCode) throws InterruptedException {
        /**
         访问总部库存管理页面,并给第一款商品即“新商品0621”的第一个规格 “小红”绑定防伪码；
         */
        driver.get(domain+"/admin/agentProduct/index");//访问总部库存管理页面
//        driver.navigate().refresh();
        Thread.sleep(2000);
        WebElement manageCodes=driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr[2]/td[2]/div/span/a"));
        manageCodes.click();      //点击第一个的防伪码管理--新商品0621
        Thread.sleep(1000);
        driver.findElement(By.id("security_code_mgr_1550-1552")).click();//点击小红规格的防伪码管理链接
        Thread.sleep(1000);
        driver.findElement(By.xpath(".//*[@id='bar-item-button6']/button")).click();//点击防伪码绑定按键
        Thread.sleep(1000);
        driver.findElement(By.id("codes")).sendKeys(bigCode); //输入大码
        driver.findElement(By.xpath("html/body/div[7]/div[3]/button[1]")).click();//确定
    }
    public static int catchStock(WebDriver driver , String domain,String goodsName) throws InterruptedException {
        /**
         访问总部库存管理页面,并搜索商品goodsName，获取库存，返回值为库存数值；
         */
        driver.get(domain+"/admin/agentProduct/index");//访问总部库存管理页面
//        driver.navigate().refresh();
        Thread.sleep(2000);
//        WebElement searchDiv=driver.findElement(By.id("productName"));
//        Actions action=new Actions(driver);   //调用鼠标点击操作点出查询框
//        action.click(searchDiv).perform();
        driver.findElement(By.id("productName")).sendKeys(goodsName); //输入商品名称
        driver.findElement(By.xpath(".//*[@id='inventoryPanel']/div[1]/div[1]/div[2]/div[1]/div[1]/button")).click();//点击查询
        Thread.sleep(2000);
        String stringStock=driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr[2]/td[8]/div/span")).getText();
        int stock=Integer.parseInt(stringStock);
//        System.out.println(stock);
        return stock;

    }
    public static void sendGoods(WebDriver driver , String domain,String bigCode) throws InterruptedException {
        /**
         点击总部所有订单列表的第一个发货链接，并按给的大码发货
         */
        driver.get(domain+"/admin/wechatAgent/order/topOrderList?tabIndex=0");//访问总部所有订单列表页面
//        driver.navigate().refresh();
        List<WebElement> links=driver.findElements(By.linkText("发货"));//获取所有发货链接
        links.get(0).click();//点击第一个的发货
        Thread.sleep(1000);
        driver.findElement(By.linkText("录入")).click();//点击录入链接
        driver.findElement(By.id("code")).sendKeys(bigCode);//输入大码
        driver.findElement(By.id("J_BtnAddCode")).click(); //点击添加
        driver.findElement(By.xpath("html/body/div[11]/div[3]/button[1]")).click();//点击确定
        Thread.sleep(1000);
        driver.findElement(By.xpath("html/body/div[13]/div[3]/button")).click();//点提示成功提示框的确定
        Thread.sleep(1000);
        driver.findElement(By.xpath("html/body/div[11]/div[3]/button[1]")).click();//点确定发货
    }

}
