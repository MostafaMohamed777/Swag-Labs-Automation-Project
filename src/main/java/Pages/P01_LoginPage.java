package Pages;

import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_LoginPage {

    //Locators
    private final By userName = By.id("user-name");
    private final By Password = By.id("password");
    private final By loginBtn = By.id("login-button");

    //Variables
    private WebDriver driver;

    //constructor
    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //action
    public P01_LoginPage enterUserName(String userNameText) {
        Utility.sendData(driver, userName, userNameText);
        return this;
    }

    public P01_LoginPage enterPassword(String passwordText) {
        Utility.sendData(driver, Password, passwordText);
        return this;
    }

    public P02_LandingPage clickOnLoginBtn() {
        Utility.clickElement(driver, loginBtn);
        return new P02_LandingPage(driver);
    }

    //Assertion
    public boolean assertLoginTc(String expectedValue) {
        return driver.getCurrentUrl().equals(expectedValue);
    }
}
