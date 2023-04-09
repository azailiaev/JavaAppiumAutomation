package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject{

    protected static String
        VIEW_LIST;
    public NavigationUI (AppiumDriver driver)
    {
        super(driver);
    }

    public void clickViewList()
    {
        this.waitForElementAndClick(
                VIEW_LIST,
                "Cannot find 'VIEW LIST'",
                5
        );
    }
}
