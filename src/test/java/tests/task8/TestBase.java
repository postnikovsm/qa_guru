package tests.task8;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;


import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class TestBase {
    private static final String REPOSITORY = "postnikovsm/qa_guru/issues/6";

    @BeforeAll
    public static void setupEnvironment() {
        Configuration.pageLoadStrategy = "eager";
        step("открываем нужный репозиторий GitHub",
                () -> open("https://github.com/" + REPOSITORY));

    }

    @AfterEach
    public void afterEach() {
        SelenideLogger.addListener("allureListener", new AllureSelenide());
        new Helpers().takeScreenshot("Последний шаг");
    }
 }
