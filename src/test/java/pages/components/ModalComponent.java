package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ModalComponent {

    private final SelenideElement modal = $(".modal-content");
    private final SelenideElement closeButton = $("#closeLargeModal");

    @Step("Проверить, что модальное окно видимо")
    public ModalComponent verifyModalVisible() {
        modal.shouldBe(visible);
        return this;
    }

    @Step("Проверить, что модальное окно НЕ видимо")
    public ModalComponent verifyModalNotVisible() {
        modal.shouldNotBe(visible);
        return this;
    }

    @Step("Закрыть модальное окно")
    public ModalComponent closeModal() {
        closeButton.click();
        return this;
    }
}