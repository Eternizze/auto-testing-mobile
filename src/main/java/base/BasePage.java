package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {
  protected AppiumDriver driver;

  public BasePage(AppiumDriver driver){
    this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver), this);
  }
//napishi wait-a po-smislenoo, i da e otdelen metod, kakto da ima proverka za nalichie na elementa
  public boolean isElVisible(MobileElement element) {
//    element = (MobileElement) driver.findElement(By.xpath(xpath));
    WebDriverWait wait = new WebDriverWait(driver, 1);
    wait.until(ExpectedConditions.visibilityOf(element));
    return element.isDisplayed();
  }

}
