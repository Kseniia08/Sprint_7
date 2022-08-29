package CreateCourier;

import TestsData.Courier.Courier;
import TestsData.Courier.CourierClient;
import TestsData.Courier.CourierGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.Assert.assertEquals;

public class CreateCourierWithoutLoginTest {
    private Courier courier;
    private CourierClient courierClient;

    //до теста присваиваем курьеру данные  курьера без пароля
    @Before
    public void setUp() {
        courier = CourierGenerator.getCourierWithoutLogin();
        courierClient = new CourierClient();
    }

    @Test
    @DisplayName("Создание курьера без логина")
    @Description("Проверка условия задания: чтобы создать курьера, нужно передать в ручку все обязательные поля; если одного из полей нет, запрос возвращает ошибку;")
    public void createCourierWithoutLoginTest() {
        //создание и проверка неуспешного создания курьера
        ValidatableResponse response = courierClient.create(courier);
        int statusCode = response.extract().statusCode();
        assertEquals("Статус код респонса соответсвут статусу 400", SC_BAD_REQUEST, statusCode);
    }
}
