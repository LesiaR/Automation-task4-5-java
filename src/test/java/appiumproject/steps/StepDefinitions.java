package appiumproject.steps;

import appiumproject.api.HttpClient;
import appiumproject.api.models.*;
import appiumproject.pom.*;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

    TestContext testContext;
    AndroidDriver<MobileElement> driver;
    TripRequest tripRequest;
    TripResponse tripResponse;
    HomePage homePage;
    SearchPage searchPage;
    CalendarPage calendarPage;
    FlightPage flightPage;

    public StepDefinitions(TestContext testContext){
        this.testContext = testContext;
        driver = testContext.getDriver();
        tripRequest = testContext.getTripRequest();
        homePage = testContext.getHomePage();
        searchPage = testContext.getSearchPage();
        calendarPage = testContext.getCalendarPage();
        flightPage = testContext.getFlightPage();
    }

    @Given("^Basic trip on env: (.*)$")
//    Basic booking -> 1A Value, Single, International, Partial
    public void set_environment (String env){
        tripRequest.setEnv(env);
    }

    @Given("^I have found an international trip")
    public void i_have_found_an_international_trip() {
        tripResponse = HttpClient.getTrip(tripRequest);
    }

    @When("^I search that trip on Find flights page$")
    public void i_search_trip_on_find_flights_page(){
        homePage.openSearch();
        searchPage.searchForOnewayTrip(tripResponse, calendarPage);
    }

    @Then("^I verify I am redirected to flight selection page$")
    public void i_verify_redirection_to_flight_selection_page(){
        flightPage.verifyPageDisplayed();
    }
}
