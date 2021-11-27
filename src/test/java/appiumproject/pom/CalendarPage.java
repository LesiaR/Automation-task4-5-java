package appiumproject.pom;

import appiumproject.api.models.TripResponse;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CalendarPage {

    AndroidDriver<MobileElement> driver;
    int expectedDay;
    String expectedMonthYear;
    MobileElement dayOfMonth;

    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.widget.TextView")
    private List<MobileElement> month;

    @AndroidFindBy(className ="androidx.recyclerview.widget.RecyclerView" )
    private MobileElement calendar;

    @AndroidFindBy(id = "com.ryanair.cheapflights.qadebug:id/view_button_bar_next_btn")
    private MobileElement nextButton;

    public CalendarPage(AndroidDriver<MobileElement> driver) {
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private void scrollToDate(String flightDate){
        Actions.waitForDisplayed(calendar, driver);
        LocalDate expectedDate = convertDate(flightDate);
        int exMonth = expectedDate.getMonthValue();
        int exYear = expectedDate.getYear();
        String monthName = new DateFormatSymbols().getMonths()[exMonth-1];
        expectedMonthYear = monthName+" "+exYear;

        MobileElement monthYearActual = month.get(0);
        MobileElement monthYearActualNext = month.get(1);

        while(!((monthYearActual.getText()).equals(expectedMonthYear))){
            swipeToBottom(monthYearActualNext, monthYearActual);
            monthYearActual = month.get(0);
            monthYearActualNext = month.get(1);
        }
    }

    private void clickDayOfMonth(String flightDate){
        LocalDate expectedDate = convertDate(flightDate);
        expectedDay = expectedDate.getDayOfMonth();
        dayOfMonth = driver.findElementByAndroidUIAutomator("new UiSelector().text(\""+expectedDay+"\")");
        Actions.waitForDisplayed(dayOfMonth, driver);
        dayOfMonth.click();
    }

    private void clickNext(){
        Actions.waitForDisplayed(nextButton, driver);
        nextButton.click();
    }

    public void selectDate(TripResponse tripResponse){
        scrollToDate(tripResponse.getOutBoundJourney().getFlightDate());
        clickDayOfMonth(tripResponse.getOutBoundJourney().getFlightDate());
        clickNext();
    }

    private void swipeToBottom(MobileElement element1, MobileElement element2)
    {
        TouchAction ts = new TouchAction(driver);
        Point pointEl1 = element1.getLocation();
        Point pointEl2 = element2.getLocation();
        PointOption point = new PointOption();
        ts
                .press(point.point(pointEl1))
                .moveTo(point.point(pointEl2))
                .release()
                .perform();
    }

    public LocalDate convertDate(String date){
        String pattern = "yyyy-MM-d";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        return parsedDate;
    }
}
