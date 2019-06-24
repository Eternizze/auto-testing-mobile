package triviaapp.tests;

import base.BasePage;
import base.MobileTest;
import logger.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import triviaapp.data.LoginTestsData;
import triviaapp.pages.*;

import java.io.IOException;

public class LoginTests extends MobileTest {

  private LoginPhonePage loginPhonePage;
  private LoginOtpPage loginOtpPage;
  private ConfirmOtpPage confirmOtpPage;
  private HomePage homePage;

  @BeforeMethod
  public void beforeLoginTest() {
    WelcomePage welcome = new WelcomePage(driver);
    welcome.openLoginPhone();
    loginPhonePage = new LoginPhonePage(driver);
    loginOtpPage = new LoginOtpPage(driver);
    confirmOtpPage = new ConfirmOtpPage(driver);
    homePage = new HomePage(driver);
  }

  @Test
  public void verifyLoginPhonePage() throws IOException {
    Log.info("verifyLoginPhonePage test starts");
    Assert.assertEquals(loginPhonePage.getLoginHeader(), LoginTestsData.LOGIN_PHONE_HEADER);
    loginPhonePage.screenshot("LoginTests", "verifyLoginPhonePage");
  }

  @Test
  public void loginInvalidPhone() throws IOException {
    Log.info("loginInvalidPhone test starts");
    loginPhonePage.populatePhone(LoginTestsData.INVALID_PHONE);
    loginPhonePage.verifyPhoneError();
    loginPhonePage.screenshot("LoginTests", "loginInvalidPhone");
  }

  @Test
  public void loginInvalidOtp() throws IOException {
    Log.info("loginInvalidOtp test starts");
    loginPhonePage.loginPhone(LoginTestsData.LOGIN_PHONE);
    loginOtpPage.waitVisibleALert();
    loginOtpPage.clickOkAlertButton();
    loginOtpPage.populateOtp(LoginTestsData.WRONG_OTP);
    loginOtpPage.verifyError();
    loginOtpPage.screenshot("LoginTests", "loginInvalidOtp");
  }

  @Test
  public void login() throws IOException {
    Log.info("login test starts");
    loginPhonePage.loginPhone(LoginTestsData.LOGIN_PHONE);
    loginOtpPage.verifyOtpAlert();
    String code = loginOtpPage.getOtpCode();
    loginOtpPage.clickOkAlertButton();
    loginOtpPage.populateOtp(code);
    loginOtpPage.clickNextButton();
    confirmOtpPage.verifyConfirmText();
    homePage.verifyUserIsLoggedIn();
    homePage.screenshot("LoginTests", "login");
  }



}
