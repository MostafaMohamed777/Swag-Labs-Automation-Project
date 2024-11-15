package Tests;

import Listeners.iInvokedMethodListenersClass;
import Listeners.iTestResultListenersClass;
import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Pages.P03_CartPage;
import Utilittes.DataUtils;
import Utilittes.LogsUtils;
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
public class Tc03_CartTest {
    private final String UserName = DataUtils.getJasonData("ValidLoginData", "UserName1");
    private final String Password = DataUtils.getJasonData("ValidLoginData", "Password");

    public Tc03_CartTest() throws FileNotFoundException {
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
    public void comparingPricesTc() throws IOException {
        String totalPrice =
                new P01_LoginPage(getDriver())
                        .enterUserName(UserName)
                        .enterPassword(Password)
                        .clickOnLoginBtn()
                        .addRandomProducts(2, 6)
                        .getTotalPriceOfSelectedProducts();
        new P02_LandingPage(getDriver()).ClickOnCartIcon();
        Assert.assertTrue(new P03_CartPage(getDriver()).comparingPrices(totalPrice));
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
