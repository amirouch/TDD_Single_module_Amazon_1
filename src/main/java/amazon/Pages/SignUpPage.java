package amazon.Pages;

//import com.amazon.common.base;
import configuration.common.base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static amazon.PageElements.SignUpPageElements.*;
//import static com.amazon.PageElements.SignUpPageElements.*;
//import static com.amazon.PageElements.SignUpPageElements.accountAndListsWebElement;

public class SignUpPage extends base {

    @FindBy(xpath =accountAndListsWebElement )
    public WebElement accountAndLists;

    public void clickOnAccountAndLists(){
        // accountAndLists.click();
        clickOnWebElementByXpath(accountAndLists);
    }
}
