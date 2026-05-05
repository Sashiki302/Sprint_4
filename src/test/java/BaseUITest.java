import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage;

public class BaseUITest {
    WebDriver driver;
    MainPage mainPage;
    OrderPage orderPage;
    @Before
    public void startBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);

    }
    @After
    public void stopBrowser() {
        driver.quit();
    }
}
