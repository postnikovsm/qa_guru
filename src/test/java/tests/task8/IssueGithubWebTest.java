package tests.task8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Feature("Проверка issue в репозитории")
public class IssueGithubWebTest extends TestBase {
    AllureSteps allureSteps = new AllureSteps();
    private final SelenideElement issuesTab = $("#issues-tab"),
            issuesTitle = $("[data-testid='issue-pr-title-link']");
    private final String issuesName = "new issue";

    @Test
    @Owner("postnikovsm")
    @DisplayName("Проверка Issue в репозитории github через чистый Selenide")
    @Link(value = "GitHub Issue", url = "https://github.com/postnikovsm/qa_guru/issues/6")
    @Severity(SeverityLevel.CRITICAL)
    void testSelenide() {
        issuesTab.click();
        issuesTitle.shouldHave(text(issuesName));
    }

    @Test
    @Owner("postnikovsm")
    @DisplayName("Проверка Issue в репозитории github через step")
    @Link(value = "GitHub Issue", url = "https://github.com/postnikovsm/qa_guru/issues/6")
    @Severity(SeverityLevel.CRITICAL)
    void lambdaTestAllure() {

        step("кликаем по кнопке Issues ", () ->
                issuesTab.click());
        step("проверяем название Issue", () ->
                issuesTitle.shouldHave(text(issuesName)));
    }

    @Test
    @Owner("postnikovsm")
    @DisplayName("Проверка Issue в репозитории github через аннотацию @Step")
    @Link(value = "GitHub Issue", url = "https://github.com/postnikovsm/qa_guru/issues/6")
    @Severity(SeverityLevel.CRITICAL)
    void annotationTestAllure() {
        allureSteps.clickIssueButton()
                .checkNameIssue(issuesName);
    }
}
