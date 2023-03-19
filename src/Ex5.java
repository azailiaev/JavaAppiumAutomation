import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class Ex5 {

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
    public void saveArticle()
    {
        String send_keys = "Java";
        String text = "Java (programming language)";
        String text2 = "Object-oriented programming language";
        String text3 = "Set of computer software and specifications";
        String text4 = "Java (software platform)";
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
                send_keys,
                "Cannot find Id",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'"+text2+"')]"),
                "Cannot find '"+text2+"'",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc='Save']"),
                "Cannot find Save",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'ADD TO LIST')]"),
                "Cannot find 'ADD TO LIST'",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "My saved list",
                "Cannot find input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'OK')]"),
                "Cannot find 'OK'",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Navigate up'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'"+text3+"')]"),
                "Cannot find '"+text3+"'",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@content-desc='Save']"),
                "Cannot find Save",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'ADD TO LIST')]"),
                "Cannot find 'ADD TO LIST'",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot find saved folder",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'VIEW LIST')]"),
                "Cannot find 'VIEW LIST'",
                5
        );
        waitForElementPresent(
                By.xpath("//*[contains(@text,'" + text + "')]"),
                "Cannot find first article",
                5
        );
        String title_before_removing = waitForElementAndGetAttribute(
                By.xpath("//*[contains(@text,'" + text4 + "')]"),
                "text",
                "Cannot find title of article",
                15
        );
        swipeElementToLeft(
                By.xpath("//*[contains(@text,'" + text + "')]"),
                "Cannot swipe"
        );
        waitForElementPresent(
                By.xpath("//*[contains(@text,'removed from My saved list')]"),
                "Element not found",
                5
        );
        waitForElementPresent(
                By.xpath("//*[contains(@text,'1 of 1 article available')]"),
                "Number of articles not as expected",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'" + text4 + "')]"),
                "Cannot find second article",
                5
        );
        Assert.assertEquals(
                "Article title changed",
                title_before_removing,
                text4
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

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }
    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
