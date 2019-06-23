package triviaapp.tests;

import base.MobileTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import triviaapp.pages.LoginPhonePage;
import triviaapp.pages.WelcomePage;

public class LoginTests extends MobileTest {

  private LoginPhonePage loginPhonePage;

  @BeforeMethod
  public void beforeLoginTest() {
    WelcomePage welcome = new WelcomePage(driver);
    welcome.OpenLoginTel();
    loginPhonePage = new LoginPhonePage(driver);
  }


  @Test
  public void loginInvalidPhone(){
    loginPhonePage.loginPhone("456456");
  }


}
