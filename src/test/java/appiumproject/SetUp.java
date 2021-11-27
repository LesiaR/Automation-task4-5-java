package appiumproject;

import appiumproject.pom.Actions;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class SetUp {

    public static AndroidDriver setCapabilitiesAppium() {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android emulator");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
        cap.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        cap.setCapability("appPackage", "com.ryanair.cheapflights.qadebug");
        cap.setCapability("appActivity", "com.ryanair.cheapflights.ui.DispatcherActivity");
        URL url = null;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        AndroidDriver<MobileElement> driver = new AndroidDriver<>(url, cap);
        System.out.println("Started");
        Actions.waitLoaded();
        return driver;
    }
}
