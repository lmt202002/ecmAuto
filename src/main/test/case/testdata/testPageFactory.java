import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

public class testPageFactory {

    public static void main(String[] args) {
        PageFacory searchPage;
        WebDriver driver =new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://www.baidu.com");
        searchPage =new BaiduSearchPage(driver);
        searchPage.inputText("selenium");
        searchPage.clickButton();
    }
}