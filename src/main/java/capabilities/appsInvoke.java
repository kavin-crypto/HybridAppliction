package capabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class appsInvoke {
    public static AndroidDriver<AndroidElement> driver;
    public static Properties pro;

    public static AndroidDriver<AndroidElement> Store() throws IOException {
        pro = new Properties();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/data.properties");
        pro.load(file);

        //String application = pro.getProperty("Browser");

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("deviceName", pro.getProperty("DeviceName"));
        cap.setCapability("udid", pro.getProperty("DeviceConnection"));
        cap.setCapability("appPackage", pro.getProperty("AppPackageName"));
        cap.setCapability("appActivity", pro.getProperty("AppActivity"));

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    public void Scroll(String text) {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));").click();
    }

    public void scroll(String location, String txt) {
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"" + location + "\")).scrollIntoView(new UiSelector().textMatches(\"" + txt + "\").instance(0))"));

    }

    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) this.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "/Screenshot/" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;
    }

}
