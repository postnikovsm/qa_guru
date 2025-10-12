package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SubmittingFormPage {
    private final ElementsCollection paramTable = $(".table-responsive").$$("tr");
    private final SelenideElement modalForm = $("#example-modal-sizes-title-lg");

    public SubmittingFormPage checkParamSubmittingForm(String labels, String values) {
        paramTable.findBy(text(labels)).shouldHave(text(values));
        return new SubmittingFormPage();
    }

    public SubmittingFormPage checkModalIsNotOpen() {
        modalForm.shouldNotBe(visible);
        return this;
    }
}
