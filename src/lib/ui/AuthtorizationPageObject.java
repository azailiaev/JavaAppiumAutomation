package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthtorizationPageObject extends MainPageObject{
    private static final String
            LOGIN_BUTTON = "css:body > div.drawer-container.view-border-box > div.drawer.drawer-container__drawer.position-fixed.visible > a",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:#wpPassword1",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthtorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthButton(){
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find LOGIN_BUTTON", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot click LOGIN_BUTTON", 5);
    }

    public void enterLoginData(String login, String password){
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot find and put a login to login input.", 5);
        this.waitForElementAndClick(PASSWORD_INPUT, "Cannot click to PASSWORD_INPUT", 10);
        this.waitForElementAndClear(PASSWORD_INPUT, "Cannot clear PASSWORD_INPUT", 10);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot find and put a password to password input.", 5);
    }

    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot click SUBMIT_BUTTON", 5);
    }
}
