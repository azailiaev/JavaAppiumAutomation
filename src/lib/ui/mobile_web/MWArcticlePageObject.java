package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArcticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "css:#content h1";
        FIRST_TITLE_IN_LIST = "id:Java (programming language)";
        SECOND_TITLE_IN_LIST = "id:JavaScript";
        THIRD_TITLE_IN_LIST = "id:Java (software platform)";
        FOOTER_ELEMENT = "css:footer";
        OPTION_ADD_TO_LIST = "xpath://*[@id='ca-watch']";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions li#ca-watch.mw-ui-icon-mf-watched watched button";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        SAVED_BUTTON = "xpath://XCUIElementTypeButton[@name='Saved']";
        SYNC_CROSS = "id:Close";
    }

    public MWArcticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
