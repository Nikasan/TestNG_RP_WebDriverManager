package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import regression.SetupDriver;

import java.io.File;
import java.io.IOException;

public  class ScreenShotter extends SetupDriver {
    public void getScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/target/surefire-reports";
        File destFile = new File((String) reportDirectory + "/failure_screenshots/" + "_"  + ".png");
        FileUtils.copyFile(scrFile, destFile);
    }
}
