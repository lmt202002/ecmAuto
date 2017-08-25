package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FgStep {
    public static void auditOrder(WebDriver driver , String domain, boolean status) throws InterruptedException {
        /**
         前台代理商审核下级订单，并按提供的参数点同意或拒绝
         */
        driver.get(domain+"/m/agent/admin/order/subOrderUnCheckList?limit=10&start=0");//访问待审核下级订单列表页面
        Thread.sleep(2000);
        List<WebElement> links=driver.findElements(By.linkText("审核"));//获取所有审核链接
        links.get(0).click();//点击第一个的审核
        Thread.sleep(2000);
//        driver.navigate().refresh();
        if (status) {
            driver.findElement(By.xpath(".//*[@id='page']/div[2]/div/div[6]/div/div/a[1]/span")).click();//如果是true就点同意
            driver.findElement(By.xpath("html/body/div[3]/div[3]/a[2]")).click();//点击确定操作
        }
        else {
            driver.findElement(By.xpath(".//*[@id='page']/div[2]/div/div[6]/div/div/a[2]/span")).click();//如果不是true就点拒绝
            driver.findElement(By.xpath("html/body/div[5]/div[3]/a[2]")).click();//点击确定操作
        }
    }
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
    public static List catchAchRebateGet(WebDriver driver , String domain,List<String> userList) throws InterruptedException {
        /**
         访问我的应收团队返利页面，获取我从目标的收取的团队返利，返回Float类型List ，包含返利金额、订单总额；
         */
        float rebate=0;
        float totals=0;
        List<WebElement> nameElement=new ArrayList<WebElement>(); //定义列表所有对象List
        List<Float> List1=new ArrayList<Float>();//定义返回值List
        driver.get(domain+"/m/agent/admin/performance/myBillList?limit=100&start=0");//访问我应收的团队返利页面
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
        driver.get(domain+"/m/agent/admin/performance/myBillList?limit=1&start="+goalnum+"");//重新访问我应收的团队返利页面，从第i条开始只显示1条，即只过滤出指定的记录，0为第一条
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

    public static List recommendRebateGet(WebDriver driver , String domain,List<String> userList) throws InterruptedException {
        /**
         访问我的应付推荐返利页面，获取我应付给目标(userlist)的推荐返利，返回Float类型List ，包含应付目标返利金额、子订单返利比例、子订单返利金额、子订单订单总额；
         */
        float rebate=0;//外部记录的返利金额
        float percent=0;//明细中订单记录的返利比例
        float subRebate=0;//明细中订单记录的返利金额
        float subtotal=0;//明细中订单记录的订单总额
        List<WebElement> nameElement=new ArrayList<WebElement>(); //定义列表所有对象List
        List<Float> List1=new ArrayList<Float>();//定义返回值List
        driver.get(domain+"/m/agent/admin/recommendRebate/myBillIndex?limit=100&start=0");//访问我应收的推荐返利页面
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
        driver.get(domain+"/m/agent/admin/recommendRebate/myBillIndex?limit=1&start="+goalnum+"");//重新访问我应收的推荐返利页面，从第i条开始只显示1条，即只过滤出指定的记录，0为第一条
        Thread.sleep(1000);
        try {
            rebate=Float.parseFloat(driver.findElement(By.xpath(".//*[@id='grid']/ul/li/div[2]/span/span/em")).getText());//获取返利金额字符串，并转为浮点型
//            totals=Float.parseFloat(driver.findElement(By.xpath(".//*[@id='page']/div[2]/div/div/ul/li/div[2]/span[2]/span/em")).getText());//获取订单总额字符串，并转为浮点型
        }catch (Exception e){
            List1.add(rebate);
            List1.add(percent);
            List1.add(subRebate);
            List1.add(subtotal);
            return List1;
        }


        driver.findElement(By.xpath(".//*[@id='grid']/ul/li/a/h3")).click();//进入明细页面
        percent=Float.parseFloat(driver.findElement(By.xpath(".//*[@id='grid']/div[1]/ul/li/div[2]/div[2]")).getText().split("%")[0].split("：")[1]);//获取明细中最新一条订单的返利比例字符串后再截取出值，并转为浮点型
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
