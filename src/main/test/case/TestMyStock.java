import common.FgStep;
import common.RWuserData;
import common.Session;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestMyStock {
    public static void main(String[] args ) throws InterruptedException, IOException {

    }
    @Test
    public static  void testCheckStock() throws IOException, InterruptedException {
        /**前台微商检查指定商品已收库存变化
         * 先判断CheckStock02.json文件是否存在，如果不存在，则继续
         * 并把返回的商品名称、已收库存、订单号写入CheckStock02.json文件，设置状态值为：0，第一次检查时创建，1，第二次检查；
         * 操作顺序为，读取库存，审核通过，再读取库存，然后比较两次库存是不是相差了审核的数量
         */
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String domain="http://o2oagent.ecmaster.cn";
        String wechat="atyj4";
        String userFilePath="E:\\github\\company\\src\\main\\test\\case\\common\\userData.json";
        String stockPath="E:\\github\\company\\src\\main\\test\\case\\testdata\\CheckStock02.json";
//        String goodSnumPath="E:\\github\\company\\src\\main\\test\\case\\testdata\\TestFgAuditOrder01.json";
        String goodsName="新商品0621";
        String status="0";
        String code="8103211325147762";//设置上级要发货的大码
        Map map= RWuserData.readUserData(userFilePath,wechat).toMap();//取出指定用户的数据为Map
        String userPhone=map.get("phone").toString();//从map中取出用户手机号
        Session.login(driver,domain,userPhone);//前台登录
        List list1;
        try {
            if (RWuserData.readTestData(stockPath,"项目4").equals("0"))       //CheckStock01.json文件存在没异常则判断读取次数，如果是0，即创建后没有再读取，则修改状态为1；
                status="1";
            list1= FgStep.catchStock(driver,domain,goodsName);//查看库存
            list1.add(goodsName);//添加库存商品名称标记
            list1.add(wechat);//添加库存所有人标记
            list1.add(status);                                  //如果检查过，则又会改为0
        }catch (Exception e){        //如果CheckStock01.json文件读取异常，即文件不存在，就读取并第一次创建
            list1= FgStep.catchStock(driver,domain,goodsName);//查看库存
            list1.add(goodsName);//添加库存商品名称标记
            list1.add(wechat);//添加库存所有人标记
            list1.add(status);
        }
//        System.out.println(list1.get(0)+"\n"+list1.get(1));
        RWuserData.writeTestJSON(list1,"CheckStock02");//写结果数据到CheckStock02.json
        Float stockOld=Float.parseFloat(list1.get(0).toString());//审核前的已收库存

        Session.logout(driver,domain);//本用户退出登录
        Map mapParent= RWuserData.readUserData(userFilePath,map.get("parent").toString()).toMap();//取出用户的上级数据为Map
        String userPhoneParent=mapParent.get("phone").toString();//从mapParent中取出用户上级手机号
        Session.login(driver,domain,userPhoneParent);//上级前台登录
        String goodsNum=FgStep.sendGoods(driver,domain,code);//上级发货，并返回发货数量
        Session.logout(driver,domain);//上级前台退出


        Session.login(driver,domain,userPhone);//下级再前台登录
        Float stockNow=stockOld+Float.parseFloat(goodsNum);//旧的已收库存数量+审核的商品数量=当前已收库存数量   计算出的当前已收库存
//        System.out.println(stockOld);
        Float stockNowReal=Float.parseFloat(FgStep.catchStock(driver,domain,goodsName).get(0).toString());//实际获取的当前已收库存

        Session.logout(driver,domain);//前台退出
        driver.close();
        Assert.assertEquals(stockNow,stockNowReal);//比较计算的当前已收库存数量和获取的当前已收库存数量
    }
}
