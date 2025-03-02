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
import uz.myproject.chill.pageobjects.HomePage;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class FAQTestChrome {
    private WebDriver driver;
    private HomePage homePage;

    private final By question;
    private final By answer;
    private final String expectedAnswer;

    public FAQTestChrome(By question, By answer, String expectedAnswer) {
        this.question = question;
        this.answer = answer;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {HomePage.question1, HomePage.answer1, HomePage.expectedAnswer1},
                {HomePage.question2, HomePage.answer2, HomePage.expectedAnswer2},
                {HomePage.question3, HomePage.answer3, HomePage.expectedAnswer3},
                {HomePage.question4, HomePage.answer4, HomePage.expectedAnswer4},
                {HomePage.question5, HomePage.answer5, HomePage.expectedAnswer5},
                {HomePage.question6, HomePage.answer6, HomePage.expectedAnswer6},
                {HomePage.question7, HomePage.answer7, HomePage.expectedAnswer7},
                {HomePage.question8, HomePage.answer8, HomePage.expectedAnswer8}
        };
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        homePage = new HomePage(driver);
    }

    @Test
    public void testFaq() {
        homePage.clickQuestion(question);
        boolean isDisplayed = homePage.isAnswerDisplayed(answer);
        Assert.assertTrue("Ответ не открылся!", isDisplayed);
        String actualAnswer = homePage.getAnswerText(answer);
        Assert.assertEquals("Текст ответа не совпадает!", expectedAnswer, actualAnswer);
    }

    @After
    public void exit() {
        driver.quit();
    }
}