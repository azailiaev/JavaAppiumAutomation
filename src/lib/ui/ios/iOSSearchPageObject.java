package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject
{
    static {
        SKIP_BUTTON = "xpath://XCUIElementTypeStaticText[@value='Skip']";
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name = 'Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value = 'Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "id:{SUBSTRING}";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name= 'No results found']";
        SEARCH_ICON = "id:Search";
    }

    public iOSSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
