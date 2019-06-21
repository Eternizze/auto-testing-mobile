package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.BeforeTest;

public class MobileTest {
    protected static AppiumDriver driver;
    private static AppiumDriverLocalService service;
    private static Settings settings;

    @BeforeTest
    public static void beforeAll(){

    }
}
