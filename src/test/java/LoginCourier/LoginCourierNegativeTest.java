package LoginCourier;

import TestsData.Courier.Courier;
import TestsData.Courier.CourierClient;
import TestsData.Courier.CourierCredentials;
import TestsData.Courier.CourierGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static Constants.Constants.NON_EXIST_MASSAGE;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.junit.Assert.assertEquals;

public class LoginCourierNegativeTest {
    private Courier courier;
    private CourierClient courierClient;

    //до теста присваиваем курьеру данные невалидные данные
    @Before
    public void setUp() {
        courier = CourierGenerator.getNonExistCourier();
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Авторизация курьера с невалидными данными")
    @Description("Проверка условия задания: система вернёт ошибку, если неправильно указать логин или пароль; если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")
    public void courierCantLoginTest () {
        //логинимся курьером
        ValidatableResponse loginResponse = courierClient.login(CourierCredentials.from(courier));
        //вводим переменную статус кода и присваем ей значение статус кода из респонса
        int authStatusCode = loginResponse.extract().statusCode();
        //проверяем, что регистрация не прошла
        assertEquals("Неуспешная авторизация", SC_NOT_FOUND, authStatusCode);
        String anotherMessage = loginResponse.extract().path("message");
        assertEquals("Тело ответа совпадает с ожидаемым",anotherMessage, NON_EXIST_MASSAGE);



    }

}
