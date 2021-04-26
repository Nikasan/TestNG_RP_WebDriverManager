package regression;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ScreenshotListener;

import java.util.Date;

@Listeners({ScreenshotListener.class})
public class Tests extends SetupDriver {
    private static final Logger LOGGER = LogManager.getLogger(Tests.class);

    @Test
    public void firstTest() {
        driver.navigate().to("https://habr.com/ru/company/luxoft/blog/270383/");
        LOGGER.info("driver navigated to habr");
        Assert.assertEquals(driver.getTitle(), "Шпаргалка Java программиста 5. Java Stream API / Блог компании Luxoft / Хабр");
        LOGGER.info("Actual title" + driver.getTitle() + new Date(System.currentTimeMillis()));
    }


}
