import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static org.junit.Assert.assertTrue;

public class HomeWork {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "and10");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\zaily\\OneDrive\\Документы\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSearchFieldHasText() {
        WebElement button_skip = driver.findElementByXPath("//*[contains(@text,'SKIP')]");
        button_skip.click();

        assertElementHasText(
                By.className("android.widget.TextView"),
                "Search Wikipedia",
                "Search text is missing"
        );
    }

    private void assertElementHasText(By by, String expected_text, String error_message){
        String actual_text = driver.findElement(by).getText();
        Assert.assertEquals(error_message, expected_text, actual_text);
    }
}
