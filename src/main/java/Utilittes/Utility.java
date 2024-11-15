package Utilittes;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;


public class Utility {

    private static String ScreenShoots_Path = "test-OutPut/ScreenShoots/";

    //ToDo: Logs File
    public static File getLatestFile(String folderPath) {

        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
        return files[0];
    }

    //ToDo:Click Element
    public static void clickElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    //ToDo:Send Text
    public static void sendData(WebDriver driver, By locator, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }
    //ToDo: General Wait

    public static void generalWait(WebDriver driver, By locator, int timeOut) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(timeOut))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println("Element not found within " + timeOut + " seconds: " + locator.toString());
        }

    }

    public static WebDriverWait GeneralWaite2(WebDriver driver, int timeout) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    //ToDo:GetText
    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();

    }

    //Todo: selecting from drop Down
    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new Select(findWebElement(driver, locator)).deselectByVisibleText(option);
    }

    //Todo: Taking ScreenShoot
    public static void talkingScreenShoot(WebDriver driver, String screenShootName) throws IOException {
        try {
            //caption ScreenShoot using TakesScreenshot
            File screenSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //saving screenShoot to file if needed
            File screenDest = new File(ScreenShoots_Path + screenShootName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(screenSrc, screenDest);
            //attach screenShoot to allure
            Allure.addAttachment(screenShootName, Files.newInputStream(Path.of(screenDest.getPath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ToDo:Taking Full ScreenShoot
    public static void takingFullScreenShoot(WebDriver driver, By locator) {
        try {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .highlight(findWebElement(driver, locator))
                    .save(ScreenShoots_Path);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
    }

    //ToDo: Create generate Random Numbers
    public static int generateRandomNumber(int upperBound) {
        return new Random().nextInt(upperBound) + 1;
    }

    //Set >> unique Numbers >> 1 2 3 3 3= 123
    public static Set<Integer> generateUniqueNumbers(int numberOfProductNeeded, int totalNumberOfProduct) {
        Set<Integer> generateNumbers = new HashSet<>();
        while (generateNumbers.size() < numberOfProductNeeded) {
            int randomNumber = generateRandomNumber(totalNumberOfProduct);
            generateNumbers.add(randomNumber);
        }
        return generateNumbers;
    }

    //Todo: Converting By to webElement

    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    //Todo: Scrolling to Element
    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                findWebElement(driver, locator));

    }

    //ToDo: Get TimeStamp
    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ss-a").format(new Date());
    }

    //ToDo: Verify Url
    public static boolean VerifyUrl(WebDriver driver, String expectedUrl) {
        try {
            GeneralWaite2(driver, 5).until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //Todo:Add Cookies
    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    //ToDo: Set Cookies
    public static void restoreSession(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies)
            driver.manage().addCookie(cookie);
    }

    //Todo: Uploading File Using Robot


}
