package pageDesign;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage {

    public static AndroidDriver<AndroidElement> driver;

    public FormPage(AndroidDriver<AndroidElement> driver) {

        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    WebElement location;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    WebElement yourName;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    WebElement gender;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    WebElement shopButton;

    public WebElement getCountry() {
        return location;
    }

    public WebElement getYourName() {
        return yourName;
    }

    public WebElement getGenderRadioButton() {
        return gender;
    }

    public ProductsPage getShopButton() {
        shopButton.click();
        ProductsPage material = new ProductsPage(driver);
        return material;
    }
}
