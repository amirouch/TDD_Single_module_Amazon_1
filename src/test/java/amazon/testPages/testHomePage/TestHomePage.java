package amazon.testPages.testHomePage;

import amazon.Pages.HomePage;
//import com.amazon.Pages.HomePage;
//
//import com.amazon.common.base;
import configuration.common.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import static com.amazon.common.base.driver;  // since the driver is declared on base class public static,
                                               // we just import the driver , we dont need to extend base
                                              //  ****************  Alt+Enter  ***********************  1 /////////////

//public class TestHomePage  extends base{ // extends base to get the driver
public class TestHomePage extends base {

    HomePage homePage;

   // WebDriver driver;              // create a field 'driver' in TestHomePage ************  2 ///////////////////////
                                     // 1 or 2 ===> same concept
@BeforeMethod
public void getInit() {
     homePage = PageFactory.initElements(driver, HomePage.class);
}

@Test
public void testSearchBoxFunctionality() {
    homePage.enterSearchKeyword("Hand Sanitizer");
    homePage.clickOnSearchBox();
    homePage.verifySearchKeyword("\"Hand Sanitizer\"");
}

    @Test
    public void testCheckoutFunctionality() throws InterruptedException {
        //testSignIn();  // my method is on base ===> I HAVE TO EXTEND TO base CLASS
        homePage.enterSearchKeyword("Hand Sanitizer");
        homePage.clickOnSearchBox();
        //homePage.verifySearchKeyword("\"Hand Sanitizer\"");
        Thread.sleep(3000);
        homePage.clickOnEligibleForFreeShippingCheckBox();
        homePage.clickOnClimatePledgeFriendlyCheckBox();
        homePage.clickOnPurellAdvancedHandSanitizer();
        homePage.selectQtyForPurell("4");
        waitFor(5);  // my method is on base ===> I HAVE TO EXTEND TO base CLASS

    }

}
