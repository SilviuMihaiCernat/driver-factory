package page.objects.base;

import com.google.common.collect.ImmutableMap;
import core.utility.LoadTestData;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.LinkedHashMap;

import static core.factory.DriverFactory.getDriver;
import static core.factory.DriverFactory.getEnvironmentData;

public abstract class BasePage {
    private static final int MAX_TRIES = 4;
    protected static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    protected WebDriver driver = getDriver();
    public static LinkedHashMap<String, String> testData = LoadTestData.loadTestData(getEnvironmentData().getEnvironment());

    /**
     * This object should be filled in the ancestor.
     */
    protected ImmutableMap<String, String> myDict;

    /**
     * This method should be defined in the child class to fill the variable.
     */
    protected abstract void fillMyDict();

    /**
     * This constructor fills the dictionary of a child class
     */
    public BasePage(){
        fillMyDict();
    }

    /**
     * This method retrieves the locator used in identifying the driver web element either with xpath or css
     */
    private By getLocatorFromMyDict(String elemKey) {
        int attempts = 0;
        org.openqa.selenium.By returnValue = null;
        while(attempts < MAX_TRIES) {
            try {
                if (myDict.get(elemKey).contains("//")) {
                    returnValue = By.xpath(myDict.get(elemKey));
                    break;
                }
                else {
                    returnValue = By.cssSelector(myDict.get(elemKey));
                    break;
                }
            } catch(StaleElementReferenceException e) {
                logger.info(">>>>> Trying to recover from a stale element: " + e.getMessage());
                attempts += 1;
            }
        }
        return returnValue;
    }

    /**
     * This method click on a web element identified from the dictionary
     */
    public void clickOnElement(String elemKey){
        getElementWithFluentWait(getLocatorFromMyDict(elemKey)).click();
    }

    /**
     * This method double-clicks on a web element identified from the dictionary
     */
    public void goToElementAndClick(String goToElemKey,String clickElemKey){
        WebElement goToElement = getElementWithFluentWait(getLocatorFromMyDict(goToElemKey));
        Actions action = new Actions(driver);
        action.moveToElement(goToElement).perform();
        WebElement clickElement = getElementWithFluentWait(getLocatorFromMyDict(clickElemKey));
        action.click(clickElement).perform();
    }

    /**
     * This method retrieves the text from a web element identified from the dictionary
     */
    public String getElementText(String elemKey) {
        return driver.findElement(getLocatorFromMyDict(elemKey)).getText();
    }

    /**
     * This method waits with fluent wait from the driver class method, for a web element to be displayed
     */
    public void waitForElementToBeDisplayed(String elemKey) {
        getElementWithFluentWait(getLocatorFromMyDict(elemKey));
    }

    /**
     * This method selects an option from a given dropdown based on visible given text
     */
    public void selectItemFromDropDownBasedOnText(String elemKey, String text) {
        Select dropDown = new Select(driver.findElement(getLocatorFromMyDict(elemKey)));
        dropDown.selectByVisibleText(text);
    }

    /**
     * This method selects an option from a given dropdown based on visible given value
     */
    public void selectItemFromDropDownBasedOnValue(String elemKey, String value) {
        Select dropDown = new Select(driver.findElement(getLocatorFromMyDict(elemKey)));
        dropDown.selectByValue(value);
    }

    /**
     * This method send String text to an input box
     */
    public void sendTextToInputBox(String elemKey, String textValue){
        WebElement element = getElementWithFluentWait(getLocatorFromMyDict(elemKey));
        element.clear();
        element.sendKeys(textValue);
    }

    /**
     * This method return the page current URL
     */
    public String getCurrentPageURL() {
        String currentUrl = String.valueOf(driver.getCurrentUrl());
        return currentUrl;
    }

    /**
     * This method checks if element is displayed
     */
    public boolean isElementDisplayed(String elemKey) {
        WebElement element = driver.findElement(getLocatorFromMyDict(elemKey));
        return element.isDisplayed();
    }

    /**
     * This method treats stale element and no suck element exceptions for the flue wait method
     */
    private WebElement getElementWithFluentWait(By locator) {
        WebElement element = null;
        int attempts = 0;
        while(attempts < MAX_TRIES) {
            try {
                element = initializeFluentWait(locator, true);
                break;
            } catch(StaleElementReferenceException | NoSuchElementException e) {
                logger.info(">>>>> Trying to recover from a stale element: " + e.getMessage());
                attempts += 1;
            }
        }
        if (attempts == MAX_TRIES) {
            logger.error("More than 4 attempts timed out for locator " + locator);
            throw new RuntimeException("More than 4 attempts timed out for locator " + locator);
        }
        return element;
    }

    /**
     * This method treats initialized the fluent wait for the driver
     */
    private WebElement initializeFluentWait(By locator, boolean presenceNeeded) {
        try {
            if (presenceNeeded) {
                Wait<WebDriver> wait = new FluentWait<>(driver)
                        .withTimeout(java.time.Duration.ofSeconds(30))
                        .pollingEvery(Duration.ofSeconds(2))
                        .ignoring(NoSuchElementException.class);
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            }
        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Timeout while waiting for " + locator + ".");
        }
        return driver.findElement(locator);
    }
}
