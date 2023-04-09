package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_BY_NAME_TPL = "id:Java (programming language)";
        ARTICLE_BY_TITLE_TPL = "id:{TITLE}";
    }

    public iOSMyListsPageObject(AppiumDriver driver){
        super(driver);
    }
}
