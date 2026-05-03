import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class orderPageTest extends BaseUITest {

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;
    private final boolean useHeaderButton;

    public orderPageTest(String firstName, String lastName, String address, String metroStation,
                         String phone, String date, String rentalPeriod, String color,
                         String comment, boolean useHeaderButton) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
        this.useHeaderButton = useHeaderButton;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {"Тест", "Тестиров", "Москва", "Щукинская", "89991234567", "25.05.2026", "сутки", "black", "Привет!", true},
                {"Тестировщик", "Тестов", "Ростов", "Ростокино", "89007654321", "30.05.2026", "двое суток", "grey", "Пока?", false},
        };
    }

    @Test
   public void checkOrder() {
        // открываем главную страницу
        mainPage.openPage();
        // принимаем куки
        mainPage.acceptCookies();
        // выбираем кнопку заказа в зависимости от параметра (верхнюю или нижнюю)
        if (useHeaderButton) {
            mainPage.clickUpperOrderButton();
        } else {
            mainPage.clickLowerOrderButton();
        }
        // заполняем первую страницу формы
        orderPage.useFirstFrom (firstName, lastName, address, metroStation, phone);
        // переходим по кнопке на фторую форму
        orderPage.clickNextButton();
        //заполняем вторую страницу формы
        orderPage.useSecondForm (date, rentalPeriod, color, comment);
        // Подтверждаем заказ
        orderPage.confirmClickOrder();
        // проверяем, что появилось окно с сообщением об успешном создании заказа
        boolean isOrderProcessed = orderPage.isSuccessDisplayed();
        assertTrue("Окно подтверждения заказа не отобразилось", isOrderProcessed);
    }
}