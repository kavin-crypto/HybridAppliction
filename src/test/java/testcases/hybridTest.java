package testcases;

import capabilities.appsInvoke;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageDesign.CheckoutPage;
import pageDesign.FormPage;
import pageDesign.ProductsPage;

import java.io.IOException;
import java.util.Set;

public class hybridTest extends appsInvoke {
    public AndroidDriver<AndroidElement> driver;
    FormPage fp;
    ProductsPage material;
    CheckoutPage checkout;
    private static final Logger log = LogManager.getLogger(hybridTest.class.getName());


    @Test
    public void StoreLogin() throws IOException, InterruptedException {
        driver = Store();
        fp = new FormPage(driver);
        fp.getCountry().click();
        String location = "Belgium";
        log.info("Selecting location: " + location);
        Scroll(location);
        //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Aruba\"));").click();

        String name = "Ankitha";
        log.info("Entering username: " + name);
        fp.getYourName().sendKeys(name);
        fp.getGenderRadioButton().click();
        material = fp.getShopButton();
    }

    @Test(dependsOnMethods = "StoreLogin")
    public void StoreProducts() {
        scroll("com.androidsample.generalstore:id/rvProductList", "Air Jordan 9 Retro");
        int count = material.getShoeProduct().size();  //driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        log.info("Select the shoe");
        for (int i = 0; i < count; i++) {
            String model = material.getShoeProduct().get(i).getText();

            if (model.equalsIgnoreCase("Air Jordan 9 Retro")) {
                material.getProductAddCart().get(i).click();

            }
        }

        checkout = material.getCartButton();

    }

    @Test(dependsOnMethods = "StoreProducts")
    public void StoreCart() throws InterruptedException {
        checkout.Wait();
        log.info("Conforming selected shoe is same in checkout page");
        String text = checkout.getProductConformation().getText();
        Assert.assertEquals("Air Jordan 9 Retro", text);

        checkout.getCheckbox().click();

        AndroidElement TC = checkout.getTerms();
        TouchAction act = new TouchAction(driver);

        act.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(TC))).release().perform();
        checkout.getClose().click();

        checkout.getProceed().click();
        //Thread.sleep(6000);
        //switching to web

        Set<String> webcontexts = driver.getContextHandles();
        for (String name : webcontexts) {
            System.out.println(name);
        }
        driver.context("WEBVIEW_com.androidsample.generalstore");
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.navigate().back();
    }
}
