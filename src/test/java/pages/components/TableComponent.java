package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TableComponent {

    private final SelenideElement table = $(".table-responsive");

    @Step("Проверить имя студента: {firstName} {lastName}")
    public TableComponent verifyStudentName(String firstName, String lastName) {
        table.$(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
        return this;
    }

    @Step("Проверить email студента: {email}")
    public TableComponent verifyStudentEmail(String email) {
        table.$(byText("Student Email")).parent().shouldHave(text(email));
        return this;
    }

    @Step("Проверить пол: {gender}")
    public TableComponent verifyGender(String gender) {
        table.$(byText("Gender")).parent().shouldHave(text(gender));
        return this;
    }

    @Step("Проверить номер телефона: {mobile}")
    public TableComponent verifyMobile(String mobile) {
        table.$(byText("Mobile")).parent().shouldHave(text(mobile));
        return this;
    }

    @Step("Проверить дату рождения: {day} {month} {year}")
    public TableComponent verifyDateOfBirth(String day, String month, String year) {
        table.$(byText("Date of Birth")).parent().shouldHave(text(day + " " + month + "," + year));
        return this;
    }

    @Step("Проверить предметы: {subjects}")
    public TableComponent verifySubjects(String subjects) {
        table.$(byText("Subjects")).parent().shouldHave(text(subjects));
        return this;
    }

    @Step("Проверить хобби: {hobbies}")
    public TableComponent verifyHobbies(String hobbies) {
        table.$(byText("Hobbies")).parent().shouldHave(text(hobbies));
        return this;
    }

    @Step("Проверить картинку: {picture}")
    public TableComponent verifyPicture(String picture) {
        table.$(byText("Picture")).parent().shouldHave(text(picture));
        return this;
    }

    @Step("Проверить адрес: {address}")
    public TableComponent verifyAddress(String address) {
        table.$(byText("Address")).parent().shouldHave(text(address));
        return this;
    }

    @Step("Проверить штат и город: {state} {city}")
    public TableComponent verifyStateAndCity(String state, String city) {
        table.$(byText("State and City")).parent().shouldHave(text(state + " " + city));
        return this;
    }
}