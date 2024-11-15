package Tests;

import Listeners.iInvokedMethodListenersClass;
import Listeners.iTestResultListenersClass;
import Pages.P01_LoginPage;
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
public class TC01_LoginTc {

    private final String UserName = DataUtils.getJasonData("ValidLoginData", "UserName1");
    private final String Password = DataUtils.getJasonData("ValidLoginData", "Password");

    public TC01_LoginTc() throws FileNotFoundException {
    }

    @BeforeMethod
    public void setUp() throws IOException {

        //we create here this new if condition to mack sure that we can run anything using MVN command
        //the If condition is = condition ? true : false

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
    public void LoginTC() throws IOException {
        new P01_LoginPage(getDriver())
                .enterUserName(UserName)
                .enterPassword(Password)
                .clickOnLoginBtn();
        Assert.assertTrue(new P01_LoginPage(getDriver()).
                assertLoginTc(getPropertyValue("Environments.properties", "HOME_URL")));
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }
}
