package common;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Utility class for creating a new instance of webdriver object based on browser
 * and running OS.
 * This class is intentionally left with "package" visibility, it is not made public
 */
class DriverFactory {
    private static final Logger LOG = CustomLogger.getInstance(DriverFactory.class).getLogger();
    private static String runningOsPlatform = computeRunningPlatform();

    /**
     * Compute the running OS name
     *
     * @return
     */
    private static String computeRunningPlatform() {
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
     * Create the webdriver and open the browser
     *
     * @return
     */
    public static WebDriver openDriver(String browserName) {
        WebDriver driver = null;
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

    private static WebDriver getChromedDriver() {
        if (runningOsPlatform.equalsIgnoreCase(ProjectConstants.PLATFORM_WINDOWS_OS)) {
            System.setProperty(ProjectConstants.DRIVER_CHROMEDRIVER_PROP_NAME, ProjectConstants.CHROMEDRIVER_WIN_OS_PATH);
            LOG.info("Chromedriver path is: " + ProjectConstants.CHROMEDRIVER_WIN_OS_PATH);
        } else if (runningOsPlatform.equalsIgnoreCase(ProjectConstants.PLATFORM_MAC_OS)) {
            System.setProperty(ProjectConstants.DRIVER_CHROMEDRIVER_PROP_NAME, ProjectConstants.CHROMEDRIVER_MAC_OS_PATH);
            LOG.info("Chromedriver path is: " + ProjectConstants.CHROMEDRIVER_MAC_OS_PATH);
        }
        DesiredCapabilities.chrome().setCapability("maxInstances", 10);
        return new ChromeDriver();
    }

    private static WebDriver getFirefoxDriver() {
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
}
