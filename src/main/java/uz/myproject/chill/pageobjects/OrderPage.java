package uz.myproject.chill.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы для полей
    private By firstNameField = By.xpath("//input[@placeholder='* Имя']");
    private By lastNameField = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By metroStationField = By.className("select-search__input");
    private By firstMetroOption = By.xpath("//button[contains(@class, 'select-search__option')]");
    private By phoneField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']");
    private By deliveryTimeField = By.className("react-datepicker__input-container");
    private By Date = By.xpath("//div[@aria-label='Choose воскресенье, 2-е марта 2025 г.']");
    private By rentTime = By.className ("Dropdown-placeholder");
    private By rentTimeOption1 = By.xpath("//div[contains(@class, 'Dropdown-option') and text()='сутки']");
    private By blackColorCheckbox = By.id("black");
    private By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private By orderButton = By.cssSelector("button.Button_Button__ra12g.Button_Middle__1CSJM:not(.Button_Inverted__3IF-i)");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }
    // Методы для ввода данных
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void enterMetroStation(String station) {
        WebElement metroField = wait.until(ExpectedConditions.elementToBeClickable(metroStationField));
        metroField.click();
        metroField.sendKeys(station);
        WebElement firstOption = wait.until(ExpectedConditions.elementToBeClickable(firstMetroOption));
        firstOption.click();
    }

    public void enterComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void enterPhoneNumber(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }
    // Методы для кликов
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void clickDeliveryTimeField() {
        driver.findElement(deliveryTimeField).click();
    }

    public void clickDate() {
        driver.findElement(Date).click();
    }

    public void clickRentTime() {
        driver.findElement(rentTime).click();
    }

    public void clickRentTimeOption1() {
        driver.findElement(rentTimeOption1).click();
    }

    public void clickBlackColorCheckbox() {
        driver.findElement(blackColorCheckbox).click();
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
}