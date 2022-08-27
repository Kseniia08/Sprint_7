package TestsData;

import static Constants.Constants.*;

public class OrderGenerator {
    public static Order getDefault(String[] color) {
        return new Order(FIRST_NAME_DATA,
                LAST_NAME_DATA,
                ADDRESS_DATA,
                METRO_STATION_DATA,
                PHONE_DATA,
                RENT_TIME_DATA,
                DELIVERY_DATE_DATA,
                COMMENT_DATA,
                color);

    }

}
