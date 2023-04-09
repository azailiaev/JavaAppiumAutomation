package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        VIEW_LIST = "xpath://*[contains(@text,'VIEW LIST')]";
    }

    public AndroidNavigationUI(AppiumDriver driver){
        super(driver);
    }
}
