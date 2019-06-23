package triviaapp.tests;

import base.MobileTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import triviaapp.data.ErrorTexts;
import triviaapp.data.LoginTestsData;
import triviaapp.pages.*;

public class LoginTests extends MobileTest {

  private LoginPhonePage loginPhonePage;
  private LoginOtpPage loginOtpPage;
  private ConfirmOtpPage confirmOtpPage;
  private HomePage homePage;

  @BeforeMethod
  public void beforeLoginTest() {
    WelcomePage welcome = new WelcomePage(driver);
    welcome.OpenLoginPhone();
    loginPhonePage = new LoginPhonePage(driver);
    loginOtpPage = new LoginOtpPage(driver);
    confirmOtpPage = new ConfirmOtpPage(driver);
    homePage = new HomePage(driver);
  }

  @Test
  public void verifyLoginPhonePage(){
    Assert.assertEquals(loginPhonePage.getLoginHeader(), LoginTestsData.LOGIN_PHONE_HEADER);
  }

  @Test
  public void loginInvalidPhone(){
    loginPhonePage.populatePhone(LoginTestsData.INVALID_PHONE);
    loginPhonePage.verifyPhoneError();
  }
  @Test
  public void loginInvalidOtp(){
    loginPhonePage.populatePhone(LoginTestsData.LOGIN_PHONE);
    loginPhonePage.verifyPhoneError();
  }

  @Test
  public void login(){
    loginPhonePage.loginPhone(LoginTestsData.LOGIN_PHONE);
    loginOtpPage.verifyOtpAlert();
    String code = loginOtpPage.getOtpCode();
    loginOtpPage.clickOkAlertButton();
    loginOtpPage.populateOtp(code);
    loginOtpPage.clickNextButton();
    confirmOtpPage.verifyConfirmText();
    homePage.verifyUserIsLoggedIn();
  }


}
