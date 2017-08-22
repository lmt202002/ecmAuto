import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class bgCatchStock {
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
}