package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //открываем старницу
    public void openPage() {
        driver.get(URL);
    }

    // принимаем куки
    public void acceptCookies() {
        By cookieButton = By.id("rcc-confirm-button");
        if (driver.findElements(cookieButton).size() > 0) {
            driver.findElement(cookieButton).click();
        }
    }

    // кликаем по вопросу по его индексу (0-7)
    public void clickQuestion(int index) {
        By questionLocator = By.id("accordion__heading-" + index);
        WebElement element = driver.findElement(questionLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    // получаем текст ответа по его индексу (0-7)
    public String getAnswerText(int index) {
        By answerLocator = By.id("accordion__panel-" + index);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return element.getText();
    }
    // нажимаем на кнопку заказа в верхней части страницы
    public void clickUpperOrderButton() {
        WebElement button = driver.findElement(By.xpath("//button[@class='Button_Button__ra12g']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }

    // нажимаем на кнопку заказа в нижней части страницы
    public void clickLowerOrderButton() {
        WebElement button = driver.findElement(By.xpath(".//button[contains(@class, 'Button_Middle')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }
}