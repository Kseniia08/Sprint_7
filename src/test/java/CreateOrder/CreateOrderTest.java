package CreateOrder;

import TestsData.Order.Order;
import TestsData.Order.OrderClient;
import TestsData.Order.OrderGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private Order order;
    private OrderClient orderClient;
    private int orderTrack;
    private final String[] color;

    public CreateOrderTest(String[] color) {
        this.color = color;
    }
    //Параметризация по цвету
    @Parameterized.Parameters
    public static Object[][] getColors() {
        return new Object[][]{
                {new String[]{"GRAY", "BLACK"}},
                {new String[]{"GRAY"}},
                {new String[]{"BLACK"}},
                {new String[]{""}}
        };
    }

    //до теста присваиваем заказу данные
    @Before
    public void setUp() {
        order = OrderGenerator.getDefault(color);
        orderClient  = new OrderClient();
    }

    @Test
    @DisplayName("Создание заказ")
    @Description("Проверка условия задания: можно указать один из цветов — BLACK или GREY; можно указать оба цвета; можно совсем не указывать цвет; тело ответа содержит track. ")
    public void orderCanBeCreatedTest() {
        //создаем ордер
        ValidatableResponse response = orderClient.create(order);
        //вводим переменную статус кода и присваем ей значение статус кода из респонса
        int statusCode = response.extract().statusCode();
        assertEquals("Заказ создан. Статус код 201", SC_CREATED, statusCode);
        //вводим переменную orderTrack и присваем ей значение тела ответа (переменная track)
        int orderTrack = response.extract().path("track");
        //проверяем, что track не пустой
        assertNotNull("Track не пустой", orderTrack);
    }

}
