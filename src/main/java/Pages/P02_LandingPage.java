package Pages;

import Utilittes.LogsUtils;
import Utilittes.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

import static Utilittes.Utility.GeneralWaite2;

public class P02_LandingPage {

    static float totalPrice;
    private static List<WebElement> allProducts;
    private static List<WebElement> selectedProducts;

    //Locator
    private final By AddProductsToCart = By.xpath("(//button[@class])");
    private final By shoppingCartNum = By.className("shopping_cart_link");
    private final By numberOfSelectedItems = By.xpath("//button[text()=\"Remove\"]");
    private final By clickOnCartIcon = By.className("shopping_cart_link");
    private final By SelectedProductPrices = By.xpath("//button[.=\"Remove\"]//preceding-sibling::div[@class=\"inventory_item_price\"]");
    //Variables
    private WebDriver driver;

    //constructor
    public P02_LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    //Action
    public P02_LandingPage addAllProductsToCart() {
        allProducts = driver.findElements(AddProductsToCart);
        LogsUtils.info("Number Of All Products Is" + allProducts.size());
        for (int i = 1; i <= allProducts.size(); i++) {
            By AddProductsToCart = By.xpath("(//button[@class])[" + i + "]");
            Utility.clickElement(driver, AddProductsToCart);
        }
        return this;
    }

    public P02_LandingPage addRandomProducts(int numberOfProductNeeded, int totalNumberOfProduct) {

        Set<Integer> randomNumber = Utility.generateUniqueNumbers(numberOfProductNeeded, totalNumberOfProduct);
        for (int random : randomNumber) {
            LogsUtils.info("Random Number is " + random);
            By AddProductsToCart = By.xpath("(//button[@class])[" + random + "]");
            Utility.clickElement(driver, AddProductsToCart);
        }
        return this;
    }

    public String getNumOfProductFromCart() {
        try {
            LogsUtils.info("Number Of Products On Cart Is" + Utility.getText(driver, shoppingCartNum));
            return Utility.getText(driver, shoppingCartNum);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "0";
        }
    }

    public String getNumberOfSelectedProducts() {
        try {
            selectedProducts = driver.findElements(numberOfSelectedItems);
            LogsUtils.info("Number of Selected Products Is" + selectedProducts.size());
            return String.valueOf(selectedProducts.size());
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
        return "0";
    }

    public P03_CartPage ClickOnCartIcon() {
        Utility.clickElement(driver, clickOnCartIcon);
        return new P03_CartPage(driver);
    }

    public String getTotalPriceOfSelectedProducts() {
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

    //assertion
    public boolean comparingNumOfSelectedProductWithCart() {
        return getNumOfProductFromCart().equals(getNumberOfSelectedProducts());
    }

    public By getShoppingCartNum() {
        return shoppingCartNum;
    }

    public boolean VerifyCartPageUrl(String expectedUrl) {
        try {
            GeneralWaite2(driver, 5).until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
