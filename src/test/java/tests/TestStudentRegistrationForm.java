package tests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static testdata.StudentRegistrationTestData.*;

@Epic("UI Тестирование")
@Feature("Регистрация студента")
@Owner("ESProshina")
public class TestStudentRegistrationForm extends TestBase {

    private final TextBoxPage textBoxPage = new TextBoxPage();

    private void openPageWithRetry() {
        int maxAttempts = 3;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                textBoxPage.openPage();
                return;
            } catch (Exception e) {
                if (attempt == maxAttempts) {
                    throw e;
                }
                sleep(2000);
            }
        }
    }

    @Test
    @DisplayName("Проверка параметра environment")
    @Tag("properties")
    void propertyEnvironmentTest() {
        System.getProperty("environment", "demoqa");
    }

    @Test
    @DisplayName("Проверка параметра name")
    @Tag("properties")
    void propertyNameTest() {
        System.getProperty("name", "DefaultUser");
    }

    @Test
    @DisplayName("Проверка параметра browser")
    @Tag("properties")
    void propertyBrowserTest() {
        System.getProperty("browser", "chrome");
    }

    @Test
    @DisplayName("Проверка параметра base.url")
    @Tag("properties")
    void propertyBaseUrlTest() {
        System.getProperty("base.url", "https://demoqa.com");
    }

    @Test
    @DisplayName("Проверка параметра remote.url")
    @Tag("properties")
    void propertyRemoteUrlTest() {
        System.getProperty("remote.url", "https://selenoid.autotests.cloud/wd/hub");
    }

    @Test
    @DisplayName("Проверка параметра headless")
    @Tag("properties")
    void propertyHeadlessTest() {
        Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    @Test
    @DisplayName("Проверка всех параметров")
    @Tag("properties")
    void propertyAllTest() {
        System.getProperty("environment", "demoqa");
        System.getProperty("name", "DefaultUser");
        System.getProperty("browser", "chrome");
        System.getProperty("browser.version", "latest");
        System.getProperty("headless", "false");
        System.getProperty("base.url", "https://demoqa.com");
        System.getProperty("remote.url", "https://selenoid.autotests.cloud/wd/hub");
        System.getProperty("browser.size", "1920x1080");
        System.getProperty("timeout", "60000");
    }

    @Test
    @Story("Успешное заполнение всей формы")
    @DisplayName("Успешное заполнение всей формы регистрации студента")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @Tag("positive")
    void successfulFillFormTest() {
        openPageWithRetry();

        textBoxPage
                .fillForm(FIRST_NAME, LAST_NAME, EMAIL, GENDER, MOBILE_NUMBER,
                        DAY, MONTH, YEAR, SUBJECT, HOBBY, PICTURE_NAME,
                        ADDRESS, STATE, CITY)
                .submitForm();

        textBoxPage.getModal().verifyModalVisible();
        textBoxPage.getTable()
                .verifyStudentName(FIRST_NAME, LAST_NAME)
                .verifyStudentEmail(EMAIL)
                .verifyGender(GENDER)
                .verifyMobile(MOBILE_NUMBER)
                .verifyDateOfBirth(DAY, MONTH, YEAR)
                .verifySubjects(SUBJECT)
                .verifyHobbies(HOBBY)
                .verifyPicture(PICTURE_NAME)
                .verifyAddress(ADDRESS)
                .verifyStateAndCity(STATE, CITY);

        textBoxPage.getModal().closeModal();
        sleep(1000);
    }

    @Test
    @Story("Успешное заполнение обязательных полей")
    @DisplayName("Успешное заполнение только обязательных полей")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @Tag("positive")
    void successfulMandatoryFieldsTest() {
        openPageWithRetry();

        textBoxPage
                .fillMandatoryFields(FIRST_NAME, LAST_NAME, EMAIL, GENDER, MOBILE_NUMBER)
                .submitForm();

        textBoxPage.getModal().verifyModalVisible();
        textBoxPage.getTable()
                .verifyStudentName(FIRST_NAME, LAST_NAME)
                .verifyStudentEmail(EMAIL)
                .verifyGender(GENDER)
                .verifyMobile(MOBILE_NUMBER);

        textBoxPage.getModal().closeModal();
        sleep(1000);
    }

    @Test
    @Story("Негативные проверки")
    @DisplayName("Негативный тест: пустое поле First Name")
    @Severity(SeverityLevel.NORMAL)
    @Tag("negative")
    void negativeTestWhenFirstNameIsEmpty() {
        openPageWithRetry();

        textBoxPage
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setGender(GENDER)
                .setMobile(MOBILE_NUMBER)
                .scrollToSubmit()
                .submitForm();

        textBoxPage.getModal().verifyModalNotVisible();

        SelenideElement firstNameField = $("#firstName");
        firstNameField.shouldHave(attribute("required"));

        boolean isValid = executeJavaScript("return arguments[0].checkValidity();", firstNameField);
        assert !isValid : "Field should be invalid when empty";
        sleep(500);
    }

    @Test
    @Story("Негативные проверки")
    @DisplayName("Негативный тест: пустое поле Last Name")
    @Severity(SeverityLevel.NORMAL)
    @Tag("negative")
    void negativeTestWhenLastNameIsEmpty() {
        openPageWithRetry();

        textBoxPage
                .setFirstName(FIRST_NAME)
                .setEmail(EMAIL)
                .setGender(GENDER)
                .setMobile(MOBILE_NUMBER)
                .scrollToSubmit()
                .submitForm();

        textBoxPage.getModal().verifyModalNotVisible();

        SelenideElement lastNameField = $("#lastName");
        lastNameField.shouldHave(attribute("required"));

        boolean isValid = executeJavaScript("return arguments[0].checkValidity();", lastNameField);
        assert !isValid : "Field should be invalid when empty";
        sleep(500);
    }

    @Test
    @Story("Негативные проверки")
    @DisplayName("Негативный тест: пустое поле Mobile")
    @Severity(SeverityLevel.NORMAL)
    @Tag("negative")
    void negativeTestWhenMobileIsEmpty() {
        try {
            openPageWithRetry();
        } catch (Exception e) {
            throw new AssertionError("Не удалось открыть страницу для выполнения теста", e);
        }

        if (webdriver().driver() == null) {
            throw new AssertionError("WebDriver не инициализирован");
        }

        textBoxPage
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setGender(GENDER)
                .scrollToSubmit()
                .submitForm();

        textBoxPage.getModal().verifyModalNotVisible();

        SelenideElement mobileField = $("#userNumber");
        mobileField.shouldHave(attribute("required"));

        boolean isValid = executeJavaScript("return arguments[0].checkValidity();", mobileField);
        assert !isValid : "Field should be invalid when empty";
        sleep(500);
    }

    @Test
    @Story("Негативные проверки")
    @DisplayName("Негативный тест: не выбран пол (Gender)")
    @Severity(SeverityLevel.NORMAL)
    @Tag("negative")
    void negativeTestWhenGenderIsEmpty() {
        openPageWithRetry();

        textBoxPage
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setMobile(MOBILE_NUMBER)
                .scrollToSubmit()
                .submitForm();

        textBoxPage.getModal().verifyModalNotVisible();

        $("#gender-radio-1").shouldBe(visible);
        $("#gender-radio-1").shouldNotBe(checked);
        $("#gender-radio-2").shouldNotBe(checked);
        $("#gender-radio-3").shouldNotBe(checked);

        boolean isFormValid = executeJavaScript("return document.querySelector('form').checkValidity();");
        assert !isFormValid : "Form should be invalid when gender not selected";
        sleep(500);
    }
}