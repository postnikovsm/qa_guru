package tests.task8;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Helpers {
    @Attachment(value = "{name}", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot(String name) {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
