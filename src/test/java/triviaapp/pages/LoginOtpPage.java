package triviaapp.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import logger.Log;
import org.testng.Assert;
import triviaapp.data.ErrorTexts;
import triviaapp.data.TestsData;

public class LoginOtpPage extends BasePage {

  @AndroidFindBy(id = "android:id/alertTitle")
  private MobileElement otpCodeAlert;

  @AndroidFindBy(id = "android:id/button1")
  private MobileElement otpAlertButton;

  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
  private MobileElement otpHeader;

  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup")
  private MobileElement otpNextButton;

  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.ImageView")
  private MobileElement otpBackButton;

  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.TextView")
  private MobileElement resendButton;

  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView[2]")
  private MobileElement textWithPhone;

  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView[3]")
  private MobileElement textTimer;

  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.EditText")
  private MobileElement otpInput;

  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView[1]")
  private MobileElement otpError;

  public LoginOtpPage (AppiumDriver driver) {
    super(driver);
  }


  public void populateOtp(String code){
    otpInput.click();
    otpInput.sendKeys(code);
    Log.info(String.format("OTP typed %s in input form.", code));
  }

  public ConfirmOtpPage clickNextButton(){
    otpNextButton.click();
    Log.info("Next Button is cklicked");
    return new ConfirmOtpPage(driver);
  }

  public  void  verifyError(){
    Assert.assertEquals(getErrorText(), ErrorTexts.ERROR_OTP);
  }

  public String getErrorText() {
    return otpError.getText();
  }

  public void verifyOtpHeader() {
    Assert.assertEquals(getOtpHeader(), TestsData.OTP_HEADER);
  }

  public void waitVisibleALert(){
   waitElemVisibility(otpCodeAlert);
  }

  public void verifyOtpAlert(){
    Assert.assertTrue(otpCodeAlert.isDisplayed());
    logAssert(otpCodeAlert, "Alert is dispalyed", "OTP alert is not displayed");
  }

  public String getOtpHeader(){
    return otpHeader.getText();
  }
  public String getOtpCode(){
    return otpCodeAlert.getText();
  }
  public void clickOkAlertButton(){
    otpAlertButton.click();
    Log.info("OK button on the alert is tapped");
  }

  public void login(){
    String code = getOtpCode();
    clickOkAlertButton();
    populateOtp(code);
    clickNextButton();
  }

}
