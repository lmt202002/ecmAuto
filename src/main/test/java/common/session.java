package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by lvshr on 2017/8/15.
 */
public  class session{
    public void main(){

    }
    public static void login(WebDriver driver , String domain){
        driver.get(String.valueOf(domain)+"/m/agent/admin/main");
        WebElement username=driver.findElement(By.id("username"));
        WebElement password=driver.findElement(By.id("password"));
        WebElement login=driver.findElement(By.id("sub"));
        username.sendKeys("13055203099");
        password.sendKeys("111111");
        login.click();
    }
    public static void logout(WebDriver driver , String domain){
        driver.get(String.valueOf(domain)+"/m/agent/admin/adminLogout.html");
    }
}
