package triviaapp.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;
import triviaapp.data.Texts;

public class ConfirmOtpPage extends BasePage {

  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[1]")
  private MobileElement confirmText;

  public ConfirmOtpPage (AppiumDriver driver) {
    super(driver);
  }

  public String getConfirmtext(){
    return confirmText.getText();
  }

  public void verifyConfirmText(){
    waitElemVisibility(confirmText);
    Assert.assertEquals(getConfirmtext(), Texts.CONFIRM_TEXT);
  }

}
