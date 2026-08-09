package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.ModalComponent;
import pages.components.TableComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {

    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            mobileInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            pictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            submitButton = $("#submit");

    private final ModalComponent modal = new ModalComponent();
    private final TableComponent table = new TableComponent();

    @Step("Открыть страницу регистрации")
    public TextBoxPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    @Step("Заполнить форму регистрации")
    public TextBoxPage fillForm(String firstName, String lastName, String email, String gender,
                                String mobile, String day, String month, String year,
                                String subject, String hobby, String picture,
                                String address, String state, String city) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setGender(gender);
        setMobile(mobile);
        setDateOfBirth(day, month, year);
        setSubject(subject);
        setHobby(hobby);
        uploadPicture(picture);
        setAddress(address);
        setState(state);
        setCity(city);
        return this;
    }

    @Step("Заполнить обязательные поля")
    public TextBoxPage fillMandatoryFields(String firstName, String lastName, String email,
                                           String gender, String mobile) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setGender(gender);
        setMobile(mobile);
        return this;
    }

    @Step("Ввести First Name: {firstName}")
    public TextBoxPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    @Step("Ввести Last Name: {lastName}")
    public TextBoxPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    @Step("Ввести Email: {email}")
    public TextBoxPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Выбрать пол: {gender}")
    public TextBoxPage setGender(String gender) {
        $x("//div[@id='genterWrapper']//label[text()='" + gender + "']").click();
        return this;
    }

    @Step("Ввести номер телефона: {mobile}")
    public TextBoxPage setMobile(String mobile) {
        mobileInput.setValue(mobile);
        return this;
    }

    @Step("Установить дату рождения: {day} {month} {year}")
    public TextBoxPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);

        // ИСПРАВЛЕНО: ищем день по тексту, игнорируя дни вне месяца
        $x("//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and text()='" + day + "']").click();
        return this;
    }

    @Step("Выбрать предмет: {subject}")
    public TextBoxPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    @Step("Выбрать хобби: {hobby}")
    public TextBoxPage setHobby(String hobby) {
        $x("//div[@id='hobbiesWrapper']//label[text()='" + hobby + "']").click();
        return this;
    }

    @Step("Загрузить картинку: {picture}")
    public TextBoxPage uploadPicture(String picture) {
        pictureInput.uploadFromClasspath(picture);
        return this;
    }

    @Step("Ввести адрес: {address}")
    public TextBoxPage setAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    @Step("Выбрать штат: {state}")
    public TextBoxPage setState(String state) {
        $("#state").click();
        $x("//div[@id='stateCity-wrapper']//div[text()='" + state + "']").click();
        return this;
    }

    @Step("Выбрать город: {city}")
    public TextBoxPage setCity(String city) {
        $("#city").click();
        $x("//div[@id='stateCity-wrapper']//div[text()='" + city + "']").click();
        return this;
    }

    @Step("Прокрутить к кнопке Submit")
    public TextBoxPage scrollToSubmit() {
        submitButton.scrollTo();
        return this;
    }

    @Step("Отправить форму")
    public TextBoxPage submitForm() {
        submitButton.click();
        return this;
    }

    @Step("Получить модальное окно")
    public ModalComponent getModal() {
        return modal;
    }

    @Step("Получить таблицу с результатами")
    public TableComponent getTable() {
        return table;
    }
}