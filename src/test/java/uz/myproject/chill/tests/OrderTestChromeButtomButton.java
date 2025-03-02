package uz.myproject.chill.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import uz.myproject.chill.pageobjects.ConfirmationWindow;
import uz.myproject.chill.pageobjects.HomePage;
import uz.myproject.chill.pageobjects.OrderPage;
import uz.myproject.chill.pageobjects.SuccessOrderWindow;

import java.util.concurrent.TimeUnit;

public class OrderTestChromeButtomButton {
    private WebDriver driver;
    private HomePage homePage;
    private OrderPage orderPage;
    private ConfirmationWindow confirmationWindow;
    private SuccessOrderWindow successOrderWindow;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru/");

        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);
        confirmationWindow = new ConfirmationWindow(driver);
        successOrderWindow = new SuccessOrderWindow(driver);
    }

    @After
    public void exit() {
        driver.quit();
    }

    @Test
    public void testOrderForm() {
        String firstName = "Иван";
        String lastName = "Иванов";
        String address = "Москва, ул. Ленина, 5";
        String metroStation = "Комсомольская";
        String phone = "998972882232";
        String comment = "Хорошего настроения!";

        homePage.clickOrderBottomButton();

        // Заполнение первой части формы заказа
        orderPage.enterFirstName(firstName);
        orderPage.enterLastName(lastName);
        orderPage.enterAddress(address);
        orderPage.enterMetroStation(metroStation);
        orderPage.enterPhoneNumber(phone);

        orderPage.clickNextButton();

        // Заполнение второй части формы заказа
        orderPage.clickDeliveryTimeField();
        orderPage.clickDate();
        orderPage.clickRentTime();
        orderPage.clickRentTimeOption1();
        orderPage.clickBlackColorCheckbox();
        orderPage.enterComment(comment);

        orderPage.clickOrderButton();

        confirmationWindow.clickYesButton();

        successOrderWindow.isOrderSuccessful();
    }
}