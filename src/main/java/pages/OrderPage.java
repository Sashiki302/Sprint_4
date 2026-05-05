package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private final WebDriver driver;

    // локаторы первой страницы
    private final By firstName = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastName = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metro = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    private final By confrimButton = By.xpath("//button[contains(text(),'Да')]");

    // локаторы второй страницы
    private final By datePrivoz = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalSrok = By.className("Dropdown-control");
    private final By commentKurier = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]/button[text()='Заказать']");
    private final By confirmButton = By.xpath(".//button[text()='Да']");
    private final By successModal = By.xpath(".//div[contains(@class, 'Order_ModalHeader') and text()='Заказ оформлен']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // заполняем первую страницу формы
    public void useFirstFrom(String name, String sName, String addr, String station, String tel) {
        driver.findElement(firstName).sendKeys(name);
        driver.findElement(lastName).sendKeys(sName);
        driver.findElement(address).sendKeys(addr);
        driver.findElement(metro).click();
        driver.findElement(metro).sendKeys(station, Keys.DOWN, Keys.ENTER);
        driver.findElement(phone).sendKeys(tel);
        driver.findElement(nextButton).click();
    }

    // переходим к следующему заказу
    public void clickNextButton() {
        driver.findElement(By.className("Button_Button__ra12g")).click();
    }

    // заполняем вторую страницу формы
    public void useSecondForm(String date, String period, String color, String comment) {
        driver.findElement(datePrivoz).sendKeys(date, Keys.ENTER);
        driver.findElement(rentalSrok).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-option' and text()='" + period + "']")).click();
        driver.findElement(By.id(color)).click();
        driver.findElement(commentKurier).sendKeys(comment);
        driver.findElement(orderButton).click();
        driver.findElement(confirmButton).click();
    }
    // подтверждаем заказ
    public void confirmClickOrder(){
driver.findElement(confrimButton).click();
    }
    // проверяем, что появилось окно с сообщением об успешном создании заказа
    public boolean isSuccessDisplayed() {
        return driver.findElements(successModal).size() > 0;
    }
}