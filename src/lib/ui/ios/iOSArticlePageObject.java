package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FIRST_TITLE_IN_LIST = "id:Java (programming language)";
        SECOND_TITLE_IN_LIST = "id:JavaScript";
        THIRD_TITLE_IN_LIST = "id:Java (software platform)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTION_ADD_TO_LIST = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SAVED_BUTTON = "xpath://XCUIElementTypeButton[@name='Saved']";
        SYNC_CROSS = "id:Close";
    }

    public iOSArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
