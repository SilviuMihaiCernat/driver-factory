package core.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilityCore {
    public static final Logger logger = LoggerFactory.getLogger(UtilityCore.class);
    public static final String driverLocation = "src/test/resources/drivers/";
    public static final String gridUrl = "http://10.2.112.50:4444";

    public static String getSystemProperty(String systemProperty){
        String systemPropertyValue = System.getProperty(systemProperty);
        if (systemPropertyValue == null){
            logger.error("No system property has been found for parameter: " + systemProperty);
            return "";
        } else return systemPropertyValue;
    }
}
