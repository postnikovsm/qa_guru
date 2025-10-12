package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormPage {

    CalendarComponent calendar = new CalendarComponent();
    private final SelenideElement firstNameElement = $("#firstName"),
            lastNameElement = $("#lastName"),
            userEmailElement = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberElement = $("#userNumber"),
            dateOfBirthElement = $("#dateOfBirthInput"),
            subjectsElement = $("#subjectsInput"),
            hobbiesElement = $("#hobbiesWrapper"),
            uploadElement = $("#uploadPicture"),
            currentAddressElement = $("#currentAddress"),
            stateElement = $("#react-select-3-input"),
            cityElement = $("#react-select-4-input"),
            submitElement = $("#submit");


    public PracticeFormPage openDemoQaForm() {
        open("/automation-practice-form");
        executeJavaScript("$('#mys-overlay').remove();");
        return this;
    }

    public PracticeFormPage setFirstName(String firstName) {
        firstNameElement.setValue(firstName);
        return this;
    }

    public PracticeFormPage setLastName(String lastName) {
        lastNameElement.setValue(lastName);
        return this;
    }

    public PracticeFormPage setUserEmail(String userEmail) {
        userEmailElement.setValue(userEmail);
        return this;
    }

    public PracticeFormPage chooseGender(String gender) {
        genderWrapper.$(byText(gender)).click();
        return this;
    }

    public PracticeFormPage setUserNumber(String userNumber) {
        userNumberElement.setValue(userNumber);
        return this;
    }

    public PracticeFormPage setCalendar(String day, String month, String year) {
        dateOfBirthElement.click();
        calendar.setCalendar(day, month, year);
        return this;
    }

    public PracticeFormPage setSubjects(String subjects) {
        subjectsElement.setValue(subjects).pressEnter();
        return this;
    }

    public PracticeFormPage setHobby(String hobby) {
        hobbiesElement.$(byText(hobby)).click();
        return this;
    }

    public PracticeFormPage uploadFile(String hobby) {
        uploadElement.uploadFromClasspath("1.png");
        return this;
    }

    public PracticeFormPage setAddress(String address) {
        currentAddressElement.setValue(address);
        return this;
    }

    public PracticeFormPage setStateAndCity(String state, String city) {
        stateElement.setValue(state).pressEnter();
        cityElement.setValue(city).pressEnter();
        return this;
    }

    public PracticeFormPage openSubmittingForm() {
        submitElement.click();
        return new PracticeFormPage();
    }
}
