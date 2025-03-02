package uz.myproject.chill.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessOrderWindow {
    private WebDriver driver;
    private SuccessOrderWindow successOrderWindow;

    public SuccessOrderWindow(WebDriver driver) {
        this.driver = driver;
    }

    private By successMessage = By.xpath("//div[@class='Order_ModalHeader__3FDaJ'][contains(text(),'Заказ оформлен')]");

    public boolean isOrderSuccessful() {
        try {
            boolean success = driver.findElement(successMessage).isDisplayed();
            if (success) {
                System.out.println("Заказ успешно оформлен!");
            }
            return success;
        } catch (NoSuchElementException e) {
            System.out.println("Заказ не удалось оформить");
            return false;
        }
    }
}
