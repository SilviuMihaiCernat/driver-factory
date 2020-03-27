package page.objects;

import com.google.common.collect.ImmutableMap;
import page.objects.base.BasePage;

public class OrderCheckoutPageObjects extends BasePage {
    @Override
    protected void fillMyDict() {
        myDict = ImmutableMap.<String, String>builder()
                .put("ButtonProceedToCheckoutInSummaryForm",                                     "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
                .put("ButtonProceedToCheckoutInAddressForm",                                     "//button[@name='processAddress']//span")
                .put("ButtonProceedToCheckoutInShippingForm",                                    "//button[@name='processCarrier']//span")
                .put("CheckBoxTermsAndConditions",                                               "//div[@id='uniform-cgv']")
                .put("ButtonPayByBankWire",                                                      "//a[@class='bankwire']")
                .put("ButtonIConfirmMyOrder",                                                    "//p[@id='cart_navigation']//span")
                .put("FinishedOrderConfirmationStep",                                            "//li[@class='step_done step_done_last four']")
                .put("LastOrderConfirmationStep",                                                "//li[@id='step_end' and @class='step_current last']")
                .put("OrderConfirmationMessage",                                                 "//*[@class='cheque-indent']/strong")
                .put("TextOrderConfirmationHeading",                                             "//h1[@class='page-heading']")
                .build();
    }
}
