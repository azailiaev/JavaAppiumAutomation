package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI {
    static {
        VIEW_LIST = "id:Saved";
    }
    public iOSNavigationUI(AppiumDriver driver){
        super(driver);
    }
}
