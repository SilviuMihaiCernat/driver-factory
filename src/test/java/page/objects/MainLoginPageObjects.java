package page.objects;

import com.google.common.collect.ImmutableMap;
import core.utility.LoadTestData;
import org.apache.commons.text.RandomStringGenerator;
import page.objects.base.BasePage;

import static core.factory.DriverFactory.getEnvironmentData;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

public class MainLoginPageObjects extends BasePage {
    @Override
    protected void fillMyDict() {
        myDict = ImmutableMap.<String, String>builder()
                .put("ButtonSignIntoAccount",                                        "//a[@class='login']")
//===============================================Create an Account===============================================================
                .put("InputBoxEmailAddressForAccountCreation",                       "//input[@id='email_create']")
                .put("ButtonCreateAnAccount",                                        "//button[@id='SubmitCreate']")
//===============================================Already registered==============================================================
                .put("InputBoxEmailAddressForAccountLogin",                          "//input[@id='email']")
                .put("InputBoxPasswordForAccountLogin",                              "//input[@id='passwd']")
                .put("ButtonSignIn",                                                 "//button[@id='SubmitLogin']")
                .build();
    }

    public MainLoginPageObjects(){
        driver.get(testData.get("url"));
        driver.manage().window().maximize();
    }

    /**
     * This method generates a random string with only alpha numerics
     */
    public String generateRandomEmail(){
        RandomStringGenerator generator = new RandomStringGenerator
                .Builder()
                .withinRange('0','z')
                .filteredBy(LETTERS, DIGITS)
                .build();
        return generator.generate(10) + "@" + generator.generate(5) + ".com";
    }

    /**
     * This method proceeds directly to the account creation form
     */
    public void createCustomerAccount(String customerEmail){
        clickOnElement("ButtonSignIntoAccount");
        sendTextToInputBox("InputBoxEmailAddressForAccountCreation",customerEmail);
        clickOnElement("ButtonCreateAnAccount");
    }

    /**
     * This method signs in automatically with an user and password
     */
    public void signInCustomerAccount(String customerEmail, String customerPassword){
        clickOnElement("ButtonSignIntoAccount");
        sendTextToInputBox("InputBoxEmailAddressForAccountLogin",customerEmail);
        sendTextToInputBox("InputBoxPasswordForAccountLogin",customerPassword);
        clickOnElement("ButtonSignIn");
    }
}
