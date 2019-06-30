package triviaapp.tests;

import base.MobileTest;
import logger.Log;
import org.testng.Assert;
import org.testng.annotations.Test;
import triviaapp.pages.WelcomePage;

/**
 * Tests for system operations.
 */
public class SystemTests extends MobileTest {

  private String bundleId = "my.bigwin.com";
//  private WelcomePage welcome = new WelcomePage(driver);

  @Test(priority = 2)
  public void verifyAppIsInstalled(){
    Assert.assertTrue(driver.isAppInstalled(bundleId), "The App is not installed");
    Log.info("App is installed");
  }

  @Test(priority = 2)
  public void unistallApp(){
    driver.removeApp(bundleId);
    Assert.assertFalse(driver.isAppInstalled(bundleId),"The app is not uninstalled");
    Log.info("App is not installed");
  }
}
