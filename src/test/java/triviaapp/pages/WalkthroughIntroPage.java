package triviaapp.pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import logger.Log;
import org.testng.Assert;
import triviaapp.data.Texts;

public class WalkthroughIntroPage extends BasePage {

  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView")
  private MobileElement dragonText;

  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView")
  private MobileElement okButton;

  @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView")
  private MobileElement skipButton;

  public WalkthroughIntroPage (AppiumDriver driver) {
    super(driver);
  }

  public String getDragonText (){
    return dragonText.getText();
  }

  public void waitVisibleIntro(){
    waitElemVisibility(dragonText);
    Log.info("Intro screen is loaded");
  }

  public void clickSkipButton(){
    skipButton.click();
    Log.info("Skip button is clicked");
  }

  public void verifyDragonText(){
    Assert.assertEquals(getDragonText(), Texts.INTRO_WALKTHROUGH, "Does not match");
  }
}
