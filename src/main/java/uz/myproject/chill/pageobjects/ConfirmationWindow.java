package uz.myproject.chill.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationWindow {
    private WebDriver driver;

    public ConfirmationWindow(WebDriver driver) {
        this.driver = driver;
    }

    public By yesButton = By.xpath("//button[contains(text(), 'Да')]");

    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }
}
