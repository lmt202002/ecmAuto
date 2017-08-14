import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created by lvshr on 2017/3/23.
 */

public class test1 {
    public static void main(String[] args ){
        WebDriver driver=new FirefoxDriver();
        driver.get("http://www.baidu.com");
        WebElement searchbox=driver.findElement(By.id("kw"));
        searchbox.sendKeys("selenium java");
        WebElement searchButton=driver.findElement(By.id("su"));
        searchButton.submit();
        System.out.print("lallllllllll");

//        driver.close();
    }
}
