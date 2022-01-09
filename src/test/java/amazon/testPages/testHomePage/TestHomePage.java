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

import static configuration.utilities.DataDriven.*;
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

  //  @Test
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
    // Data Driven Approach: using List
    // getItemValue() method from utilities.DataDriven
   // @Test(enabled = true)
    public void testCheckoutFunctionality1() throws InterruptedException {
        for (int i = 0; i <getItemValue().size() ; i++) {
            //getItemValue().get(i);
            homePage.enterSearchKeyword(getItemValue().get(i));
            homePage.clickOnSearchBox();
            Thread.sleep(5000);
            homePage.verifySearchKeyword("\""+getItemValue().get(i)+"\"");
            //homePage.verifySearchKeyword("\"Hand Sanitizer1\"");
        }
    }
    // Data Driven Approach: using database
    // i will need secret.properties + its path on loadProperties() method
    // i will need getItemsListFromDB("amazon_items","AmazonItems") which needs ConnectToSqlDB class
    @Test
    public void testCheckoutFunctionality2() throws Exception {
        for (int i = 0; i <getItemsListFromDB("amazon_items","AmazonItems").size() ; i++) {
            System.out.println("Size ==> "+getItemsListFromDB("amazon_items","AmazonItems").size());

            homePage.enterSearchKeyword(getItemsListFromDB("amazon_items","AmazonItems").get(i));
            homePage.clickOnSearchBox();
            Thread.sleep(3000);

            homePage.verifySearchKeyword("\""+getItemsListFromDB("amazon_items","AmazonItems").get(i)+"\"");
        }
    }

//    // // Data Driven Approach: using excel
//    @Test
//    public void testCheckoutFunctionality10() throws Exception {
//        for (int i = 0; i <getItemsListFromExcel().size() ; i++) {
//            System.out.println("Size ==> "+getItemsListFromExcel().size());
//
//            homePage.enterSearchKeyword(getItemsListFromExcel().get(i));
//            homePage.clickOnSearchBox();
//            Thread.sleep(3000);
//
//            homePage.verifySearchKeyword("\""+getItemsListFromExcel().get(i)+"\"");
//        }
//    }

}
