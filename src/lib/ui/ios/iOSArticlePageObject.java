package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTION_ADD_TO_LIST = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SAVED_BUTTON = "xpath://XCUIElementTypeButton[@name='Saved']";
        SYNC_CROSS = "id:Close";
    }

    public iOSArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
