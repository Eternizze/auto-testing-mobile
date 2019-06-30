package triviaapp.tests;

import base.MobileTest;
import logger.Log;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import triviaapp.data.TestsData;
import triviaapp.pages.*;

import java.io.IOException;

public class MyProfileTests extends MobileTest {

  private LoginPhonePage loginPhonePage;
  private LoginOtpPage loginOtpPage;
  private ConfirmOtpPage confirmOtpPage;
  private HomePage homePage;
  private MyProfilePage myProfilePage;
  private EmailPage emailPage;
  private SettingsPage settingsPage;
  private DifficultyPage difficultyPage;


  @BeforeMethod
  public void beforeLoginTest() {
//    WelcomePage welcome = new WelcomePage(driver);
//    welcome.openLoginPhone();
//    loginPhonePage = new LoginPhonePage(driver);
//    loginPhonePage.loginPhone(TestsData.LOGIN_PHONE);
//    loginOtpPage = new LoginOtpPage(driver);
//    loginOtpPage.login();
//    confirmOtpPage = new ConfirmOtpPage(driver);
    homePage = new HomePage(driver);

    myProfilePage = new MyProfilePage(driver);
    emailPage = new EmailPage(driver);
    settingsPage = new SettingsPage(driver);
    difficultyPage = new DifficultyPage(driver);
  }

  @Test(priority = 0)
  public void openMyProfilePage(){
    homePage.openMyProfile();
    myProfilePage.verifyMyProfileHeader();
    Log.info("My Profile is opened");
  }

  @Test(priority = 1)
  public void openEmailPage() throws IOException {
    myProfilePage.openEmailPage();
    emailPage.verifyEmailHeader();
    Log.info("Email Page is opened");
    emailPage.screenshot("MyProfile", "OpenEmail");
  }

  @Test(priority = 1)
  public void changeEmail(){
//    myProfilePage.openEmailPage();
    emailPage.populateEmail();
    emailPage.clickConfirm();
    myProfilePage.verifyMyProfileHeader();
    Log.info("My profile is opened");
  }

  @Test(priority = 1)
  public void openDifficultyPage() throws IOException {
    myProfilePage.openDifficultyPage();
    difficultyPage.verifyDifficultyHeader();
    Log.info("Difficulty Page is opened");
    emailPage.screenshot("MyProfile", "OpenDifficulty");
  }

  @Test(priority = 1)
  public void openSettingsPage() throws IOException {
    difficultyPage.clickBackButton();
    myProfilePage.openSettingsPage();
    settingsPage.verifySettingsHeader();
    Log.info("Settings Page is opened");
    emailPage.screenshot("MyProfile", "OpenSettings");
  }
}
