package core.factory;

import core.dto.EnvironmentData;
import core.utility.UtilityCore;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static WebDriver driver;
    private static WebDriverManager webDriverManager;
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
    private static final long implicitWaits = 10;
    private static final EnvironmentData environmentData = loadEnvironmentData();

    public static WebDriver getDriver() {
        return driver;
    }

    public static EnvironmentData getEnvironmentData(){
        return environmentData;
    }

    /**
     * This method initializes the driver
     */
    public static void initializeDriver(){
        getDriverManager(environmentData);
        if (environmentData.isRemoteRun()){
            driver = new RemoteDriver().getDriver(webDriverManager.getDesiredCapabilities(environmentData));
        } else {
            driver = webDriverManager.getDriver();
        }
        driver.manage().timeouts().implicitlyWait(implicitWaits, TimeUnit.SECONDS);
    }

    /**
     * Close all the browser instances opened by the framework.
     */
    public static void cleanUp(){
        driver.close();
        driver.quit();
        driver = null;
    }

    /**
     * This Method takes a screenshot and moves it to the target folder
     */
    public static void takeScreenshot() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
        Date date = new Date();
        String fileName = Thread.currentThread().getStackTrace()[2].getFileName().replace(".java", "");
        String SCREENSHOTS_FOLDER = "target/";
        String destFile = SCREENSHOTS_FOLDER + fileName + "-" + dateFormat.format(date) + ".png";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(destFile));
        } catch (IOException e) {
            logger.error("File could not be copied to the new destination: " + e.getMessage());
        }
    }

    /**
     * This factory method initializes the main member variable with the correct WebDriver object
     */
    private static void getDriverManager(EnvironmentData environmentData){
        switch (environmentData.getBrowser().toLowerCase()) {
            case "chrome":
                webDriverManager = new ChromeDriverImpl();
                break;
            case "firefox":
                webDriverManager = new FirefoxDriverImpl();
                break;
            default:
                webDriverManager = new ChromeDriverImpl();
                break;
        }
    }

    private static EnvironmentData loadEnvironmentData(){
        EnvironmentData environmentData = new EnvironmentData();
        environmentData.setBrowser(UtilityCore.getSystemProperty("browser"));
        environmentData.setEnvironment(UtilityCore.getSystemProperty("environment"));
        environmentData.setOperatingSystem(UtilityCore.getSystemProperty("os"));
        environmentData.setOperatingSystemVersion(UtilityCore.getSystemProperty("osVersion"));
        if (UtilityCore.getSystemProperty("isRemote").isEmpty()){
            environmentData.setRemoteRun(false);
        } else {
            environmentData.setRemoteRun(Boolean.valueOf(UtilityCore.getSystemProperty("isRemote")));
        }
        return environmentData;
    }
}
