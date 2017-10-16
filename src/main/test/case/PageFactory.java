import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageFctory {
    WebDriver driver;
    @FindBy(id = "kw")
    @CacheLookup
    WebElement searchField;
    @FindBy(id = "su")
    @CacheLookup
    WebElement searchButton;
    public void BaiduSearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public void inputText(String search){
        searchField.sendKeys(search);
    }
    public void clickButton(){
        searchButton.click();
    }
}