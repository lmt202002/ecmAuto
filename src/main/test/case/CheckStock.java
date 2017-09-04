import common.FgStep;
import common.RWuserData;
import common.Session;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CheckStock {
    public static void main(String[] args ) throws InterruptedException, IOException {
        /**前台微商检查指定商品库存
         * 先判断CheckStock01.json文件是否存在，如果不存在，则继续
         * 并把返回的商品名称、已收库存、待收库存、订单号写入CheckStock01.json文件，设置状态值为：0，第一次检查时创建，1，第二次检查；
         */
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String domain="http://o2oagent.ecmaster.cn";
        String wechat="atyj4";
        String userFilePath="E:\\github\\company\\src\\main\\test\\case\\common\\userData.json";
        String stockPath="E:\\github\\company\\src\\main\\test\\case\\testdata\\CheckStock01.json";
        String goodsName="新商品0621";
        String status="0";
        Map map= RWuserData.readUserData(userFilePath,wechat).toMap();//取出指定用户的数据为Map
        String userPhone=map.get("phone").toString();//从map中取出用户手机号
        Session.login(driver,domain,userPhone);//前台登录
//        List<String> userList=new ArrayList<String>();
        List<String> list1;
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


        RWuserData.writeTestJSON(list1,"CheckStock01");//写结果数据到CheckStock01.json

        Session.logout(driver,domain);//前台退出
        driver.close();
    }
}
