package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthtorizationPageObject extends MainPageObject{
    private static final String
            LOGIN_BUTTON = "xpath://body/div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
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
        this.waitForElementAndSendKeys(PASSWORD_INPUT, login, "Cannot find and put a password to password input.", 5);
    }

    public void submitForm(){
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot click SUBMIT_BUTTON", 5);
    }
}
