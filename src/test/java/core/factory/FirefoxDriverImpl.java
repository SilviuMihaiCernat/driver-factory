package core.factory;

import core.dto.EnvironmentData;
import core.utility.UtilityCore;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriverImpl implements WebDriverManager{
    /**
     * This method initialized the driver for a given environment
     */
    @Override
    public WebDriver getDriver(){
        WebDriver driver;
        String WEB_DRIVER_FIREFOX = "webdriver.gecko.driver";
        String FIREFOX_DRIVER_SERVER_LOCATION = UtilityCore.driverLocation + "geckodriver.exe";
        System.setProperty(WEB_DRIVER_FIREFOX, FIREFOX_DRIVER_SERVER_LOCATION);
        driver = new org.openqa.selenium.firefox.FirefoxDriver();
        return driver;
    }

    @Override
    public DesiredCapabilities getDesiredCapabilities(EnvironmentData environmentData) {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        if("windows".equalsIgnoreCase(environmentData.getOperatingSystem())){
            capabilities.setPlatform(Platform.WINDOWS);
        } else {
            capabilities.setPlatform(Platform.MAC);
        }
        capabilities.setBrowserName("firefox");
        capabilities.setVersion(environmentData.getOperatingSystemVersion());
        return capabilities;
    }
}
