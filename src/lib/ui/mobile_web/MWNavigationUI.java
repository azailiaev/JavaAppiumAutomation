package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        VIEW_LIST = "css:#p-personal > li:nth-child(2) > a";
        OPEN_NAVIGATION = "css:#mw-mf-main-menu-button";
    }
    public MWNavigationUI(RemoteWebDriver driver){
        super(driver);
    }
}