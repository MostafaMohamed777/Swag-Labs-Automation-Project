package Tests;

import Listeners.iInvokedMethodListenersClass;
import Listeners.iTestResultListenersClass;
import Pages.*;
import Utilittes.DataUtils;
import Utilittes.LogsUtils;
import Utilittes.Utility;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilittes.DataUtils.getPropertyValue;

@Listeners({iInvokedMethodListenersClass.class,
        iTestResultListenersClass.class})
public class Tc05_PaymentTC {
    private final String UserName = DataUtils.getJasonData("ValidLoginData", "UserName1");
    private final String Password = DataUtils.getJasonData("ValidLoginData", "Password");
    private final String FirstName = DataUtils.getJasonData("Information", "fName") + "-" + Utility.getTimeStamp();
    private final String LastName = DataUtils.getJasonData("Information", "lName") + "-" + Utility.getTimeStamp();
    private final String ZipCode = new Faker().number().digits(5);

    public Tc05_PaymentTC() throws FileNotFoundException {
    }


    @BeforeMethod
    public void setUp() throws IOException {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : getPropertyValue("Environments.properties", "BROWSER");
        LogsUtils.info(System.getProperty("browser"));
        setUpBrowser(browser);
        LogsUtils.info("Chrome driver is opened");
        getDriver().get(getPropertyValue("Environments.properties", "LOGIN_URL"));
        LogsUtils.info("Page is redirected to URL");
        getDriver().manage().timeouts().
                implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void checkOutStepTwoTC() throws IOException {


        //ToDo :Login Steps
        new P01_LoginPage(getDriver())
                .enterUserName(UserName)
                .enterPassword(Password)
                .clickOnLoginBtn();
        //ToDo:Add Products Steps
        new P02_LandingPage(getDriver()).addAllProductsToCart()
                .ClickOnCartIcon();

        //ToDo:Go To Checkout Page
        new P03_CartPage(getDriver()).ClickOnCheckOutBtt();

        //ToDo:Filling Information Steps
        new P04_CheckOutPage(getDriver())
                .enterFirstName(FirstName)
                .enterLastName(LastName)
                .enterPostalCode(ZipCode)

                .clickOnContinue();
        Assert.assertTrue(new P05_PaymentPage(getDriver()).comparingPrice());
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
