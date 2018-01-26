package common;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class CustomDriver {

    private WebDriver driver;
    private String runningOsPlatform = null;
    private static CustomDriver instance = null;

    private final Logger LOG = CustomLogger.getInstance(CustomDriver.class).getLogger();

    /**
     * Private constructor which will start the webdriver
     */
    private CustomDriver(String browserName) {
        runningOsPlatform = computeRunningPlatform();
        LOG.info("Running platform is: " + runningOsPlatform);
        openDriver(browserName);
    }

    /**
     * Get the CustomDriver unique instance
     *
     * @return
     */
    static public CustomDriver getInstance(String browserName) {
        if (instance == null)
            instance = new CustomDriver(browserName);
        return instance;
    }

    /**
     * Compute the running OS name
     *
     * @return
     */
    private String computeRunningPlatform() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "windows";
        } else if (os.contains("mac")) {
            return "mac";
        } else if (os.contains("linux")) {
            return "linux";
        } else {
            return "other";
        }
    }

    /**
     * Open the webdriver
     *
     * @return
     */
    private WebDriver openDriver(String browserName) {
        LOG.info("Starting the driver...");
        if (browserName.equals(ProjectConstants.BROWSER_CHROME)) {
            LOG.info("Starting on Chrome browser");
            driver = getChromedDriver();
        } else if (browserName.equals(ProjectConstants.BROWSER_SAFARI)) {
            LOG.info("Starting on Safari browser");
            driver = new SafariDriver();
        } else if (browserName.equals(ProjectConstants.BROWSER_FIREFOX)) {
            LOG.info("Starting on Firefox browser");
            driver = getFirefoxDriver();
        }
        driver.manage().window().maximize();
        LOG.info("Driver started and it should navigate to: " + ProjectConstants.URL);
        driver.navigate().to(ProjectConstants.URL);
        return driver;
    }

    private WebDriver getChromedDriver() {
        if (runningOsPlatform.equalsIgnoreCase(ProjectConstants.PLATFORM_WINDOWS_OS)) {
            System.setProperty(ProjectConstants.DRIVER_CHROMEDRIVER_PROP_NAME, ProjectConstants.CHROMEDRIVER_WIN_OS_PATH);
            LOG.info("Chromedriver path is: " + ProjectConstants.CHROMEDRIVER_WIN_OS_PATH);
        } else if (runningOsPlatform.equalsIgnoreCase(ProjectConstants.PLATFORM_MAC_OS)) {
            System.setProperty(ProjectConstants.DRIVER_CHROMEDRIVER_PROP_NAME, ProjectConstants.CHROMEDRIVER_MAC_OS_PATH);
            LOG.info("Chromedriver path is: " + ProjectConstants.CHROMEDRIVER_MAC_OS_PATH);
        }
        return new ChromeDriver();
    }

    private WebDriver getFirefoxDriver() {
        LOG.info("Starting on Firefox browser");
        if (runningOsPlatform.equalsIgnoreCase(ProjectConstants.PLATFORM_WINDOWS_OS)) {
            System.setProperty(ProjectConstants.DRIVER_FIREFOXDRIVER_PROP_NAME, ProjectConstants.GECKODRIVER_WIN_OS_PATH);
            LOG.info("Geckodriver path is: " + ProjectConstants.GECKODRIVER_WIN_OS_PATH);
        } else if (runningOsPlatform.equalsIgnoreCase(ProjectConstants.PLATFORM_MAC_OS)) {
            System.setProperty(ProjectConstants.DRIVER_FIREFOXDRIVER_PROP_NAME, ProjectConstants.GECKODRIVER_MAC_OS_PATH);
            LOG.info("Geckodriver path is: " + ProjectConstants.GECKODRIVER_MAC_OS_PATH);
        }
        return new FirefoxDriver();
    }

    /**
     * Return the webdriver
     *
     * @return
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Close the driver and its instance
     */
    public void closeDriver() {
        LOG.info("Closing the driver...");
        if (instance != null) {
            driver.quit();
            instance = null;
        } else {
            LOG.warn("The CustomDriver instance is null, driver is not probably started.");
        }
    }
}
