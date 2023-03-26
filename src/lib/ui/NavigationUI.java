package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject{

    private static final String
        VIEW_LIST = "//*[contains(@text,'VIEW LIST')]";
    public NavigationUI (AppiumDriver driver)
    {
        super(driver);
    }

    public void clickViewList()
    {
        this.waitForElementAndClick(
                By.xpath(VIEW_LIST),
                "Cannot find 'VIEW LIST'",
                5
        );
    }
}
