package tests;

import org.junit.jupiter.api.Test;
import pages.PracticeFormPage;
import pages.components.SubmittingFormComponent;


public class PracticeFormTest extends TestBase {

    PracticeFormPage practiceForm = new PracticeFormPage();
    SubmittingFormComponent submittingForm = new SubmittingFormComponent();
    Data data = new Data();
    DataGenerator dataFaker = new DataGenerator();


    @Test
    void checkRegistrationPracticeFormWithFakerTest() {

        practiceForm.openDemoQaForm()
                .setFirstName(dataFaker.firstName)
                .setLastName(dataFaker.lastName)
                .setUserEmail(dataFaker.userEmail)
                .chooseGender(dataFaker.gender)
                .setUserNumber(dataFaker.userNumber)
                .setCalendar(dataFaker.formattedDay, dataFaker.monthCorrect, dataFaker.year)
                .setSubjects(dataFaker.subjects)
                .setHobby(dataFaker.hobby)
                .uploadFile(dataFaker.file)
                .setAddress(dataFaker.address)
                .setStateAndCity(dataFaker.state, dataFaker.city)
                .openSubmittingForm();
        submittingForm.checkParamSubmittingForm("Student Name", dataFaker.firstName + " " + dataFaker.lastName)
                .checkParamSubmittingForm("Student Email", dataFaker.userEmail)
                .checkParamSubmittingForm("Gender", dataFaker.gender)
                .checkParamSubmittingForm("Mobile", dataFaker.userNumber)
                .checkParamSubmittingForm("Date of Birth", dataFaker.day + " " + dataFaker.month + "," + dataFaker.year)
                .checkParamSubmittingForm("Subjects", dataFaker.subjects)
                .checkParamSubmittingForm("Hobbies", dataFaker.hobby)
                .checkParamSubmittingForm("Address", dataFaker.address)
                .checkParamSubmittingForm("State and City", dataFaker.state + " " + dataFaker.city)
                .checkParamSubmittingForm("Picture", dataFaker.file);

    }

    @Test
    void checkRegistrationPracticeFormTest() {
        practiceForm.openDemoQaForm()
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setUserEmail(data.userEmail)
                .chooseGender(data.gender)
                .setUserNumber(data.userNumber.toString())
                .setCalendar(data.day.toString(), data.month, data.year.toString())
                .setSubjects(data.subjects)
                .setHobby(data.hobby)
                .uploadFile(data.file)
                .setAddress(data.address)
                .setStateAndCity(data.state, data.city)
                .openSubmittingForm();
        submittingForm.checkParamSubmittingForm("Student Name", data.firstName + " " + data.lastName)
                .checkParamSubmittingForm("Student Email", data.userEmail)
                .checkParamSubmittingForm("Gender", data.gender)
                .checkParamSubmittingForm("Mobile", data.userNumber.toString())
                .checkParamSubmittingForm("Date of Birth", data.day + " " + data.month + "," + data.year)
                .checkParamSubmittingForm("Subjects", data.subjects)
                .checkParamSubmittingForm("Hobbies", data.hobby)
                .checkParamSubmittingForm("Address", data.address)
                .checkParamSubmittingForm("State and City", data.state + " " + data.city)
                .checkParamSubmittingForm("Picture", data.file);

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
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .chooseGender(data.gender)
                .setUserNumber(data.userNumber.toString())
                .openSubmittingForm();
        submittingForm.checkParamSubmittingForm("Student Name", data.firstName + " " + data.lastName)
                .checkParamSubmittingForm("Gender", data.gender)
                .checkParamSubmittingForm("Mobile", data.userNumber.toString());
    }
}
