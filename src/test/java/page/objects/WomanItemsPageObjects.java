package page.objects;

import com.google.common.collect.ImmutableMap;
import page.objects.base.BasePage;

public class WomanItemsPageObjects extends BasePage {
    @Override
    protected void fillMyDict() {
        myDict = ImmutableMap.<String, String>builder()
                .put("ContainerFadedShortSleeveTShirt",                                         "//a[@title='Faded Short Sleeve T-shirts']/ancestor::div[@class='product-container']")
                .put("ButtonMoreFadedShortSleeveTShirt",                                         "//a[@title='Faded Short Sleeve T-shirts']/ancestor::div[@class='product-container']//a[@title='View']")
                .put("ButtonAddItemToCart",                                                 "//button[@name='Submit']")
                .put("ButtonProceedToCheckoutInItemsForm",                                  "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
                .build();
    }
}
