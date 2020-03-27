package auto.tests.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import page.objects.MainLoginPageObjects;

import static core.factory.DriverFactory.cleanUp;
import static core.factory.DriverFactory.initializeDriver;

public class BaseTest {
    protected MainLoginPageObjects mlp;
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    protected void initialize() {
        initializeDriver();
    }

    @BeforeMethod
    protected void beforeMethod(){
        mlp = new MainLoginPageObjects();
    }

    @AfterClass
    protected void afterClass(){
        cleanUp();
    }

}
