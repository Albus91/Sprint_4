package uz.myproject.chill.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessOrderWindow {
    private final WebDriver driver;
    private SuccessOrderWindow successOrderWindow;

    public SuccessOrderWindow(WebDriver driver) {
        this.driver = driver;
    }

    private final By successMessage = By.xpath("//div[@class='Order_ModalHeader__3FDaJ'][contains(text(),'Заказ оформлен')]");

    public void isOrderSuccessful() {
        try {
            boolean success = driver.findElement(successMessage).isDisplayed();
            if (success) {
                System.out.println("Заказ успешно оформлен!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
