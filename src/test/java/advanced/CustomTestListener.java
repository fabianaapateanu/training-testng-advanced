package advanced;

import common.CustomLogger;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Custome test listener example for showing what can we do before & after test run
 * in TestNG
 *
 * @author fapateanu
 */
public class CustomTestListener implements ITestListener {

    private Logger LOG = CustomLogger.getInstance(CustomTestListener.class).getLogger();

    public void onStart(ITestContext context) {
        LOG.info("===ON START=== Invoked after the test class is instantiated and before any configuration method is called.");
    }

    public void onFinish(ITestContext context) {
        LOG.info("===ON FINISH=== Invoked after all the tests have run and all their Configuration methods have been called.");
    }

    public void onTestStart(ITestResult testResult) {
        LOG.info("===ON TEST START === Invoked each time before a test will be invoked");
    }

    public void onTestSuccess(ITestResult testResult) {
        LOG.info("===ON TEST SUCCESS === Invoked each time a test succeeds.");
    }

    public void onTestSkipped(ITestResult testResult) {
        LOG.info("===ON TEST SKIPPED === Invoked each time a test is skipped.");
    }

    public void onTestFailure(ITestResult testResult) {
        LOG.info("===ON TEST FAIL === Invoked each time a test fails.");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult testResult) {
        LOG.info("===ON FINISH=== Invoked each time a method fails but has been annotated with successPercentage and this failure still keeps it within the success percentage requested.");
    }

}
