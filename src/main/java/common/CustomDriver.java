package common;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Custom driver class
 *
 * @author fapateanu
 */
public class CustomDriver {
    private WebDriver driver;

    private static CustomDriver instance = null;

    private final Logger LOG = CustomLogger.getInstance(CustomDriver.class).getLogger();

    /**
     * Private constructor which will start the webdriver
     */
    private CustomDriver(String browserName) {
        driver = DriverFactory.openDriver(browserName);
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
     * Get the webdriver
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
