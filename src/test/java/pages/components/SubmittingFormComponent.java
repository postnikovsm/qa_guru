package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SubmittingFormComponent {
    private final ElementsCollection paramTable = $(".table-responsive").$$("tr");
    private final SelenideElement modalForm = $("#example-modal-sizes-title-lg");

    public SubmittingFormComponent checkParamSubmittingForm(String labels, String values) {
        paramTable.findBy(text(labels)).shouldHave(text(values));
        return new SubmittingFormComponent();
    }

    public SubmittingFormComponent checkModalIsNotOpen() {
        modalForm.shouldNotBe(visible);
        return this;
    }
}
