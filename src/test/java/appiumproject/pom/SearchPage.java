package appiumproject.pom;

import appiumproject.api.models.TripResponse;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    AndroidDriver<MobileElement> driver;

    @AndroidFindBy(id = "com.ryanair.cheapflights.qadebug:id/trip_one_way")
    private MobileElement oneWayButton;

    @AndroidFindBy(id = "com.ryanair.cheapflights.qadebug:id/hint_from")
    private MobileElement fromButton;

    @AndroidFindBy(id = "com.ryanair.cheapflights.qadebug:id/hint_to")
    private MobileElement toButton;

    @AndroidFindBy(id = "com.ryanair.cheapflights.qadebug:id/view_search_edit_text")
    private MobileElement searchView;

    @AndroidFindBy(id = "com.ryanair.cheapflights.qadebug:id/airport_code")
    private MobileElement airport;

    @AndroidFindBy(id = "com.ryanair.cheapflights.qadebug:id/select_date_fly_out")
    private MobileElement flyOutButton;

    @AndroidFindBy(id = "com.ryanair.cheapflights.qadebug:id/cta_search")
    private MobileElement letsgoButton;

    public SearchPage (AndroidDriver<MobileElement> driver){
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private void selectOnewayTrip(){
        Actions.waitForDisplayed(oneWayButton, driver);
        oneWayButton.click();
    }

    private void selectFromAirport(String origin){
        fromButton.click();
        Actions.waitForDisplayed(searchView, driver);
        searchView.sendKeys(origin);
        airport.click();
    }

    private void selectToAirport(String destination){
        Actions.waitForDisplayed(toButton, driver);
        toButton.click();
        Actions.waitForDisplayed(searchView, driver);
        searchView.sendKeys(destination);
        airport.click();
    }
    private void openCalendarPage() {
        Actions.waitForDisplayed(flyOutButton, driver);
        flyOutButton.click();
    }

    private void clickLetsgo(){
        Actions.waitForDisplayed(letsgoButton, driver);
        letsgoButton.click();
    }

    public void searchForOnewayTrip(TripResponse tripResponse, CalendarPage calendarPage){
        selectOnewayTrip();
        selectFromAirport(tripResponse.getOutBoundJourney().getOrigin());
        selectToAirport(tripResponse.getOutBoundJourney().getDestination());
        openCalendarPage();
        calendarPage.selectDate(tripResponse);
        clickLetsgo();
    }
}
