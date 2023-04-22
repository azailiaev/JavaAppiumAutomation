package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
        VIEW_LIST,
        OPEN_NAVIGATION;
    public NavigationUI (RemoteWebDriver driver)
    {
        super(driver);
    }

    public void clickViewList()
    {
        if (Platform.getInstance().isMW()){
            this.tryClickElementWithFewAttempts(
                    VIEW_LIST,
                    "Cannot find 'VIEW LIST'",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    VIEW_LIST,
                    "Cannot find 'VIEW LIST'",
                    5
            );
        }
    }

    public void openNavigation(){
        if (Platform.getInstance().isMW()){
            this.waitForElementAndClick(OPEN_NAVIGATION, "Cannot click OPEN_NAVIGATION", 5);
        } else {
            System.out.println("Method openNavigation() do nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }
}
