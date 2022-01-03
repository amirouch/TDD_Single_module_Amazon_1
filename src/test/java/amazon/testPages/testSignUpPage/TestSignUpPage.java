package amazon.testPages.testSignUpPage;

import amazon.Pages.SignUpPage;
//import com.amazon.Pages.SignUpPage;
//import com.amazon.common.base;
import configuration.common.base;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestSignUpPage extends base {
    SignUpPage signUpPage;

    @BeforeMethod
    public void getInit() {
        signUpPage = PageFactory.initElements(driver, SignUpPage.class);
    }

    @Test
    public void testSignUp(){
        signUpPage.clickOnAccountAndLists();
    }

}
