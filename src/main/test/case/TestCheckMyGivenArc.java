import common.FgStep;
import common.RWuserData;
import common.Session;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestCheckMyGivenArc {
    WebDriver driver=new ChromeDriver();
    @Test
    public   void testCheckMyGivenArc() throws IOException, InterruptedException {
        /**前台微商检查我的应付目标用户的团队业绩返利
         * 先判断CheckMyGivenArc01.json文件是否存在，如果不存在，则继续
         * 并把返回的应付团队业绩、百分比、业绩所有人写入CheckMyGivenArc01.json文件，设置状态值为：0，第一次检查时创建，1，第二次检查；
         * 操作顺序为，读取我的应付团队业绩返利，下单并审核通过，再读取我的业绩返利，然后比较两次返利的业绩总额是不是相差了审核的订单金额
         */
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String domain="http://o2oagent.ecmaster.cn";
        String wechat="atyj4";
        String userFilePath="E:\\github\\company\\src\\main\\test\\case\\common\\userData.json";
        String arcPath="E:\\github\\company\\src\\main\\test\\case\\testdata\\CheckMyGivenArc01.json";
        String status="0";
        Map map= RWuserData.readUserData(userFilePath,wechat).toMap();//取出指定用户的数据为Map
        String userPhone=map.get("phone").toString();//从map中取出用户手机号
        Session.login(driver,domain,userPhone);//前台登录
        FgStep.getBill(driver,domain,wechat);//本人下单
        Session.logout(driver,domain);//本用户退出登录

        Map mapParent= RWuserData.readUserData(userFilePath,map.get("parent").toString()).toMap();//取出用户的上级数据为Map
        String userPhoneParent=mapParent.get("phone").toString();//从mapParent中取出用户上级手机号


        List<String> userlist=new ArrayList<String>();  //存储用户姓名和微信号

        List list1=new ArrayList();  //存储要写入json文件的各项值
        List list2=new ArrayList();  //存储审核后的各项值
        String reference=map.get("reference").toString();


        if (reference.equals("")) {
            /**如果推荐人为空，则查找上级应付给本人的团队业绩返利
             *
             */
            Session.login(driver,domain,userPhoneParent);//上级前台登录
            try {
                if (RWuserData.readTestData(arcPath,"项目4").equals("0"))       //CheckMyGivenArc01.json文件存在没异常则判断读取次数，如果是0，即创建后没有再读取，则修改状态为1；
                    status="1";
                userlist.add(map.get("name").toString());//添加本人姓名
                userlist.add(wechat);//添加本人微信号
                list1 = FgStep.catchAchRebateGiven(driver, domain, userlist);//上级查看应付团队业绩列表中应付给本人的返利
                list1.add(map.get("parent").toString());//添加上级微信号，即返利付款人的微信号，不是返利付款目标微信号
                list1.add(status);//设置读取状态
            }catch (Exception e){        //如果CheckMyGivenArc01.json文件读取异常，即文件不存在，就读取并第一次创建
                userlist.add(map.get("name").toString());//添加本人姓名
                userlist.add(wechat);//添加本人微信号
                list1.add("0");//返利比例、返利金额、订单总额都是0
                list1.add("0");
                list1.add("0");
                list1.add(map.get("parent").toString());//添加上级微信号，即返利付款人的微信号，不是返利付款目标微信号
                list1.add(status);
            }
            RWuserData.writeTestJSON(list1,"CheckMyGivenArc01");//写结果数据到CheckMyGivenArc01.json
            float arcOld=Float.parseFloat(list1.get(2).toString());//审核前的业绩总额
            System.out.println("审核前上级获取的应付给我的团队业绩总额：(不是返利，是业绩)"+arcOld);
            float money=Float.parseFloat(FgStep.auditOrder(driver,domain,true).get(0).toString());//上级审核通过，并返回订单金额
            list2=FgStep.catchAchRebateGiven(driver,domain,userlist);//再次获取审核后的应付团队业绩信息
            float percentNow=Float.parseFloat(list2.get(0).toString());//获取当前返利比例
            float arcRebateNow=(arcOld+money)*percentNow/100; //(审核的订单金额+旧的总业绩金额)*当前返利比例=计算出的当前业绩返利
            float arcRebateNowReal=Float.parseFloat(list2.get(1).toString());//实际获取的当前应付团队业绩
            System.out.println("审核后获取的："+arcRebateNowReal);
            Assert.assertEquals(arcRebateNowReal,arcRebateNow);//比较计算的当前应付业绩返利和实际获取的当前应付业绩返利
        }
        else{

            /**
             * 如果推荐人不为空，则查找推荐人应付给本人的团队业绩返利
             */
//            Map mapReference = new HashMap(map); //初始化用来取值的用户map
////            HashMap map1 = new HashMap();
////            HashMap map2 = new HashMap();
////            for(Iterator it = map1.keySet().iterator() ; it.hasNext();){
////                String key = it.next().toString();
////                map2.put(key, map1.get(key));
////            }
//            do {
//                mapReference= RWuserData.readUserData(userFilePath,mapReference.get("reference").toString()).toMap();// 循环取出用户的推荐人数据为Map
//            }while (!mapReference.get(reference).toString().equals("")); //判断推荐人是否为空，如果为空则到了最源头推荐人，此时mapReference就是最源头推荐人的数据
            Map mapReference= RWuserData.readUserData(userFilePath,map.get("reference").toString()).toMap();// 取出用户的推荐人数据为Map
            String userPhoneReference=mapReference.get("phone").toString();//从mapReference中取出推荐人手机号
            Session.login(driver,domain,userPhoneReference);//推荐人前台登录

            try {
                if (RWuserData.readTestData(arcPath,"项目4").equals("0"))       //CheckMyGivenArc01.json文件存在没异常则判断读取次数，如果是0，即创建后没有再读取，则修改状态为1；
                    status="1";
                userlist.add(map.get("name").toString());//添加本人姓名
                userlist.add(map.get("wechat").toString());//添加本人微信号
                list1 = FgStep.catchAchRebateGiven(driver, domain, userlist);//推荐人应付给我的返利
                list1.add(map.get("reference").toString());//添加推荐人微信号，即返利付款人的微信号，不是返利付款目标微信号
                list1.add(status);//设置读取状态
            }catch (Exception e){        //如果CheckMyGivenArc01.json文件读取异常，即文件不存在，就读取并第一次创建
                userlist.add(map.get("name").toString());//添加推荐人姓名
                userlist.add(map.get("wechat").toString());//添加推荐人微信号
                list1 = FgStep.catchAchRebateGiven(driver, domain, userlist);//推荐人应付给我的返利
                list1.add(map.get("reference").toString());//添加推荐人微信号，即返利付款人的微信号，不是返利付款目标微信号
                list1.add(status);
            }
            RWuserData.writeTestJSON(list1,"CheckMyGivenArc01");//写结果数据到CheckMyGivenArc01.json

            float arcOld=Float.parseFloat(list1.get(2).toString());//审核前的业绩总额
            System.out.println("审核前推荐人应付给我的团队业绩总额：(不是返利，是业绩)"+arcOld);
            Session.logout(driver,domain);//推荐人退出登录
            Session.login(driver,domain,userPhoneParent);//上级登录
            float money=Float.parseFloat(FgStep.auditOrder(driver,domain,true).get(0).toString());//上级审核通过，并返回订单金额
            Session.logout(driver,domain);//上级退出登录
            Session.login(driver,domain,userPhoneReference);//推荐人登录
            list2=FgStep.catchAchRebateGiven(driver,domain,userlist);//再次获取审核后的应付团队业绩信息
            float percentNow=Float.parseFloat(list2.get(0).toString());//获取当前返利比例
            float arcRebateNow=(arcOld+money)*percentNow/100; //(审核的订单金额+旧的总业绩金额)*当前返利比例=计算出的当前业绩返利
            float arcRebateNowReal=Float.parseFloat(list2.get(1).toString());//实际获取的当前应付团队业绩
            System.out.println("审核后获取的："+arcRebateNowReal);
            Assert.assertEquals(arcRebateNowReal,arcRebateNow);//比较计算的当前应付业绩返利和实际获取的当前应付业绩返利

        }
        Session.logout(driver,domain);//前台退出

    }
    @AfterClass
    public void teardown()
    {
        driver.close();
    }
}
