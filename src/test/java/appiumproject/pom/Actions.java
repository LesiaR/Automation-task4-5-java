package appiumproject.pom;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Actions {

    public final static int TIMEOUT = 50;

    public static void waitForDisplayed(MobileElement mobileElement, AndroidDriver<MobileElement> driver){
        WebDriverWait wait = new WebDriverWait (driver, TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(mobileElement));
    }

    public static void waitLoaded(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
