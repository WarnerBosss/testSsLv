package Tests;

import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import ru.stqa.selenium.factory.WebDriverFactory;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by DeBeers on 14.11.2016.
 */
public class BaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static String baseUrl;
    public static StringBuffer verificationErrors = new StringBuffer();
    public static org.slf4j.Logger log;

    @Parameters({"URL"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String URL)
            throws ClassNotFoundException, IOException, SQLException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {

        String TestClassName = this.getClass().getName();
        System.out.println(TestClassName);

        baseUrl          = URL;
        log              = LoggerFactory.getLogger(Logger.class);

        String path = System.getProperty("user.dir") + "\\src\\main\\java\\Downloaded_Files";
        File downloadDir = new File(path);
        FirefoxProfile fProfile = new FirefoxProfile();
        fProfile.setAcceptUntrustedCertificates(true);
        fProfile.setPreference("browser.download.dir", downloadDir.getAbsolutePath());
        fProfile.setPreference("browser.download.folderList", 2);
        fProfile.setPreference("browser.download.manager.showWhenStarting", false);
        fProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
        fProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/plain");
        fProfile.setPreference("privacy.popups.disable_from_plugins", 3);
        DesiredCapabilities dc = DesiredCapabilities.firefox();
        //dc.setCapability(CapabilityType.PROXY, fProfile);
        dc.setJavascriptEnabled(true);
        dc.setCapability(FirefoxDriver.PROFILE, fProfile);
        dc.setCapability("marionette", true);

        System.setProperty("webdriver.gecko.driver",
                "C:\\Users\\DeBeers\\Desktop\\SlabiyIlyaQA\\src\\main\\java\\geckoDriver\\geckodriver.exe");


        driver = WebDriverFactory.getDriver(dc);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);

        try {
            driver.get(baseUrl);
            Assert.assertTrue(driver.getCurrentUrl().contains(baseUrl), "We are not on main page!"
                    + driver.getCurrentUrl() + "  " + "But expected:::: " + baseUrl);
        }catch (Exception e){
            System.out.println("<<<<< We are not on the MAIN PAGE >>>>>");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws Exception {

        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                Calendar calendar = Calendar.getInstance();
                File source = ts.getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(source, new File("./Screenshots/"
                        + "_"
                        + result.getName()
                        + "_"
                        + calendar.get(Calendar.MONTH)
                        + "_"
                        + calendar.get(Calendar.DAY_OF_MONTH)
                        + "_"
                        + calendar.get(Calendar.HOUR_OF_DAY)
                        + "_"
                        + calendar.get(Calendar.MINUTE)
                        + "_"
                        + calendar.get(Calendar.SECOND)
                        + ".png"));

                System.out.println("Screenshot taken");
            } catch (Exception e) {

                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
        if (driver.getCurrentUrl() != baseUrl) {
            driver.get("https://www.ss.lv/");
        }

        driver.manage().deleteAllCookies();
        driver.quit();

        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }
}
