import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class PracticeFormTest {
    File file = new File("src/test/resources/1.png");
    private final String firstName = "Semyon";
    private String lastName = "lastName";
    private String userEmail = "test@test.test";
    private String gender = "Male";
    private Integer userNumber = 1111111111;
    private String month = "December";
    private Integer year = 1999;
    private Integer day = 30;
    private String subjects = "Math";
    private String hobby = "Sports";
    private String address = "SPB";

    @Test
    void checkRegistrationPracticeForm() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(userNumber.toString());
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").$(byText(month)).click();
        $(".react-datepicker__year-select").$(byText(year.toString())).click();

        String selector = "react-datepicker__day react-datepicker__day--0" + day;
        $("[class='" + selector + "']").click();

        $("#subjectsInput").setValue(subjects).pressEnter();
        ;
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").setValue(address);
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();

        $("#submit").click();

        ElementsCollection param = $(".table-responsive").$$("tr");

        checkParamSubmittingForm(param, "Student Name", firstName + " " + lastName);
        checkParamSubmittingForm(param, "Student Email", userEmail);
        checkParamSubmittingForm(param, "Gender", gender);
        checkParamSubmittingForm(param, "Mobile", userNumber.toString());
        checkParamSubmittingForm(param, "Date of Birth", day + " " + month + "," + year);
        checkParamSubmittingForm(param, "Subjects", subjects);
        checkParamSubmittingForm(param, "Hobbies", hobby);
        checkParamSubmittingForm(param, "Address", address);
        checkParamSubmittingForm(param, "State and City", "NCR Delhi");

    }

    void checkParamSubmittingForm(ElementsCollection param, String labels, String values) {
        param.findBy(text(labels)).shouldHave(text(values));
    }
}
