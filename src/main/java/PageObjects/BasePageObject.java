package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DeBeers on 14.11.2016.
 */
public abstract class BasePageObject implements BasePageObjectInterface{

    protected static WebDriver driver;
    protected static org.slf4j.Logger log;

    public WebDriver getDriver() {
        return driver;
    }

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
        this.init(driver);
        log = LoggerFactory.getLogger(Logger.class);
    }

    protected void init(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
