package amazon.Pages;

//import com.amazon.PageElements.HomePageElements;

//import com.amazon.common.base;
import configuration.common.base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static amazon.PageElements.HomePageElements.*;
//import static com.amazon.PageElements.HomePageElements.*;
// THIS IMPORT ALL ELEMENT FROM HomePageElements CLASS

public class HomePage extends base {

    @FindBy(xpath = searchBoxWebElement)
    public WebElement searchBox;
    @FindBy(xpath = searchButtonWebElement)
    public WebElement searchButton;
    @FindBy(xpath = searchResultWebElement)
    public WebElement searchResult;
    @FindBy(xpath = eligibleForFreeShippingCheckBoxWebElement)
    public WebElement eligibleForFreeShippingCheckBox;
    @FindBy(xpath = climatePledgeFriendlyCheckBoxWebElement)
    public WebElement climatePledgeFriendlyCheckBox;
    @FindBy(xpath = purellAdvancedHandSanitizerWebElement)
    public WebElement purellAdvancedHandSanitizer;
    @FindBy(xpath = selectQtyWebElement)
    public WebElement selectQty;

    public void enterSearchKeyword(String productName) {
        searchBox.sendKeys(productName);
    }
    public void clickOnSearchBox() {
        searchButton.click();
    }
    public void verifySearchKeyword(String expectedKeyWord) {
        String actualSearchKeyword = searchResult.getText();
        Assert.assertEquals(actualSearchKeyword, expectedKeyWord, "Search keyword not match");
    }

    public void clickOnEligibleForFreeShippingCheckBox() {
        eligibleForFreeShippingCheckBox.click();
    }
    public void clickOnClimatePledgeFriendlyCheckBox() {
        climatePledgeFriendlyCheckBox.click();
    }
    public void clickOnPurellAdvancedHandSanitizer() {
        purellAdvancedHandSanitizer.click();
    }
    public void selectQtyForPurell(String qty){
        selectByVisibleTextFromSelect(selectQty,qty);  // my method is on base ===> I HAVE TO EXTEND TO base CLASS
    }



    //     @BeforeMethod
//    public void setUpBrowser1() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "BrowserDrivers/windows/chromedriver.exe");
//
//        driver = new ChromeDriver();
//        //homePage = PageFactory.initElements(driver, HomePage.class);
//
//        driver.manage().window().maximize();
//        driver.get("https://www.amazon.com/");
//        Thread.sleep(3000);
//
//    }

}
