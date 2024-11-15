package Listeners;

import Utilittes.LogsUtils;
import Utilittes.Utility;
import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static DriverFactory.DriverFactory.getDriver;

public class iInvokedMethodListenersClass implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        File LogFile = Utility.getLatestFile(LogsUtils.Logs_Path);
        try {
            assert LogFile != null;
            Allure.addAttachment("logs.log", Files.readString(Path.of(LogFile.getPath())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Utility.takingFullScreenShoot(getDriver(),
        //     new P02_LandingPage(getDriver()).getShoppingCartNum());
        if (testResult.getStatus() == ITestResult.FAILURE) {
            try {
                LogsUtils.info("Test Case" + testResult.getName() + "failed");
                Utility.talkingScreenShoot(getDriver(), testResult.getName());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
