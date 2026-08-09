package tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TextBoxTestData.*;

@Epic("UI Тестирование")
@Feature("TextBox")
@Owner("ESProshina")
public class TextBoxTests extends TestBase {

    @Test
    @Story("Заполнение формы TextBox")
    @DisplayName("Успешное заполнение формы TextBox")
    @Severity(SeverityLevel.NORMAL)
    @Tag("smoke")
    @Tag("positive")
    void successfulFillFormTest() {
        Selenide.open("/text-box");

        $("#userName").shouldBe(visible).setValue(USER_NAME);
        $("#userEmail").shouldBe(visible).setValue(USER_EMAIL);
        $("#currentAddress").shouldBe(visible).setValue(CURRENT_ADDRESS);
        $("#permanentAddress").shouldBe(visible).setValue(PERMANENT_ADDRESS);

        $("#submit").scrollTo().shouldBe(visible).click();

        $("#output").shouldBe(visible);
        $("#output #name").shouldHave(text(USER_NAME));
        $("#output #email").shouldHave(text(USER_EMAIL));
        $("#output #currentAddress").shouldHave(text(CURRENT_ADDRESS));
        $("#output #permanentAddress").shouldHave(text(PERMANENT_ADDRESS));

        sleep(1000);
    }
}