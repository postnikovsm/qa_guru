package pages.components;

import com.codeborne.selenide.SelenideElement;
import pages.PracticeFormPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    private final SelenideElement monthElement = $(".react-datepicker__month-select"),
            yearElement = $(".react-datepicker__year-select");
    String dayElement ="react-datepicker__day react-datepicker__day--0";

    public PracticeFormPage setCalendar(String day, String month, String year) {
        monthElement.$(byText(month)).click();
        yearElement.$(byText(year)).click();
        $("[class='" + dayElement +day + "']").click();
        return new PracticeFormPage();
    }
}
