package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import testData.dataSet;
import utils.DriverFactory;

public class Hooks {

    @Before
    public void driverStart() {
        DriverFactory.startingDriver("chrome");
    }

    @After
    public void driverQuit(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        if (driver != null) {
            try {
                String scenarioName = scenario.getName().replaceAll(" ", "_");
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
              
                String screenshotPath = takeScreen(driver, scenarioName, timeStamp, scenario.isFailed());
                if (scenario.isFailed()) {
                    System.out.println("📸 Screenshot saved (FAIL): " + screenshotPath);
                } else {
                    System.out.println("📸 Screenshot saved (SUCCESS): " + screenshotPath);
                }
            } catch (Exception e) {
                System.out.println("❌ Error during driver quit or screenshot: " + e.getMessage());
            } finally {
            	 try {
            	        Thread.sleep(3000); 
            	    } catch (InterruptedException e) {
            	        e.printStackTrace();
            	    }
                driver.quit();
                System.out.println("🛑 Driver quit for scenario: " + scenario.getName());
            }
        }
    }

    public String takeScreen(WebDriver driver, String scenarioName, String timeStamp, boolean isFailed)
            throws IOException {
        String screenshotPath;
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        if (isFailed) {
            String failPath = dataSet.screenFilesPath + "fails/";
            File newFile = new File(failPath);
            if (!newFile.exists()) newFile.mkdirs();

            screenshotPath = failPath + "fail_" + timeStamp + "_" + scenarioName + ".png";
        } else {
            String successPath = dataSet.screenFilesPath + "success/";
            File successNewPath = new File(successPath);
            if (!successNewPath.exists()) successNewPath.mkdirs();

            screenshotPath = successPath + "pass_" + timeStamp + "_" + scenarioName + ".png";
        }
        FileUtils.copyFile(scrFile, new File(screenshotPath));
        return screenshotPath;
    }
}
