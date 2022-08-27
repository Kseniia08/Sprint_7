package OrderList;

import TestsData.Order.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetOrderListTest {
    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient  = new OrderClient();
    }

    @Test
    @DisplayName("Получение заказа")
    @Description("Проверка условия задания: в тело ответа возвращается список заказов.")
    public void getOrdersTest() {
        //запрашиваем список всех ордеров и проверяем, что он не пустой и не равен нулю
        ValidatableResponse response = orderClient.allOrders();
        //вводим переменную статус кода и присваем ей значение статус кода из респонса
        int statusCode = response.extract().statusCode();
        assertEquals("Список заказов получен. Статус 200", SC_OK, statusCode);
        ArrayList<String> orderListBody = response.extract().path("orders");
        boolean isNotEmpty = orderListBody !=null && !orderListBody.isEmpty();
        assertTrue("Список заказов не пустой", isNotEmpty);
    }

}
