package tests.task8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;


public class AllureSteps {
    private final SelenideElement issuesTab = $("#issues-tab"),
            issuesTitle = $("[data-testid='issue-pr-title-link']");


    @Step(" кликаем по кнопке Issues")
    public AllureSteps clickIssueButton() {
        issuesTab.click();
        return this;
    }

    @Step("проверяем название Issue")
    public AllureSteps checkNameIssue(String name) {
        issuesTitle.shouldHave(text(name));
        return this;
    }
}
