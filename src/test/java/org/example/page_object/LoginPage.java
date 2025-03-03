package org.example.page_object;

import lombok.extern.log4j.Log4j2;
import org.example.page_object.base.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@SuppressWarnings("unused")
@Log4j2
public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement usernameInput;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    public boolean inputUserName(String name) {
        try {
            log.info("im entering user name");
            wait.until(ExpectedConditions.visibilityOf(usernameInput));
            usernameInput.sendKeys(name);
            log.info("Input username: " + usernameInput +" successful");
            return true;
        } catch (Exception e) {
            log.error("error encountered when entering user name");
            return false;
        }
    }

    public boolean inputPassword(String password) {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordInput));
            passwordInput.sendKeys(password);
            log.info("Input user password: " +password +" successful");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickEnter() {
        try {
            wait.until(ExpectedConditions.visibilityOf(passwordInput));
            passwordInput.sendKeys(Keys.RETURN);
            log.info("Click Enter key to submit login form successful");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
