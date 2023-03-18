import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","and8");
        capabilities.setCapability("platformVersion","8.0.0");
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
    public void testSwipeArticle()
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
                "Appium",
                "Cannot find Id",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Automation for Apps')]"),
                "Cannot find Xpath",
                5
        );
        swipeUpToFindElement(
                By.xpath("//*[@text='View article in browser']"),
                "Cannot find the end of the article",
                20
        );
    }

    @Test
    public void saveArticle()
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
        String send_keys = "Java";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                send_keys,
                "Cannot find Id",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language'",
                5
        );
        String text = "Java (programming language)";
        waitForElementPresent(
                By.xpath("//*[contains(@text,'" + text + "')]"),
                "Cannot find article title",
                15
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
                By.xpath("//*[contains(@text,'VIEW LIST')]"),
                "Cannot find 'VIEW LIST'",
                5
        );
        waitForElementPresent(
                By.xpath("//*[contains(@text,'" + text + "')]"),
                "Cannot find 'Java (programming language)'",
                5
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
    }

    @Test
    public void testAmountOfNotEmptySearch()
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
        String search_line = "Linkin Park discography";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line,
                "Cannot find Id",
                5
        );
        String search_result_container = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(
                By.xpath(search_result_container),
                "Cannot find anything by request " + search_line,
                15
        );
        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_container)
        );
        Assert.assertTrue(
                "We found to few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
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
        String search_line2 = "sdjkhhvfkjdfbkdfngbjkfd";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line2,
                "Cannot find Id",
                5
        );
        String search_result_container = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        String empty_result_label = "//*[@text='No results']";
        waitForElementPresent(
                By.xpath(empty_result_label),
                "Cannot find 'No results'" + search_line2,
                15
        );
        assertElementNotPresent(
                By.xpath(search_result_container),
                "We found some results" + search_line2
        );
    }

    @Test
    public void testChangeScreenOrientation()
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
        String search_line3 = "Java";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_line3,
                "Cannot find Id",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language'" + search_line3,
                15
        );
        String title_before_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "text",
                "Cannot find title of article",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "Article title changed",
                title_before_rotation,
                title_after_rotation
        );
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = waitForElementAndGetAttribute(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "text",
                "Cannot find title of article",
                15
        );
        Assert.assertEquals(
                "Article title changed",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckArticleInBackground() {
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
        String send_keys2 = "Java";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                send_keys2,
                "Cannot find Id",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Object-oriented programming language')]"),
                "Cannot find 'Object-oriented programming language'",
                5
        );

        driver.runAppInBackground(6);

        waitForElementPresent(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Cannot find article after returning from background",
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

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0){

            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
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

    private void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0)
        {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}
