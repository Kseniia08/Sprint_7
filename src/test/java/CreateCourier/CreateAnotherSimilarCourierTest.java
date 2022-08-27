package CreateCourier;

import TestsData.Courier;
import TestsData.CourierClient;
import TestsData.CourierCredentials;
import TestsData.CourierGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static Constants.Constants.DOUBLE_COURIER_MESSAGE;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CreateAnotherSimilarCourierTest {
    private Courier courier;
    private CourierClient courierClient;
    private int courierId;

    //до теста присваиваем курьеру данные дефолтного курьера
    @Before
    public void setUp() {
        courier = CourierGenerator.getDefault();
        courierClient = new CourierClient();
    }

    //после теста выполняем удаление курьера
    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

    @Test
    @DisplayName("Создание дубля курьера")
    @Description("Проверка условия задания: нельзя создать двух одинаковых курьеров; запрос возвращает правильный код ответа; если создать пользователя с логином, который уже есть, возвращается ошибка.")
    public void createAnotherSimilarCourierTest() {
        //создаем курьера для теста
        ValidatableResponse response = courierClient.create(courier);
        //вводим переменную статус кода и присваем ей значение статус кода из респонса
        int statusCode = response.extract().statusCode();
        assertEquals("Статус код респонса соответсвут статусу 201", SC_CREATED, statusCode);
        //вводим переменную isCreated и присваем ей значение тела ответа
        boolean isCreated = response.extract().path("ok");
        assertTrue("Курьер создан", isCreated);

        //повторно создаем курьера для теста
        ValidatableResponse doubleResponse = courierClient.create(courier);
        //вводим переменную anotherStatusCode и присваем ей значение статус кода из респонса
        int anotherStatusCode = doubleResponse.extract().statusCode();
        assertEquals("Статус код респонса соответсвует статусу 409",SC_CONFLICT, anotherStatusCode);
        String anotherMessage = doubleResponse.extract().path("message");
        assertEquals("Тело ответа совпадает с ожидаемым",anotherMessage, DOUBLE_COURIER_MESSAGE);

        //чтобы удалить курьера логинимся под созданным курьером, вытаскиваем его id и сравниваем его с null
        ValidatableResponse anotherResponse = courierClient.login(CourierCredentials.from(courier));
        int authStatusCode = anotherResponse.extract().statusCode();
        assertEquals("Курьер успешно залогинился", SC_OK, authStatusCode);
        courierId = anotherResponse.extract().path("id");
        assertNotNull("Id не пустой", courierId);
    }

}
