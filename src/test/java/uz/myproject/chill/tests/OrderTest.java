package uz.myproject.chill.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import uz.myproject.chill.pageobjects.ConfirmationWindow;
import uz.myproject.chill.pageobjects.HomePage;
import uz.myproject.chill.pageobjects.OrderPage;
import uz.myproject.chill.pageobjects.SuccessOrderWindow;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private HomePage homePage;
    private OrderPage orderPage;
    private ConfirmationWindow confirmationWindow;
    private SuccessOrderWindow successOrderWindow;

    private final String browser;
    private final String button;

    public OrderTest (String browser, String button) {
        this.browser = browser;
        this.button = button;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"chrome", "topOrderButton"},
                {"chrome", "bottomOrderButton"},
                {"firefox", "topOrderButton"},
                {"firefox", "bottomOrderButton"}
        };
    }

    @Before
    public void setUp() {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            System.setProperty("webdriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);
        confirmationWindow = new ConfirmationWindow(driver);
        successOrderWindow = new SuccessOrderWindow(driver);
    }



    @Test
    public void testOrders() {
        String firstName = "Иван";
        String lastName = "Иванов";
        String address = "Москва, ул. Ленина, 5";
        String metroStation = "Комсомольская";
        String phone = "998972882232";
        String comment = "Хорошего настроения!";

        if (button.equals("topOrderButton")) {
            homePage.clickOrderTopButton();
        } else if (button.equals("bottomOrderButton")) {
                homePage.clickOrderBottomButton();
        }

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
    @After
    public void exit() {
        driver.quit();
    }
}