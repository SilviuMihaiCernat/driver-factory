package page.objects;

import com.google.common.collect.ImmutableMap;
import page.objects.base.BasePage;

public class MyAccountPageObjects extends BasePage {
    @Override
    protected void fillMyDict() {
        myDict = ImmutableMap.<String, String>builder()
                .put("TextMyAccountHeading",                                        "//h1[@class='page-heading']")
                .put("TextAccountFirstAndLastName",                                 "//a[@class='account']/span")
                .put("TextAccountInfoGreeting",                                     "//p[@class='info-account']")
                .put("ButtonAccountSignOut",                                        "//a[@class='logout']")
                .put("ButtonWomanItems",                                            "//a[@title='Women']")
                .build();
    }
}
