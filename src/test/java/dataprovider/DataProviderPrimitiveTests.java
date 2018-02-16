package dataprovider;

import common.CustomDriver;
import common.CustomLogger;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import training.LoginUserHelper;
import training.basic.pageObject.HomePage;
import training.basic.pageObject.LoginPage;

/**
 * DataProvider can be used with primitive types: String, Boolean, Integer, etc.
 *
 * @author fapateanu
 */
public class DataProviderPrimitiveTests {
    private static String username;
    private static String password;
    private HomePage homePage;

    private static Logger LOG;

    private static ThreadLocal<CustomDriver> myDriver = new ThreadLocal<CustomDriver>();

    @DataProvider(name = "valid_search_data", parallel = true)
    public static Object[] validSearchData() {
        Object[] data = new Object[]{"appium", "webdriver"};
        return data;
    }

    @DataProvider(name = "invalid_search_data", parallel = true)
    public static Object[] invalidSearchData() {
        Object[] data = new Object[]{"poooooooop", "loooooooop"};
        return data;
    }

    @BeforeClass(groups = {"positive_tests", "negative_tests"})
    public static void runBeforeClassInit() {
        LOG = CustomLogger.getInstance(DataProviderPrimitiveTests.class).getLogger();
        LOG.info("Running setup before class test methods initialization");
    }

    @AfterClass(groups = {"positive_tests", "negative_tests"})
    public static void runAfterClassFinished() {
        LOG.info("Running teardown after class test methods run finished");
    }

    @BeforeMethod(groups = {"positive_tests", "negative_tests"})
    @Parameters({"browserName"})
    public void runBeforeEachTestMethod(String browserName) {
        LOG.info("Running setup before each test method");
        username = LoginUserHelper.readValidUsername();
        password = LoginUserHelper.readValidPassword();
        myDriver.set(CustomDriver.getInstance(browserName));
        LoginPage loginPage = new LoginPage(myDriver.get().getDriver());
        homePage = loginPage.performLogin(username, password);
    }

    @AfterMethod(groups = {"positive_tests", "negative_tests"})
    public void runAfterEachTestMethod() {
        LOG.info("Running teardown before each test method");
        myDriver.get().closeDriver();
    }

    @Test(groups = "positive_tests", dataProvider = "valid_search_data", threadPoolSize = 10)
    public void searchWithMultipleResults(String searchQuery) {
        Assert.assertTrue(homePage.isSearchAreaDisplayed(), "The search area is not displayed");
        homePage.performSearch(searchQuery);

        Assert.assertTrue(homePage.isRepositorySearchResultListDisplayed(), "The search action did not return multiple results");
    }

    @Test(groups = "negative_tests", dataProvider = "invalid_search_data", threadPoolSize = 10)
    public void searchWithNoResults(String searchQuery) {
        Assert.assertTrue(homePage.isSearchAreaDisplayed(), "The search area is not displayed");
        homePage.performSearch(searchQuery);

        Assert.assertTrue(homePage.isEmptyResultDisplayed(), "The empty results container is not displayed");
    }
}
