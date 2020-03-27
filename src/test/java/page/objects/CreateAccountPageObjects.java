package page.objects;

import com.google.common.collect.ImmutableMap;
import page.objects.base.BasePage;

public class CreateAccountPageObjects extends BasePage {
    @Override
    protected void fillMyDict() {
        myDict = ImmutableMap.<String, String>builder()
//===============================================Personal Information=============================================================
                .put("RadioButtonGenderMr",                                         "//input[@id='id_gender1']")
                .put("RadioButtonGenderMrs",                                        "//input[@id='id_gender2']")
                .put("InputBoxCustomerFirstName",                                   "//input[@id='customer_firstname']")
                .put("InputBoxCustomerLastName",                                    "//input[@id='customer_lastname']")
                .put("InputBoxCustomerEmail",                                       "//input[@id='email']")
                .put("InputBoxCustomerPassword",                                    "//input[@id='passwd']")
                .put("DropDownDateOfBirthDays",                                     "#days")
                .put("DropDownDateOfBirthMonths",                                   "#months")
                .put("DropDownDateOfBirthYears",                                    "#years")
//===============================================Customer Address==================================================================
                .put("InputBoxCustomerCompany",                                      "#company")
                .put("InputBoxCustomerFirstAddress",                                 "#address1")
                .put("InputBoxCustomerSecondAddress",                                "#address2")
                .put("InputBoxCustomerCity",                                         "#city")
                .put("DropDownCustomerState",                                        "#id_state")
                .put("InputBoxCustomerZipCode",                                      "#postcode")
                .put("DropDownCustomerCountry",                                      "#id_country")
                .put("InputBoxCustomerAdditionalInfo",                               "#other")
                .put("InputBoxCustomerHomePhone",                                    "#phone")
                .put("InputBoxCustomerMobilePhone",                                  "#phone_mobile")
                .put("InputBoxCustomerAddressAlias",                                 "#alias")
                .put("ButtonRegisterAccount",                                        "//button[@id='submitAccount']")
                .build();
    }

}
