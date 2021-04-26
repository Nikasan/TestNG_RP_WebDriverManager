package regression;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.ScreenshotListener;

import java.net.MalformedURLException;
import java.net.URL;

@Listeners({ScreenshotListener.class})
public class SetupDriver {

    private static final Logger LOGGER = LogManager.getLogger(SetupDriver.class);
    public static WebDriver driver;

    @BeforeSuite
    public void beforeSuit() {
        LOGGER.info("Test started");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void afterTest() {
        driver.close();
        LOGGER.info("Browser closed");
    }

    public static RemoteWebDriver CreateWebDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("90.0");
        capabilities.setCapability("screenResolution", "1920x1080x24");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),
                    capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
        return driver;
    }
}
