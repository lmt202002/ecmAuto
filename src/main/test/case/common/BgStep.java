package common;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class BgStep {
    public static String addCode(WebDriver driver , String domain) throws InterruptedException {
        /**后台防伪码数据管理页面，生成防伪码
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
        /**后台总部审核订单
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
        /**后台绑定防伪码
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
        /**后台总部库存列表获取指定商品库存
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
        /**后台发货
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

    public static boolean changeRelations(WebDriver driver , String domain, List<String> list) throws InterruptedException {
        /**后台修改微商资料
         根据给的微商关系List，设置指定微商的等级、上级、推荐人，并返回是否成功
         list.get(0)为当前用户微信号,后续三个分别为微商的等级、上级、推荐人
         上级格式为:at董事，推荐人格式为：at总裁1(atzc1)
         */
        try {
            driver.get(domain+"/admin/wechatAgent/list");//访问总部微商列表页面
            driver.findElement(By.id("short_cut_like_orderNumber_")).sendKeys(list.get(0));// 输入微信号
            driver.findElement(By.xpath("html/body/div[1]/div[2]/div[1]/div[1]/button")).click();//点击搜索
            driver.findElement(By.xpath("html/body/div[2]")).click();//点击隐藏取消层
            driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr[2]/td[2]/div/span/a[1]")).click();//点击编辑
            Select sel1=new Select(driver.findElement(By.id("angetLv")));
            sel1.selectByVisibleText(list.get(1)); //选择会员等级
            driver.findElement(By.xpath(".//*[@id='select2-angetChlidLv-container']")).click();//点击上级搜索下拉列表
            driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys(list.get(2));//在上级搜索框输入上级姓名
            driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);//搜索框ENTER键选择
            Select sel2=new Select(driver.findElement(By.id("referrer")));
            sel2.selectByVisibleText(list.get(3));//选择推荐人
//            Actions actions=new Actions(driver);
            driver.findElement(By.xpath(".//*[@id='basicInfoForm']/div[1]/button")).click();
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    public static List getTeamArcRebate(WebDriver driver , String domain, String wechat) throws InterruptedException {
        /**后台团队业绩返利菜单
         根据给的微商微信号，搜索返利记录，并过滤出记录，返回最新记录的订单总额、返利比例、返利总额，是否正常，1为正常，0为不正常
         */
        float orderMoney=0;
        float percent=0;
        float rebate=0;
        float status=1;
        List<Float> resultList=new ArrayList<Float>();
        try {
            driver.get(domain+"/admin/teamPerformance/index");//访问团队业绩返利报表页面
            driver.findElement(By.name("agentName")).sendKeys(wechat);// 输入微信号
            driver.findElement(By.id("btnSearch")).click();//点击搜索
            List<WebElement> trlist=new ArrayList<WebElement>();
            trlist=driver.findElements(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr")); //获取表格区tr元素对象list

            int size=trlist.size();
            Thread.sleep(1000);
            orderMoney=Float.parseFloat(driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr["+size+"]/td[4]/div/span")).getText().substring(1));//取订单总额字符串,再截取数值部分，再转为Fload型
            percent=Float.parseFloat(driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr["+size+"]/td[5]/div/span")).getText().split("%")[0]);//取返点比例字符串,再截取数值部分，再转为Fload型
            rebate=Float.parseFloat(driver.findElement(By.xpath(".//*[@id='grid']/div/div[3]/table/tbody/tr["+size+"]/td[6]/div/span")).getText().substring(1));//取返利总额字符串,再截取数值部分，再转为Fload型
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
            return resultList;
        }
        return resultList;
    }

    public static List sameLevelRebate(WebDriver driver , String domain, String wechat) throws InterruptedException {
        /**后台平缓推荐返利菜单
         根据给的微商微信号，搜索返利记录，并过滤出记录，返回最近的统计中，明细页面最新子订单记录的订单总额、返利比例、返利总额，是否正常，1为正常，0为不正常
         */
        float orderMoney=0;
        float percent=0;
        float rebate=0;
        float status=1;
        List<Float> resultList=new ArrayList<Float>();
        try {
            driver.get(domain+"/admin/agentSameRecommend/index");//访问团队业绩返利报表页面
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
//                System.out.println(resultList);
            return resultList;
        }
//        System.out.println(resultList);
        return resultList;
    }

    public static List onceRebate(WebDriver driver , String domain, String wechat) throws InterruptedException {
        /**后台一次性推荐返利菜单
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
//            System.out.println(rebate);
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
//            System.out.println(resultList);
            return resultList;
        }
//        System.out.println("最新子订单总额："+orderMoney+"\n返点比例："+percent+"\n返利金额："+rebate+"\n是否成功："+status);
        return resultList;
    }
}
