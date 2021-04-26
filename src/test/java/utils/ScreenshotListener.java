package utils;

import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.BaseTestNGListener;
import com.epam.reportportal.testng.ITestNGService;
import com.epam.reportportal.testng.TestNGService;
import com.google.common.base.Suppliers;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import regression.SetupDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.function.Supplier;

public class ScreenshotListener extends BaseTestNGListener {
    private static final Logger LOGGER = LogManager.getLogger(ScreenshotListener.class);
    private static final Supplier<ITestNGService> SERVICE = Suppliers.memoize(TestNGService::new);

    public ScreenshotListener() {
        super((ITestNGService) SERVICE.get());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LOGGER.info("Test Failed with screenshot:");
        try {
            ScreenShotter.getScreenshot(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ScreenShotter extends SetupDriver {
        public static void getScreenshot(ITestResult result) throws IOException {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
            File destFile = new File((String) reportDirectory + "/failure_screenshots/" + result.getTestName() + ".png");
            FileUtils.copyFile(scrFile, destFile);
            ReportPortal.emitLog(new ReportPortalMessage(destFile, "error"), "error", new Date(destFile.lastModified()));
        }
    }
}