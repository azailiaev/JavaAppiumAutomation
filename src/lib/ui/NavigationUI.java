package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject{

    private static final String
        VIEW_LIST = "xpath://*[contains(@text,'VIEW LIST')]";
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
