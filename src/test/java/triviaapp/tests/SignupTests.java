package triviaapp.tests;

import base.MobileTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import triviaapp.data.TestsData;
import triviaapp.pages.*;

import java.io.IOException;

public class SignupTests extends MobileTest {

  private LoginPhonePage loginPhonePage;
  private LoginOtpPage loginOtpPage;
  private ConfirmOtpPage confirmOtpPage;
  private HomePage homePage;
  private CreateProfilePage createProfilePage;
  private WalkthroughIntroPage walkthroughIntroPage;

  @BeforeMethod
  public void beforeSignupTest() {
    WelcomePage welcome = new WelcomePage(driver);
    welcome.openLoginPhone();
    loginPhonePage = new LoginPhonePage(driver);
    loginOtpPage = new LoginOtpPage(driver);
    confirmOtpPage = new ConfirmOtpPage(driver);
    createProfilePage = new CreateProfilePage(driver);
    homePage = new HomePage(driver);
    walkthroughIntroPage = new WalkthroughIntroPage(driver);
  }

  @Test
  public void register() throws IOException {
    loginPhonePage.loginPhone(TestsData.SIGNUP_PHONE);
    String code = loginOtpPage.getOtpCode();
    loginOtpPage.clickOkAlertButton();
    loginOtpPage.populateOtp(code);
    loginOtpPage.clickNextButton();
    createProfilePage.waitCreateProfile();
    createProfilePage.verifyCreateProfileHeader();
    createProfilePage.populateNickname(TestsData.REGISTER_NICKNAME);
    createProfilePage.verifyUniqueNickname();
    createProfilePage.chooseState();
    createProfilePage.chooseAvatar();
    createProfilePage.clickContinueButton();
    walkthroughIntroPage.waitVisibleIntro();
    walkthroughIntroPage.clickSkipButton();
    homePage.verifyUserIsRegistered(TestsData.REGISTER_NICKNAME);
    homePage.screenshot("SignupTests","register");
  }

}
