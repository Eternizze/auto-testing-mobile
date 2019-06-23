package triviaapp.tests;

import base.MobileTest;
import org.openqa.selenium.ScreenOrientation;
import org.testng.annotations.Test;
import triviaapp.pages.WelcomePage;

/**
 * Tests for system operations.
 */
public class SystemTests extends MobileTest {

  private WelcomePage welcome = new WelcomePage(driver);

  @Test
  public void rotateHomeScreen() {
    driver.rotate(ScreenOrientation.PORTRAIT);
  }

  @Test
  public void runInBackground(){

  }
}
