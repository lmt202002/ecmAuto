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

public class TestCheckMyGetArc {
    public static void main(String[] args ) throws InterruptedException, IOException {

    }
    @Test
    public static  void testCheckMyGetArc() throws IOException, InterruptedException {
        /**前台微商检查我的团队业绩
         * 先判断CheckMyGetArc01.json文件是否存在，如果不存在，则继续
         * 并把返回的团队业绩、百分比、业绩所有人写入CheckMyGetArc01.json文件，设置状态值为：0，第一次检查时创建，1，第二次检查；
         * 操作顺序为，读取我的团队业绩返利，下单并审核通过，再读取我的业绩返利，然后比较两次返利的业绩总额是不是相差了审核的订单金额
         */
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String domain="http://o2oagent.ecmaster.cn";
        String wechat="atyj4";
        String userFilePath="E:\\github\\company\\src\\main\\test\\case\\common\\userData.json";
        String arcPath="E:\\github\\company\\src\\main\\test\\case\\testdata\\CheckMyGetArc01.json";
        String status="0";
        Map map= RWuserData.readUserData(userFilePath,wechat).toMap();//取出指定用户的数据为Map
        String userPhone=map.get("phone").toString();//从map中取出用户手机号
        Session.login(driver,domain,userPhone);//前台登录
        List list1;
        try {
            if (RWuserData.readTestData(arcPath,"项目3").equals("0"))       //CheckMyGetArc01.json文件存在没异常则判断读取次数，如果是0，即创建后没有再读取，则修改状态为1；
                status="1";
            list1= FgStep.catchAch(driver,domain);//查看我的团队业绩
            list1.add(wechat);//添加业绩所有人标记
            list1.add(status);            //如果检查过，则又会改为0
        }catch (Exception e){        //如果CheckMyGetArc01.json文件读取异常，即文件不存在，就读取并第一次创建
            list1= FgStep.catchAch(driver,domain);//查看我的团队业绩
            list1.add(wechat);//添加业绩所有人标记
            list1.add(status);
        }
//        System.out.println(list1.get(0)+"\n"+list1.get(1));
        RWuserData.writeTestJSON(list1,"CheckMyGetArc01");//写结果数据到CheckMyGetArc01.json
        Float arcOld=Float.parseFloat(list1.get(0).toString());//审核前的业绩
        System.out.println("审核前获取的金额："+arcOld);
        FgStep.getBill(driver,domain,wechat);//本人下单
        Session.logout(driver,domain);//本用户退出登录


        Map mapParent= RWuserData.readUserData(userFilePath,map.get("parent").toString()).toMap();//取出用户的上级数据为Map
        String userPhoneParent=mapParent.get("phone").toString();//从mapParent中取出用户上级手机号
        Session.login(driver,domain,userPhoneParent);//上级前台登录
        float money=Float.parseFloat(FgStep.auditOrder(driver,domain,true).get(0).toString());//上级审核通过，并返回订单金额

        Session.logout(driver,domain);//上级前台退出


        Session.login(driver,domain,userPhone);//下级再前台登录
        Float arcNow=arcOld+money;//旧的业绩+审核的订单金额=当前业绩   计算出的当前业绩
        Float arcNowReal=Float.parseFloat(FgStep.catchAch(driver,domain).get(0).toString());//实际获取的当前团队业绩
        System.out.println("审核后获取的："+arcNowReal);
        Session.logout(driver,domain);//前台退出
        driver.close();
        Assert.assertEquals(arcNow,arcNowReal);//比较计算的当前业绩和实际获取的当前业绩
    }
}
