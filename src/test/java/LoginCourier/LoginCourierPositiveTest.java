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

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginCourierPositiveTest {
    private Courier courier;
    private CourierClient courierClient;
    private int courierId;

    //до теста присваиваем курьеру данные
    @Before
    public void setUp() {
        courier = CourierGenerator.getRightCourier();
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Авторизация курьера")
    @Description("Проверка условия задания: курьер может авторизоваться; для авторизации нужно передать все обязательные поля; успешный запрос возвращает id")
    public void loginCourierPositiveTest() {
        //логинимся курьером
        ValidatableResponse authResponse = courierClient.login(CourierCredentials.from(courier));
        //вводим переменную статус кода и присваем ей значение статус кода из респонса
        int authStatusCode = authResponse.extract().statusCode();
        //проверяем, что курьер залогинился
        assertEquals("Авторизация прошла успешно", SC_OK, authStatusCode);
        //вводим переменную isCreated и присваем ей значение тела ответа
        courierId = authResponse.extract().path("id");
        //проверяем, что id что не пустой
        assertNotNull("Id не пустой", courierId);
    }

}
