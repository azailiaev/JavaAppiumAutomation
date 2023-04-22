package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "id:Java (programming language)";
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://*[@id='mw-content-text']/ul/li[1]/a[2]";
    }

    public MWMyListsPageObject(RemoteWebDriver driver){
        super(driver);
    }
}
