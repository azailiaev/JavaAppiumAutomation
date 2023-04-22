package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
            TITLE = "xpath:/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.view.View[1]";
            FIRST_TITLE_IN_LIST = "xpath://*[contains(@text,'Java (programming language)')]";
            SECOND_TITLE_IN_LIST = "xpath://*[contains(@text,'JavaScript')]";
            THIRD_TITLE_IN_LIST = "xpath://*[contains(@text,'Java (software platform)')]";
            FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
            SAVE_BUTTON = "xpath://android.widget.TextView[@content-desc='Save']";
            OPTION_ADD_TO_LIST = "xpath://*[contains(@text,'ADD TO LIST')]";
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
            MY_LIST_OK_BUTTON = "xpath://*[contains(@text,'OK')]";
            MY_SAVED_LIST = "id:org.wikipedia:id/item_title";
            CLOSE_ARTICLE_BUTTON = "id:Navigate up";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver){
        super(driver);
    }
}
