package Pages;

import Utilittes.LogsUtils;
import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class P03_CartPage {

    static float totalPrice;

    //Locator
    private final By SelectedProductPrices = By.xpath("//button[.=\"Remove\"]//preceding-sibling::div[@class=\"inventory_item_price\"]");
    private final By checkOutBtt = By.id("checkout");
    //variables
    private final WebDriver driver;

    //constructors
    public P03_CartPage(WebDriver driver) {
        this.driver = driver;
    }

    //Actions
    public String getTotalPrice() {
        try {
            List<WebElement> priceOfSelectedProduct = driver.findElements(SelectedProductPrices);
            for (int i = 1; i <= priceOfSelectedProduct.size(); i++) {
                By elements = By.xpath("(//button[.=\"Remove\"]//preceding-sibling::div[@class=\"inventory_item_price\"])[" + i + "]");
                String fullText = Utility.getText(driver, elements);
                totalPrice += Float.parseFloat(fullText.replace("$", ""));
            }
            LogsUtils.info("Total Price" + totalPrice);
            return String.valueOf(totalPrice);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public P04_CheckOutPage ClickOnCheckOutBtt() {
        Utility.clickElement(driver, checkOutBtt);
        return new P04_CheckOutPage(driver);
    }


    //Assertions
    public boolean comparingPrices(String prices) {
        return getTotalPrice().equals(prices);
    }

}
