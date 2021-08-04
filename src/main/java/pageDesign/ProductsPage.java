package pageDesign;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage {

    public static AndroidDriver<AndroidElement> driver;

    public ProductsPage(AndroidDriver<AndroidElement> driver) {

        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    public List<AndroidElement> shoe;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
    public List<AndroidElement> productAddCart;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    public WebElement cart;

    public List<AndroidElement> getShoeProduct() {
        return shoe;
    }

    public List<AndroidElement> getProductAddCart() {
        return productAddCart;
    }

    public CheckoutPage getCartButton() {
        cart.click();
        CheckoutPage checkout = new CheckoutPage(driver);
        return checkout;
    }
}
