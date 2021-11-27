package appiumproject.steps;

import appiumproject.SetUp;
import appiumproject.api.models.TripRequest;
import appiumproject.pom.CalendarPage;
import appiumproject.pom.FlightPage;
import appiumproject.pom.HomePage;
import appiumproject.pom.SearchPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class TestContext {
    private AndroidDriver<MobileElement> driver;
    private TripRequest tripRequest;
    private HomePage homePage;
    private SearchPage searchPage;
    private CalendarPage calendarPage;
    private FlightPage flightPage;

    public TestContext(){
        driver = SetUp.setCapabilitiesAppium();
        tripRequest = new TripRequest();
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        calendarPage = new CalendarPage(driver);
        flightPage = new FlightPage(driver);
    }

    public AndroidDriver<MobileElement> getDriver(){
        return driver;
    }

    public TripRequest getTripRequest(){
        return tripRequest;
    }

    public HomePage getHomePage(){
        return homePage;
    }

    public SearchPage getSearchPage(){
        return searchPage;
    }

    public CalendarPage getCalendarPage(){
        return calendarPage;
    }

    public FlightPage getFlightPage(){
        return flightPage;
    }
}
