package LoginCourier;

import TestsData.Courier;
import TestsData.CourierClient;
import TestsData.CourierCredentials;
import TestsData.CourierGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static Constants.Constants.NEGATIVE_AUTH_MESSAGE;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.Assert.assertEquals;

public class LoginCourierWithoutLoginTest {
    private Courier courier;
    private CourierClient courierClient;

    //до теста присваиваем курьеру данные без пароля
    @Before
    public void setUp() {
        courier = CourierGenerator.getCourierWithoutLogin();
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Авторизация курьера без логина")
    @Description("Проверка условия задания: для авторизации нужно передать все обязательные поля; если какого-то поля нет, запрос возвращает ошибку")
    public void loginCourierWithoutLoginTest() {
        //логинимся с курьером без пароля
        ValidatableResponse authResponse = courierClient.login(CourierCredentials.from(courier));
        //вводим переменную статус кода и присваем ей значение статус кода из респонса
        int authStatusCode = authResponse.extract().statusCode();
        assertEquals("Код статусса соответсвует 400", SC_BAD_REQUEST, authStatusCode);
        String anotherMessage = authResponse.extract().path("message");
        assertEquals("Тело ответа совпадает с ожидаемым",anotherMessage, NEGATIVE_AUTH_MESSAGE);
    }

}
