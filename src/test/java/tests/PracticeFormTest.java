package tests;

import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import pages.components.SubmittingFormComponent;


public class PracticeFormTest extends Data {

    PracticeFormPage practiceForm = new PracticeFormPage();
    SubmittingFormComponent submittingForm = new SubmittingFormComponent();

    @Test
    void checkRegistrationPracticeFormTest() {
        practiceForm.openDemoQaForm()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .chooseGender(gender)
                .setUserNumber(userNumber.toString())
                .setCalendar(day.toString(), month, year.toString())
                .setSubjects(subjects)
                .setHobby(hobby)
                .uploadFile(file)
                .setAddress(address)
                .setStateAndCity(state, city)
                .openSubmittingForm();
        submittingForm.checkParamSubmittingForm("Student Name", firstName + " " + lastName)
                .checkParamSubmittingForm("Student Email", userEmail)
                .checkParamSubmittingForm("Gender", gender)
                .checkParamSubmittingForm("Mobile", userNumber.toString())
                .checkParamSubmittingForm("Date of Birth", day + " " + month + "," + year)
                .checkParamSubmittingForm("Subjects", subjects)
                .checkParamSubmittingForm("Hobbies", hobby)
                .checkParamSubmittingForm("Address", address)
                .checkParamSubmittingForm("State and City", state + " " + city)
                .checkParamSubmittingForm("Picture", file);

    }

    @Test
    void shouldNotOpenFormWhenRequiredFieldsMissingTest() {
        practiceForm.openDemoQaForm().openSubmittingForm();
        submittingForm.checkModalIsNotOpen();
    }

    @Test
    void shouldOpenFormWithMinimalRequiredField() {
        practiceForm.openDemoQaForm()
                .openSubmittingForm()
                .setFirstName(firstName)
                .setLastName(lastName)
                .chooseGender(gender)
                .setUserNumber(userNumber.toString())
                .openSubmittingForm();
        submittingForm.checkParamSubmittingForm("Student Name", firstName + " " + lastName)
                .checkParamSubmittingForm("Gender", gender)
                .checkParamSubmittingForm("Mobile", userNumber.toString());
    }
}
