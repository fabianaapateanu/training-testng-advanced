package advanced;

import common.CustomLogger;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Custom test listener example for showing what can we do before & after test run
 * or specific actions based on test execution results.
 *
 * @author fapateanu
 */
public class CustomTestListener implements ITestListener {

    private Logger LOG = CustomLogger.getInstance(CustomTestListener.class).getLogger();

    public void onStart(ITestContext context) {
        LOG.info("=== ON START === Invoked before the test class is instantiated and before any configuration method is called.");
    }

    public void onFinish(ITestContext context) {
        LOG.info("=== ON FINISH === Invoked after all the tests have run and all their configuration methods have been called.");
    }

    public void onTestStart(ITestResult testResult) {
        LOG.info("=== ON TEST START === Invoked each time before a test will be invoked");
    }

    public void onTestSuccess(ITestResult testResult) {
        LOG.info("=== ON TEST SUCCESS === Invoked each time a test succeeds.");
        setMethodDetails("PASSED", testResult);
        //TODO Example: send Pass status to test in Jira/HOQC
    }

    public void onTestSkipped(ITestResult testResult) {
        LOG.info("=== ON TEST SKIPPED === Invoked each time a test is skipped.");
        setMethodDetails("SKIPPED", testResult);
    }

    public void onTestFailure(ITestResult testResult) {
        LOG.info("=== ON TEST FAIL === Invoked each time a test fails.");
        setMethodDetails("FAILED", testResult);
        //TODO Example: take screenshot of the browser
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {
        LOG.info("=== ON FINISH WITHIN SUCCESS PERCENTAGE === Invoked each time a method fails but has been annotated with successPercentage and this failure still keeps it within the success percentage requested.");
    }

    /**
     * Method which displays in the console the test method name and its status
     *
     * @param resultStatus
     * @param testResult
     */
    public void setMethodDetails(String resultStatus, ITestResult testResult) {
        String methodName = testResult.getMethod().getMethodName();
        LOG.info("<<< METHOD: " + methodName + " HAS " + resultStatus + " >>>");
    }
}
