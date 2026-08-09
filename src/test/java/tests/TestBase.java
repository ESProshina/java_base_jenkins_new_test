package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.webdriver;

public class TestBase {

    @BeforeAll
    static void setupSelenideEnv() {
        String baseUrl = System.getProperty("base.url", "https://demoqa.com");
        Configuration.baseUrl = baseUrl;

        String remoteUrl = System.getProperty("remote.url",
                "https://user1:1234@selenoid.autotests.cloud/wd/hub");
        Configuration.remote = remoteUrl;

        String browser = System.getProperty("browser", "chrome");
        Configuration.browser = browser;

        String browserVersion = System.getProperty("browser.version", "");
        // Если передана конкретная версия (не "latest"), применяем её
        if (!browserVersion.isEmpty() && !browserVersion.equals("latest")) {
            Configuration.browserVersion = browserVersion;
        }

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        Configuration.headless = headless;

        String browserSize = System.getProperty("browser.size", "1920x1080");
        Configuration.browserSize = browserSize;

        long timeout = Long.parseLong(System.getProperty("timeout", "60000"));
        Configuration.timeout = timeout;
        long pageLoadTimeout = Long.parseLong(System.getProperty("page.load.timeout", "120000"));
        Configuration.pageLoadTimeout = pageLoadTimeout;

        System.setProperty("allure.results.directory", "build/allure-results");
        Configuration.screenshots = true;
        Configuration.savePageSource = true;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    protected void addScreenshotToAllure() {
        try {
            byte[] screenshot = ((TakesScreenshot) webdriver().driver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("📸 Скриншот страницы", "image/png", new ByteArrayInputStream(screenshot), "png");
        } catch (Exception e) {
            // Если скриншот не удалось сделать — просто игнорируем, чтобы не ломать тест
        }
    }

    @AfterEach
    void tearDown() {
        addScreenshotToAllure();
        closeWebDriver();
    }
}