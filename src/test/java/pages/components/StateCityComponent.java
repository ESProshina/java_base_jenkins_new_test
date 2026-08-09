package pages.components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class StateCityComponent {

    private final SelenideElement stateInput = $("#state");
    private final SelenideElement cityInput = $("#city");

    public StateCityComponent selectStateAndCity(String state, String city) {
        selectOption(stateInput, state);
        selectOption(cityInput, city);
        return this;
    }
    private void selectOption(SelenideElement input, String value) {
        input.click();
        SelenideElement option = $$(".css-26l3qy-menu div[id*='react-select'], " +
                ".css-1n6sfyn-menu div[id*='react-select'], " +
                "div[class*='menu'] div[class*='option']")
                .findBy(text(value))
                .shouldBe(visible);
        option.click();
    }
}