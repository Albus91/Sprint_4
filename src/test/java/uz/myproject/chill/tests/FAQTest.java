package uz.myproject.chill.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import uz.myproject.chill.pageobjects.HomePage;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class FAQTest {
    private WebDriver driver;
    private HomePage homePage;

    private final By question;
    private final By answer;
    private final String expectedAnswer;
    private final String browser;

    public FAQTest(By question, By answer, String expectedAnswer, String browser) {
        this.question = question;
        this.answer = answer;
        this.expectedAnswer = expectedAnswer;
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {HomePage.question1, HomePage.answer1, HomePage.expectedAnswer1, "chrome"},
                {HomePage.question2, HomePage.answer2, HomePage.expectedAnswer2, "chrome"},
                {HomePage.question3, HomePage.answer3, HomePage.expectedAnswer3, "chrome"},
                {HomePage.question4, HomePage.answer4, HomePage.expectedAnswer4, "chrome"},
                {HomePage.question5, HomePage.answer5, HomePage.expectedAnswer5, "chrome"},
                {HomePage.question6, HomePage.answer6, HomePage.expectedAnswer6, "chrome"},
                {HomePage.question7, HomePage.answer7, HomePage.expectedAnswer7, "chrome"},
                {HomePage.question8, HomePage.answer8, HomePage.expectedAnswer8, "chrome"},
                {HomePage.question1, HomePage.answer1, HomePage.expectedAnswer1, "firefox"},
                {HomePage.question2, HomePage.answer2, HomePage.expectedAnswer2, "firefox"},
                {HomePage.question3, HomePage.answer3, HomePage.expectedAnswer3, "firefox"},
                {HomePage.question4, HomePage.answer4, HomePage.expectedAnswer4, "firefox"},
                {HomePage.question5, HomePage.answer5, HomePage.expectedAnswer5, "firefox"},
                {HomePage.question6, HomePage.answer6, HomePage.expectedAnswer6, "firefox"},
                {HomePage.question7, HomePage.answer7, HomePage.expectedAnswer7, "firefox"},
                {HomePage.question8, HomePage.answer8, HomePage.expectedAnswer8, "firefox"}
        };
    }

    @Before
    public void setUp() {
        if (Objects.equals(browser, "chrome")) {
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
    }

    @Test
    public void FAQTests() {
        homePage.clickQuestion(question);
        boolean isDisplayed = homePage.isAnswerDisplayed(answer);
        Assert.assertTrue(isDisplayed);
        String actualAnswer = homePage.getAnswerText(answer);
        Assert.assertEquals(expectedAnswer, actualAnswer);
    }

    @After
    public void exit() {
            driver.quit();
        }
}