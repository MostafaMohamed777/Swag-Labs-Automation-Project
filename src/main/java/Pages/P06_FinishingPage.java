package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilittes.Utility.findWebElement;

public class P06_FinishingPage {

    //Locator
    private final By finishingMsg = By.className("complete-header");

    //var
    private final WebDriver driver;

    //constructor
    public P06_FinishingPage(WebDriver driver) {
        this.driver = driver;
    }

    //Action
    public boolean CheckVisibilityOfFinishingMsg() {
        return findWebElement(driver, finishingMsg).isDisplayed();
    }


}
