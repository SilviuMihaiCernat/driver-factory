package core.factory;

import core.dto.EnvironmentData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface WebDriverManager {
    /**
     * This method initialized the driver for a given environment environment.test.data
     */
    WebDriver getDriver();

    /**
     * This retrieves the desired capabilities for a given environment environment.test.data
     */
    DesiredCapabilities getDesiredCapabilities(EnvironmentData environmentData);
}