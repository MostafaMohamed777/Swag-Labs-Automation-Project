package Pages;

import Utilittes.LogsUtils;
import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05_PaymentPage {

    //Locator
    private final By SubTotalPrice = By.cssSelector("div[data-test=\"subtotal-label\"]");
    private final By TaxLocator = By.cssSelector("div[data-test=\"tax-label\"]");
    private final By TotalPrice = By.className("summary_total_label");
    private final By FinishBtn = By.id("finish");


    //variables
    private final WebDriver driver;

    //Constructor
    public P05_PaymentPage(WebDriver driver) {
        this.driver = driver;
    }

    //Action
    public float getSubTotal() {
        //  LogsUtils.info("Sub Total IS= " + Float.parseFloat(Utility.getText(driver, SubTotalPrice).replace("Item total: $", "")));
        return Float.parseFloat(Utility.getText(driver, SubTotalPrice).replace("Item total: $", ""));
    }

    public float getTax() {
        // LogsUtils.info("Tax IS= " + Float.parseFloat(Utility.getText(driver, TaxLocator).replace("Tax: $", "")));

        return Float.parseFloat(Utility.getText(driver, TaxLocator).replace("Tax: $", ""));
    }

    public float getTotalPrice() {
        LogsUtils.info(" Total Price IS=" + Float.parseFloat(Utility.getText(driver, TotalPrice).replace("Total: $", "")));
        return Float.parseFloat(Utility.getText(driver, TotalPrice).replace("Total: $", ""));
    }

    public String calculateTotalPrice() {
        LogsUtils.info("Calc Total Price=" + (getTax() + getSubTotal()));
        return String.valueOf(getTax() + getSubTotal());
    }

    public P06_FinishingPage clickOnFinishBtn() {
        Utility.clickElement(driver, FinishBtn);
        return new P06_FinishingPage(driver);
    }

    //Assertions
    public boolean comparingPrice() {
        return calculateTotalPrice().equals(String.valueOf(getTotalPrice()));
    }

}
