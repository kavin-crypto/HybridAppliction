package pageDesign;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.collections.ArrayIterator;

import java.util.List;

public class CheckoutPage {

    public static AndroidDriver<AndroidElement> driver;
    public CheckoutPage(AndroidDriver<AndroidElement> driver) {

        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    public WebElement productConformation;

    @AndroidFindBy(className = "android.widget.CheckBox")
    public WebElement checkbox;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
    public AndroidElement terms;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Close']")
    public WebElement close;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
    public WebElement proceed;

    public WebElement getProductConformation() {
        return productConformation;
    }

    public WebElement getCheckbox() {
        return checkbox;
    }

    public AndroidElement getTerms() {
        return terms;
    }

    public WebElement getClose() {
        return close;
    }

    public WebElement getProceed() {
        return proceed;
    }
    public WebElement Wait()
    {
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.androidsample.generalstore:id/productName")));
        return productConformation;
    }

}
