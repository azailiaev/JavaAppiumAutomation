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

public class FirstTest {

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
    public void testSendKeys()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Cannot find SKIP",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Xpath",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Id",
                5
        );
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
                "Cannot find Id",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Id",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search",
                5
        );
        waitForElementAndClick(
                By.className("android.widget.ImageButton"),
                "Cannot find Cl",
                5
        );
        waitForElementNotPresent(
                By.className("android.widget.ImageButton"),
                "Cl still presist",
                5
        );
    }

    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Cannot find SKIP",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find Xpath",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Id",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Cannot find Xpath",
                5
        );
        WebElement title_element = waitForElementPresent(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Cannot article title",
                15
        );
        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "Unexpected title",
                "Java (programming language)",
                article_title
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
    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
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
