package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
            TITLE = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[1]",
            FIRST_TITLE_IN_LIST = "//*[contains(@text,'Java (programming language)')]",
            SECOND_TITLE_IN_LIST = "//*[contains(@text,'JavaScript')]",
            THIRD_TITLE_IN_LIST = "//*[contains(@text,'Java (software platform)')]",
            FOOTER_ELEMENT = "//*[@text='View article in browser']",
            SAVE_BUTTON = "//android.widget.TextView[@content-desc='Save']",
            OPTION_ADD_TO_LIST = "//*[contains(@text,'ADD TO LIST')]",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[contains(@text,'OK')]",
            MY_SAVED_LIST = "org.wikipedia:id/item_title",
            CLOSE_ARTICLE_BUTTON = "Navigate up";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page", 15);
    }

    public WebElement doNotWaitForTitleElement()
    {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page", 0);
    }

    public WebElement waitForFirstTitleElementInList()
    {
        return this.waitForElementPresent(By.xpath(FIRST_TITLE_IN_LIST), "Cannot find article title in list " + FIRST_TITLE_IN_LIST, 15);
    }

    public WebElement waitForSecondTitleElementInList()
    {
        return this.waitForElementPresent(By.xpath(SECOND_TITLE_IN_LIST), "Cannot find article title in list " + SECOND_TITLE_IN_LIST, 15);
    }

    public WebElement waitForThirdTitleElementInList()
    {
        return this.waitForElementPresent(By.xpath(THIRD_TITLE_IN_LIST), "Cannot find article title in list " + THIRD_TITLE_IN_LIST, 15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public String getArticleTitleWithoutWaiting()
    {
        WebElement title_element = doNotWaitForTitleElement();
        return title_element.getAttribute("text");
    }

    public String getFirstArticleTitleInList()
    {
        WebElement title_first_element_in_list = waitForFirstTitleElementInList();
        return title_first_element_in_list.getAttribute("text");
    }

    public String getSecondArticleTitleInList()
    {
        WebElement title_second_element_in_list = waitForSecondTitleElementInList();
        return title_second_element_in_list.getAttribute("text");
    }

    public String getThirdArticleTitleInList()
    {
        WebElement title_third_element_in_list = waitForThirdTitleElementInList();
        return title_third_element_in_list.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(SAVE_BUTTON),
                "Cannot find Save",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_LIST),
                "Cannot find 'ADD TO LIST'",
                5
        );
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot find input",
                5
        );
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot find 'OK'",
                5
        );
    }

    public void addArticleToSavedList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(SAVE_BUTTON),
                "Cannot find Save",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTION_ADD_TO_LIST),
                "Cannot find 'ADD TO LIST'",
                5
        );
        this.waitForElementAndClick(
                By.id(MY_SAVED_LIST),
                "Cannot find previously saved folder",
                5
        );
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article",
                5
        );
    }
}
