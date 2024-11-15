package Tests;

import Listeners.iInvokedMethodListenersClass;
import Listeners.iTestResultListenersClass;
import Pages.P01_LoginPage;
import Pages.P02_LandingPage;
import Utilittes.DataUtils;
import Utilittes.LogsUtils;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static DriverFactory.DriverFactory.*;
import static Utilittes.DataUtils.getPropertyValue;
import static Utilittes.Utility.getAllCookies;
import static Utilittes.Utility.restoreSession;

@Listeners({iInvokedMethodListenersClass.class,
        iTestResultListenersClass.class})
public class TC02_LandingTest {
    private final String UserName = DataUtils.getJasonData("ValidLoginData", "UserName1");
    private final String Password = DataUtils.getJasonData("ValidLoginData", "Password");

    private Set<Cookie> cookies;

    public TC02_LandingTest() throws FileNotFoundException {
    }

    @BeforeClass
    public void logIn() throws IOException {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : getPropertyValue("Environments.properties", "BROWSER");
        LogsUtils.info(System.getProperty("browser"));
        setUpBrowser(browser);
        LogsUtils.info("Chrome driver is opened");
        getDriver().get(getPropertyValue("Environments.properties", "LOGIN_URL"));
        LogsUtils.info("Page is redirected to URL");
        getDriver().manage().timeouts().
                implicitlyWait(Duration.ofSeconds(5));
        new P01_LoginPage(getDriver())
                .enterUserName(UserName)
                .enterPassword(Password)
                .clickOnLoginBtn();
        cookies = getAllCookies(getDriver());
        quitDriver();
    }

    @BeforeMethod
    public void setUp() throws IOException {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : getPropertyValue("Environments.properties", "BROWSER");
        LogsUtils.info(System.getProperty("browser"));
        setUpBrowser(browser);
        LogsUtils.info("Chrome driver is opened");
        getDriver().get(getPropertyValue("Environments.properties", "LOGIN_URL"));
        LogsUtils.info("Page is redirected to URL");
        restoreSession(getDriver(), cookies);
        getDriver().get(getPropertyValue("Environments.properties", "HOME_URL"));
        getDriver().navigate().refresh();

    }

    @Test
    public void checkingNumOfSelectedProductsTc() {

        new P02_LandingPage(getDriver()).addAllProductsToCart();
        Assert.assertTrue(new P02_LandingPage(getDriver()).comparingNumOfSelectedProductWithCart());
    }

    @Test
    public void InValidTC() {
        new P02_LandingPage(getDriver())
                .addAllProductsToCart();
        Assert.assertTrue(new P02_LandingPage(getDriver()).comparingNumOfSelectedProductWithCart());
    }

    @Test
    public void AddRandomProductToCartTC() {
        new P02_LandingPage(getDriver())
                .addRandomProducts(3, 6);
        Assert.assertTrue(new P02_LandingPage(getDriver()).comparingNumOfSelectedProductWithCart());

    }

    @Test
    public void clickOnCartIcon() throws IOException {
        new P02_LandingPage(getDriver())
                .ClickOnCartIcon();
        Assert.assertTrue(new P02_LandingPage(getDriver()).
                VerifyCartPageUrl(DataUtils.getPropertyValue("Environments.properties", "CART_URL")));
    }

    @AfterMethod
    public void quit() {
        quitDriver();
    }

    @AfterClass
    public void clearSession() {
        cookies.clear();
    }


}
