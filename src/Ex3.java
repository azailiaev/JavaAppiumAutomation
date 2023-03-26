import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Ex3 {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","and10");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\zaily\\OneDrive\\Документы\\GitHub\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Cannot find SKIP",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search field",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot send keys",
                5
        );
        WebElement article1 = waitForElementPresent(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Cannot find article title",
                15
        );
        String article_title1 = article1.getAttribute("text");
        Assert.assertEquals(
                "Unexpected title",
                "Java (programming language)",
                article_title1
        );
        WebElement article2 = waitForElementPresent(
                By.xpath("//*[contains(@text,'JavaScript')]"),
                "Cannot find article title",
                15
        );
        String article_title2 = article2.getAttribute("text");
        Assert.assertEquals(
                "Unexpected title",
                "JavaScript",
                article_title2
        );
        WebElement article3 = waitForElementPresent(
                By.xpath("//*[contains(@text,'Java (software platform)')]"),
                "Cannot find article title",
                15
        );
        String article_title3 = article3.getAttribute("text");
        Assert.assertEquals(
                "Unexpected title",
                "Java (software platform)",
                article_title3
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot clear field",
                15
        );
        waitForElementNotPresent(
                By.xpath("//*[contains(@text,'Java (software platform)')]"),
                "Element is still present",
                15
        );
        waitForElementNotPresent(
                By.xpath("//*[contains(@text,'JavaScript')]"),
                "Element is still present",
                15
        );
        waitForElementNotPresent(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Element is still present",
                15
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
}