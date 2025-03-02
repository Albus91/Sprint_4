package uz.myproject.chill.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    // Локатор верхней кнопки для заказа
    public By topOrderButton = By.className("Button_Button__ra12g");

    // Локатор нижней кнопки для заказа
    public By bottomOrderButton = By.className("Button_Middle__1CSJM");

    // Локатор кнопки закрытия баннера кукис
    private By cookieButton = By.id("rcc-confirm-button");

    // Локаторы вопросов
    public static By question1 = By.id("accordion__heading-0");
    public static By question2 = By.id("accordion__heading-1");
    public static By question3 = By.id("accordion__heading-2");
    public static By question4 = By.id("accordion__heading-3");
    public static By question5 = By.id("accordion__heading-4");
    public static By question6 = By.id("accordion__heading-5");
    public static By question7 = By.id("accordion__heading-6");
    public static By question8 = By.id("accordion__heading-7");

    // Локаторы ответов
    public static By answer1 = By.id("accordion__panel-0");
    public static By answer2 = By.id("accordion__panel-1");
    public static By answer3 = By.id("accordion__panel-2");
    public static By answer4 = By.id("accordion__panel-3");
    public static By answer5 = By.id("accordion__panel-4");
    public static By answer6 = By.id("accordion__panel-5");
    public static By answer7 = By.id("accordion__panel-6");
    public static By answer8 = By.id("accordion__panel-7");

    // Тексты ответов
    public static String expectedAnswer1 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static String expectedAnswer2 = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static String expectedAnswer3 = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static String expectedAnswer4 = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static String expectedAnswer5 = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static String expectedAnswer6 = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static String expectedAnswer7 = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public static String expectedAnswer8 = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    // Клик по вопросу
    public void clickQuestion(By questionLocator) {
        WebElement element = new WebDriverWait(driver, 2)
                .until(ExpectedConditions.elementToBeClickable(questionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    // Ожидание открытия ответа
    public boolean isAnswerDisplayed(By answerLocator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
            return driver.findElement(answerLocator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Получение текста ответа
    public String getAnswerText(By answerLocator) {
        return driver.findElement(answerLocator).getText();
    }

    //Закрыть баннер кукис
    public void closeCookieBanner() {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(cookieButton));
            button.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieButton));
    }

    // Клик на заказать сверху
    public void clickOrderTopButton() {
        closeCookieBanner();
        wait.until(ExpectedConditions.elementToBeClickable(topOrderButton)).click();
    }

    // Клик на заказать снизу
    public void clickOrderBottomButton() {
        wait.until(ExpectedConditions.elementToBeClickable(bottomOrderButton)).click();
    }
}