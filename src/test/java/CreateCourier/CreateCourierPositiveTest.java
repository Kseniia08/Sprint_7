package CreateCourier;

import TestsData.Courier.Courier;
import TestsData.Courier.CourierClient;
import TestsData.Courier.CourierCredentials;
import TestsData.Courier.CourierGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.*;

public class CreateCourierPositiveTest {
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
    @DisplayName("Создание курьера")
    @Description("Проверка условия задания: курьера можно создать; успешный запрос возвращает ok: true, ")
    public void createCourierPositiveTest() {
        //создаем курьера для теста
        ValidatableResponse response = courierClient.create(courier);
        //вводим переменную статус кода и присваем ей значение статус кода из респонса
        int statusCode = response.extract().statusCode();
        assertEquals("Статус код респонса соответсвут статусу 201", SC_CREATED, statusCode);
        //вводим переменную isCreated и присваем ей значение тела ответа
        boolean isCreated = response.extract().path("ok");
        assertTrue("Курьер создан", isCreated);

        //чтобы удалить курьера логинимся под созданным курьером, вытаскиваем его id и сравниваем его с null
        ValidatableResponse anotherResponse = courierClient.login(CourierCredentials.from(courier));
        int authStatusCode = anotherResponse.extract().statusCode();
        assertEquals("Курьер успешно залогинился", SC_OK, authStatusCode);
        courierId = anotherResponse.extract().path("id");
        assertNotNull("Id не пустой", courierId);
    }

}
