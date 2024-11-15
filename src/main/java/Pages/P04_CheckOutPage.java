package Pages;

import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Utilittes.Utility.GeneralWaite2;

public class P04_CheckOutPage {

    //locators
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By cancelBtn = By.id("cancel");
    //variables
    private final WebDriver driver;

    //constructors
    public P04_CheckOutPage(WebDriver driver) {
        this.driver = driver;
    }

    //Actions
    public P04_CheckOutPage fillDAta(String lName, String fName, String ZipCode) {
        Utility.sendData(driver, firstName, fName);
        Utility.sendData(driver, firstName, lName);
        Utility.sendData(driver, firstName, ZipCode);
        return this;
    }

    public P04_CheckOutPage enterFirstName(String firestName) {
        Utility.sendData(driver, firstName, firestName);
        return this;
    }

    public P04_CheckOutPage enterLastName(String lastNamef) {
        Utility.sendData(driver, lastName, lastNamef);
        return this;
    }

    public P04_CheckOutPage enterPostalCode(String ZipCode) {
        Utility.sendData(driver, postalCode, ZipCode);
        return this;
    }

    public P05_PaymentPage clickOnContinue() {
        Utility.clickElement(driver, continueBtn);
        return new P05_PaymentPage(driver);
    }

    //Assertions
    public boolean VerifyCheckOutPageUrl(String expectedUrl) {
        try {
            GeneralWaite2(driver, 5).until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
