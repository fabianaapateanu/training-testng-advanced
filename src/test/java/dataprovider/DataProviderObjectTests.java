package dataprovider;

import common.CustomDriver;
import common.CustomLogger;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import training.basic.dataprovider.User;
import training.basic.pageObject.LoginPage;

/**
 * DataProvider can be used with your own defined objects.
 * {main/java/training/basic/dataprovider/User.java} is used in this example for login test data.
 *
 * @author fapateanu
 */
//@Listeners({CustomTestListener.class})
public class DataProviderObjectTests {
    private static Logger LOG;
    private static ThreadLocal<CustomDriver> myDriver = new ThreadLocal<CustomDriver>();

    @DataProvider(name = "invalid_user_data")
    public Object[] loginInvalidProvider() {
        return new Object[]{new User("yeseniaworld@gmail.com", "test12345"),
                new User("invalidUser@yahoo", "invalidPass")};
    }

    @BeforeClass()
    public static void runBeforeClassInit() {
        LOG = CustomLogger.getInstance(DataProviderObjectTests.class).getLogger();
        LOG.info("Running setup before class test methods initialization");
    }

    @AfterClass()
    public static void runAfterClassFinished() {
        LOG.info("Running teardown after class test methods run finished");
    }

    @BeforeMethod()
    @Parameters({"browserName"})
    public void runBeforeEachTestMethod(String browserName) {
        LOG.info("Running setup before each test method");
        myDriver.set(CustomDriver.getInstance(browserName));
    }

    @AfterMethod()
    public void runAfterEachTestMethod() {
        LOG.info("Running teardown before each test method");
        myDriver.get().closeDriver();
    }

    @Test(dataProvider = "invalid_user_data")
    public void loginWithInvalidUser(User testUser) {
        LoginPage loginPage = new LoginPage(myDriver.get().getDriver());
        loginPage.fillUsername(testUser.getUsername());
        loginPage.fillPassword(testUser.getPassword());
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Some failure log");
    }

    @Test()
    public void loginWithValidUser() {
        Assert.assertTrue(false, "Stub test method for failed status");
    }
}
