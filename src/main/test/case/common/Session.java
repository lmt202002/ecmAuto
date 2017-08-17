package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by lvshr on 2017/8/15.
 */
public  class Session{
    public void main(){

    }
    public static void login(WebDriver driver , String domain){
        driver.get(domain+"/m/agent/admin/main");//访问登录页
        WebElement username=driver.findElement(By.id("username"));//
        WebElement password=driver.findElement(By.id("password"));
        WebElement login=driver.findElement(By.id("sub"));
        username.sendKeys("13055203099");//填写用户名
        password.sendKeys("111111");//填写密码
        login.click();//登录前台
    }
    public static void logout(WebDriver driver , String domain){
        driver.get(domain+"/m/agent/admin/adminLogout.html");//退出前台
    }
    public static void suLogin(WebDriver driver , String domain){
        driver.get(domain+"/admin/login");
        WebElement username=driver.findElement(By.id("username"));
        WebElement password=driver.findElement(By.id("password"));
        WebElement login=driver.findElement(By.id("sub"));
        username.sendKeys("admin");//填写用户名
        password.sendKeys("111111");//填写密码
        login.click();//登录后台
    }
    public static void suLogout(WebDriver driver , String domain){
        driver.get(domain+"/admin/adminLogout.htm");//退出后台
    }
}
