import common.BgStep;
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
         * 先从CheckStockStatus.json文件读取是否检查过状态，如图没检查过，就检查当前库存
         * 并把返回的商品名称、已收库存、待收库存、订单号写入CheckStock01.json文件
         * 并更新CheckStockStatus.json的检查状态，状态值为：0，未检查，1，检查一次，2检查第二次；
         */
        WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String domain="http://o2oagent.ecmaster.cn";
        String wechat="atyj4";
        String userFilePath="E:\\github\\company\\src\\main\\test\\case\\testdata\\CheckStockStatus.json";
        Map map= RWuserData.readUserData(userFilePath,wechat).toMap();//取出指定用户的数据为Map
        String userPhone=map.get("phone").toString();//从map中取出用户手机号
        Session.login(driver,domain,userPhone);//前台登录
//        List<String> userList=new ArrayList<String>();
        List<String> list1= FgStep.getBill(driver,domain);//下采购单
        list1.add(wechat);

        RWuserData.writeTestJSON(list1,"TestAddBill01");//写结果数据到TestAddBill01.json

        Session.logout(driver,domain);//前台退出
        driver.close();
    }
}
