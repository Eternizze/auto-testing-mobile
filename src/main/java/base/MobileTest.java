package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import settings.Settings;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MobileTest {

  private static Settings settings;
  private static AppiumDriverLocalService service;
  protected static AppiumDriver driver;

  @BeforeMethod
  public static void beforeAll() throws IOException {
    //Get Settings
    settings = Settings.getInstance();


    //Start Appium Server
    AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder().usingAnyFreePort()
        .withArgument(GeneralServerFlag.RELAXED_SECURITY)
        .withArgument(GeneralServerFlag.LOG_LEVEL, "warn");
    System.out.println("vbhjnkml,");

    service = AppiumDriverLocalService.buildService(serviceBuilder);
    service.start();

    //Start Appium Client and set implicity wait of 30sec.
    driver = new AppiumDriver(service.getUrl(), getCapabilities());
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

//  @BeforeMethod
//  public void  beforeEach(){
//    if (settings.shouldRestartBetweenTests()){
//      driver.resetApp();
//    }
//  }

  @AfterTest
  public static void afterAll(){
    if (driver != null) {
      driver.quit();
    }

    if (service != null) {
      service.stop();
    }
  }

  private static DesiredCapabilities getCapabilities() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, settings.getPlatform());
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, settings.getPlatformVersion());
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, settings.getDeviceName());
    capabilities.setCapability(MobileCapabilityType.APP, settings.getAppPath());

    // Set command timeout(in debug timeout is huge to allow normal debugging

    //Set Android specific settings
    if (settings.getPlatform() == Platform.ANDROID){
      capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
      capabilities.setCapability(AndroidMobileCapabilityType.ANDROID_DEVICE_READY_TIMEOUT, 120);
      String avd = settings.getAvdName();
      if (avd != null) {
        capabilities.setCapability(AndroidMobileCapabilityType.AVD, avd);
      }

      //Set iOS specific settings
      if (settings.getPlatform() == Platform.IOS){
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);

      }
    }
    return capabilities;
  }
}
