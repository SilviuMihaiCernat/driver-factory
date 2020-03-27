package auto.tests;

import auto.tests.base.BaseTest;
import org.testng.annotations.Test;
import page.objects.CreateAccountPageObjects;
import page.objects.MyAccountPageObjects;

import static core.factory.DriverFactory.takeScreenshot;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class TC01ClientCreationTest extends BaseTest {
    private CreateAccountPageObjects cap;
    private MyAccountPageObjects map;
    private String customerEmailAccount = "";
    private String customerFirstName = "";
    private String customerLastName = "";

    @Test
    public void signInTest(){
        cap = new CreateAccountPageObjects();
        map = new MyAccountPageObjects();

        customerFirstName = mlp.testData.get("FirstName");
        customerLastName = mlp.testData.get("LastName");
        customerEmailAccount = mlp.generateRandomEmail();

        logger.info("Proceed to customer creation form");
        mlp.createCustomerAccount(customerEmailAccount);

        logger.info("Fill page with personal information");
        cap.clickOnElement("RadioButtonGenderMrs");
        cap.sendTextToInputBox("InputBoxCustomerFirstName",customerFirstName);
        cap.sendTextToInputBox("InputBoxCustomerLastName",customerLastName);
        cap.sendTextToInputBox("InputBoxCustomerEmail",customerEmailAccount);
        cap.sendTextToInputBox("InputBoxCustomerPassword",mlp.testData.get("Password"));
        cap.selectItemFromDropDownBasedOnValue("DropDownDateOfBirthDays",mlp.testData.get("DateOfBirthDays"));
        cap.selectItemFromDropDownBasedOnValue("DropDownDateOfBirthMonths",mlp.testData.get("DateOfBirthMonths"));
        cap.selectItemFromDropDownBasedOnValue("DropDownDateOfBirthYears",mlp.testData.get("DateOfBirthYears"));

        logger.info("Fill page with address information");
        cap.sendTextToInputBox("InputBoxCustomerCompany",mlp.testData.get("Company"));
        cap.sendTextToInputBox("InputBoxCustomerFirstAddress",mlp.testData.get("FirstHomeAddress"));
        cap.sendTextToInputBox("InputBoxCustomerSecondAddress",mlp.testData.get("SecondHomeAddress"));
        cap.sendTextToInputBox("InputBoxCustomerCity",mlp.testData.get("City"));
        cap.selectItemFromDropDownBasedOnText("DropDownCustomerState",mlp.testData.get("State"));
        cap.sendTextToInputBox("InputBoxCustomerZipCode",mlp.testData.get("ZipCode"));
        cap.selectItemFromDropDownBasedOnText("DropDownCustomerCountry",mlp.testData.get("Country"));
        cap.sendTextToInputBox("InputBoxCustomerAdditionalInfo",mlp.testData.get("AdditionalInfo"));
        cap.sendTextToInputBox("InputBoxCustomerHomePhone",mlp.testData.get("HomePhone"));
        cap.sendTextToInputBox("InputBoxCustomerMobilePhone",mlp.testData.get("MobilePhone"));
        cap.sendTextToInputBox("InputBoxCustomerAddressAlias",mlp.testData.get("AddressAlias"));

        logger.info("Submit information for account creation");
        cap.clickOnElement("ButtonRegisterAccount");
        map.waitForElementToBeDisplayed("TextMyAccountHeading");

        try {
            assertEquals(map.getElementText("TextMyAccountHeading").toUpperCase()
                    ,"MY ACCOUNT"
                    ,"Check if page heading is equal to My Account");
            assertEquals(map.getElementText("TextAccountFirstAndLastName")
                    ,customerFirstName + " " + customerLastName
                    ,"Check if the correct text is displayed in the account box");
            assertTrue(map.getElementText("TextAccountInfoGreeting").contains("Welcome to your account.")
                    ,"Check if info greeting contains text");
            assertTrue(map.isElementDisplayed("ButtonAccountSignOut")
                    ,"Check if logout button is displayed");
            assertTrue(map.getCurrentPageURL().contains("controller=my-account")
                    ,"Check if current URL contains text");
        }catch (AssertionError e){
            logger.error(e.getMessage());
            fail(e.getMessage());
            takeScreenshot();
        }
        map.clickOnElement("ButtonAccountSignOut");
    }

    @Test
    public void logInTest(){
        logger.info("Proceed to customer creation form");
        mlp.signInCustomerAccount(customerEmailAccount,mlp.testData.get("Password"));
        map.waitForElementToBeDisplayed("TextMyAccountHeading");

        try {
            assertEquals(map.getElementText("TextMyAccountHeading").toUpperCase()
                    ,"MY ACCOUNT"
                    ,"Check if page heading is equal to My Account");
            assertEquals(map.getElementText("TextAccountFirstAndLastName")
                    , mlp.testData.get("FirstName") + " " + mlp.testData.get("LastName")
                    ,"Check if the correct text is displayed in the account box");
            assertTrue(map.getElementText("TextAccountInfoGreeting").contains("Welcome to your account.")
                    ,"Check if info greeting contains text");
            assertTrue(map.isElementDisplayed("ButtonAccountSignOut")
                    ,"Check if logout button is displayed");
            assertTrue(map.getCurrentPageURL().contains("controller=my-account")
                    ,"Check if current URL contains text");

        }catch (AssertionError e){
            logger.error(e.getMessage());
            fail(e.getMessage());
            takeScreenshot();
        }
    }
}