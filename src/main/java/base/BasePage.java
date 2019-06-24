package base;

import enums.SwipeDirection;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import logger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {
  protected AppiumDriver driver;

  public BasePage(AppiumDriver driver){
    this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }

  public void swipe(SwipeDirection direction, int duration) {
    Dimension size = driver.manage().window().getSize();
    int centerX = (int) (size.width * 0.5);
    int centerY = (int) (size.height * 0.5);
    int startX = centerX;
    int startY = centerY;
    int endX = centerX;
    int endY = centerY;

    if (direction == SwipeDirection.UP) {
      startY = (int) (size.height * 0.7);
      endY = (int) (size.height * 0.3);
    } else if (direction == SwipeDirection.DOWN) {
      startY = (int) (size.height * 0.3);
      endY = (int) (size.height * 0.7);
    } else if (direction == SwipeDirection.LEFT) {
      startX = (int) (size.width * 0.7);
      endX = (int) (size.width * 0.3);
    } else if (direction == SwipeDirection.RIGHT) {
      startX = (int) (size.width * 0.3);
      endX = (int) (size.width * 0.7);
    }

    TouchAction action = new TouchAction(driver)
        .press(PointOption.point(startX, startY))
        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
        .moveTo(PointOption.point(endX, endY))
        .release();
    action.perform();
    Reporter.log(String.format("Swipe %s.", direction));
  }

  public void swipe(SwipeDirection direction) {
    this.swipe(direction, 1000);
  }


  public void waitElemVisibility(MobileElement element) {
    WebDriverWait wait = new WebDriverWait(driver, 1);
    wait.until(ExpectedConditions.visibilityOf(element));
//    return element.isDisplayed();
  }

  public boolean isTextVisible(String text) {
    WebElement element = driver.findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
    return element.isDisplayed();
  }

   public void logAssert(MobileElement element, String successMsg, String failMsg) {
     if (element.isDisplayed()) {
       Log.info(successMsg);
     } else {
       Log.error(failMsg);
     }
   }

  public void screenshot(String folder, String name) throws IOException {
    File srcFile = driver.getScreenshotAs(OutputType.FILE);
    String fileName = name;
    File targetFile=new File("screenshots"+ File.separator + folder + File.separator + fileName +".png");
    FileUtils.copyFile(srcFile,targetFile);
  }

}
