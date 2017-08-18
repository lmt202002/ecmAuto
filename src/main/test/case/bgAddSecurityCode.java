import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class bgAddSecurityCode {
    public static String addCode(WebDriver driver , String domain) throws InterruptedException {
        driver.get(domain+"/admin/securityCode/list");//访问防伪码数据管理页面
//        driver.navigate().refresh();
        Thread.sleep(3000);
        //点击生成防伪码按键
        driver.findElement(By.xpath(".//*[@id='bar-item-button7']/button")).click();
        // 设置要生成的大码数量
        driver.findElement(By.id("bigCount")).sendKeys("1");
        driver.findElement(By.id("smallCount")).sendKeys("60");//设置一个大码有多少个小码
        driver.findElement(By.xpath("html/body/div[7]/div[3]/button[1]")).click();
        //点出查询框
        WebElement searchDiv=driver.findElement(By.id("codeStr"));
        Actions action=new Actions(driver);
        action.click(searchDiv).perform();
        //设置过滤条件为查询60个小码的大码，未绑定，生成
        driver.findElement(By.name("nums")).sendKeys("60");
        Select sel=new Select(driver.findElement(By.xpath(".//*[@id='sourceType']")));
        sel.selectByValue("GENERATE");//选择条件为生成
        Select sel2=new Select(driver.findElement(By.xpath(".//*[@id='status']")));
        sel2.selectByVisibleText("未绑定");//选择条件为未绑定
        driver.findElement(By.id("btnSearch")).click();//点击搜索
        List<WebElement> bigCodeList=driver.findElements(By.className("bui-grid-cell-text"));
        String bigCode=bigCodeList.get(0).getText();
        return bigCode;
    }
}
