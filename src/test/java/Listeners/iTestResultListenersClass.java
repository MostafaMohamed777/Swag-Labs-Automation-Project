package Listeners;

import Utilittes.LogsUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class iTestResultListenersClass implements ITestListener {
    public void onTestStart(ITestResult result) {
        LogsUtils.info("Test Case"+result.getName()+"Started");
    }

    public void onTestSuccess(ITestResult result) {
        LogsUtils.info("Test Case"+result.getName()+"Passed");
    }
    public void onTestSkipped(ITestResult result) {
        LogsUtils.info("Test Case"+result.getName()+"Skipped");
    }

}
