package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.factories.ArticlePageObjectFactory;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            FIRST_TITLE_IN_LIST,
            SECOND_TITLE_IN_LIST,
            THIRD_TITLE_IN_LIST,
            FOOTER_ELEMENT,
            SAVE_BUTTON,
            OPTION_ADD_TO_LIST,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            MY_SAVED_LIST,
            CREATE_NEW_LIST,
            CLOSE_ARTICLE_BUTTON,
            CANCEL_BUTTON,
            SAVED_BUTTON,
            SYNC_CROSS;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public void titleElementNotFound() {
        this.waitForElementNotPresent(TITLE, "Can find article title on page", 0);
    }

    public WebElement doNotWaitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 0);
    }

    public WebElement waitForFirstTitleElementInList() {
        return this.waitForElementPresent(FIRST_TITLE_IN_LIST, "Cannot find article title in list " + FIRST_TITLE_IN_LIST, 15);
    }

    public WebElement waitForSecondTitleElementInList() {
        return this.waitForElementPresent(SECOND_TITLE_IN_LIST, "Cannot find article title in list " + SECOND_TITLE_IN_LIST, 15);
    }

    public WebElement waitForThirdTitleElementInList() {
        return this.waitForElementPresent(THIRD_TITLE_IN_LIST, "Cannot find article title in list " + THIRD_TITLE_IN_LIST, 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()){
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }

    }

    public String getArticleTitleWithoutWaiting() {
        WebElement title_element = doNotWaitForTitleElement();
        return title_element.getAttribute("text");
    }

    public String getFirstArticleTitleInList() {
        WebElement title_first_element_in_list = waitForFirstTitleElementInList();
        if (Platform.getInstance().isAndroid()) {
            return title_first_element_in_list.getAttribute("text");
        } else {
            return title_first_element_in_list.getAttribute("name");
        }

    }

    public String getSecondArticleTitleInList() {
        WebElement title_second_element_in_list = waitForSecondTitleElementInList();
        if (Platform.getInstance().isAndroid()) {
            return title_second_element_in_list.getAttribute("text");
        } else {
            return title_second_element_in_list.getAttribute("name");
        }
    }

    public String getThirdArticleTitleInList() {
        WebElement title_third_element_in_list = waitForThirdTitleElementInList();
        if (Platform.getInstance().isAndroid()) {
            return title_third_element_in_list.getAttribute("text");
        } else {
            return title_third_element_in_list.getAttribute("name");
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        } else if (Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40);
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find Save",
                5
        );
        this.waitForElementAndClick(
                OPTION_ADD_TO_LIST,
                "Cannot find 'ADD TO LIST'",
                5
        );
        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot find input",
                5
        );
        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot find 'OK'",
                5
        );
    }

    public void addArticleToSavedList(String name_of_folder) {
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Cannot find Save",
                5
        );
        this.waitForElementAndClick(
                OPTION_ADD_TO_LIST,
                "Cannot find 'ADD TO LIST'",
                5
        );
        this.waitForElementAndClick(
                MY_SAVED_LIST,
                "Cannot find previously saved folder",
                5
        );
    }

    public void closeArticle() {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article",
                5
        );
    }

    public void addArticlesToMySaved() {
        if (Platform.getInstance().isMW()){
            this.removeArticleFromSavedIfItAdded();
            this.waitForElementAndClick(OPTION_ADD_TO_LIST, "Cannot find option to add article to reading list", 10);
        } else {
            this.waitForElementAndClick(OPTION_ADD_TO_LIST, "Cannot find option to add article to reading list", 10);
            this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON, "Cannot close article", 10);
            this.waitForElementAndClick(CANCEL_BUTTON, "Cannot find Cancel button", 10);
            this.waitForElementAndClick(SAVED_BUTTON, "Cannot find Saved button", 10);
        }
    }

    public void closeSyncForm() {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
            this.waitForElementAndClick(SYNC_CROSS, "Cannot find cross on SYNC form", 10);
        } else {
            System.out.println("Method closeSyncForm() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void removeArticleFromSavedIfItAdded(){
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove article from saved",
                    1
            );
            this.waitForElementPresent(
                    OPTION_ADD_TO_LIST,
                    "Cannot find button to add article to saved after removing"
            );
        }
    }
}
