package core.factory;

import core.utility.UtilityCore;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriver {
    /**
     * This method initialized the driver for a given environment
     */
    public WebDriver getDriver(DesiredCapabilities desiredCapabilities) {
        URL gridUrl = null;
        try {
            gridUrl = new URL(UtilityCore.gridUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        WebDriver driver = new RemoteWebDriver(gridUrl,desiredCapabilities);
        return driver;
    }
}
