package auto.tests;

import auto.tests.base.BaseTest;
import org.testng.annotations.Test;
import page.objects.MyAccountPageObjects;
import page.objects.OrderCheckoutPageObjects;
import page.objects.WomanItemsPageObjects;

import static core.factory.DriverFactory.takeScreenshot;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class TC02ItemCheckoutTest extends BaseTest {
    private MyAccountPageObjects map;
    private WomanItemsPageObjects wip;
    private OrderCheckoutPageObjects ocp;

    @Test
    public void itemCheckoutTest(){
        map = new MyAccountPageObjects();
        wip = new WomanItemsPageObjects();
        ocp = new OrderCheckoutPageObjects();

        logger.info("Proceed to customer creation form");
        mlp.signInCustomerAccount(mlp.testData.get("Email"),mlp.testData.get("Password"));

        logger.info("Proceed to woman items page");
        map.clickOnElement("ButtonWomanItems");

        logger.info("Select an item and add to chart");
        wip.goToElementAndClick("ContainerFadedShortSleeveTShirt","ButtonMoreFadedShortSleeveTShirt");
        wip.clickOnElement("ButtonAddItemToCart");
        wip.clickOnElement("ButtonProceedToCheckoutInItemsForm");

        logger.info("Finish checking out the order");
        ocp.clickOnElement("ButtonProceedToCheckoutInSummaryForm");
        ocp.clickOnElement("ButtonProceedToCheckoutInAddressForm");
        ocp.clickOnElement("CheckBoxTermsAndConditions");
        ocp.clickOnElement("ButtonProceedToCheckoutInShippingForm");
        ocp.clickOnElement("ButtonPayByBankWire");
        ocp.clickOnElement("ButtonIConfirmMyOrder");

        ocp.waitForElementToBeDisplayed("TextOrderConfirmationHeading");

        try {
            assertEquals(ocp.getElementText("TextOrderConfirmationHeading").toUpperCase()
                    ,"ORDER CONFIRMATION"
                    ,"Check if page heading is equal to Order Confirmation");
            assertTrue(ocp.isElementDisplayed("FinishedOrderConfirmationStep")
                    ,"Check if the user has reached the last step" );
            assertTrue(ocp.isElementDisplayed("LastOrderConfirmationStep")
                    ,"Check if the user has finished the last step" );
            assertTrue(map.getCurrentPageURL().contains("controller=order-confirmation")
                    ,"Check if current URL contains text");

        }catch (AssertionError e){
            logger.error(e.getMessage());
            fail(e.getMessage());
            takeScreenshot();
        }
    }
}
