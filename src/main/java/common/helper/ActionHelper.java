package common.helper;

import common.CustomLogger;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionHelper {
    private WebDriver driver;
    private static final Logger LOG = CustomLogger.getInstance(ActionHelper.class).getLogger();

    private static ActionHelper instance = null;

    private ActionHelper(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Get the CustomDriver unique instance
     *
     * @return
     */
    static public ActionHelper getInstance(WebDriver driver) {
        if (instance == null)
            instance = new ActionHelper(driver);
        return instance;
    }

    // Click on an element
    public void clickOn(WebElement elem) {
        LOG.info("Clicking on the element " + elem);
        elem.click();
    }

    //Send keys to an element
    public void sendKeys(WebElement elem, String text) {
        elem.sendKeys(text);
        LOG.info("The text " + text + " was sent to the element " + elem);
    }
}
