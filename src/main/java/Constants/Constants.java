package Constants;


public class Constants {

    public static final String BASE_URL ="http://qa-scooter.praktikum-services.ru/";
    public static final String COURIER_CREATE_PATH = "/api/v1/courier";
    public static final String COURIER_AUTH_PATH = "/api/v1/courier/login";
    public static final String COURIER_DELETE_PATH = "api/v1/courier/";
    //Константы для авторзации и создании курьера
    //Позитивный сценарий
    public static final String FINAL_NAME_DATA = "Ksen";
    public static final String PASSWORD_DATA = "123321";
    public static final String LOGIN_DATA = "Ksen25";
    public static final String PASSWORD_DATA_FOR_LOG_IN = "123321";
    public static final String LOGIN_DATA_FOR_LOG_IN = "Kara";
    //Негативный сценарий
    public static final String NON_EXISTENT_LOGIN_DATA ="NonexistentUser";
    public static final String NON_EXISTENT_PASSWORD_DATA ="NonexistentPassword";
    //Константы заказа
    public static final String FIRST_NAME_DATA = "Александр";
    public static final String LAST_NAME_DATA = "Печкин";
    public static final String ADDRESS_DATA = "г. Москва, ул. Южнопортовая 9А";
    public static final int METRO_STATION_DATA = 5;
    public static final String PHONE_DATA = "8-894-342-21-44";
    public static final int RENT_TIME_DATA = 3;
    public static final String DELIVERY_DATE_DATA = "2022-08-12";
    public static final String COMMENT_DATA = "Тестовый комментарий";
    public static final String BLACK_COLOR_DATA = "BLACK";
    public static final String GREY_COLOR_DATA = "GREY";
    //Константы для получения списка заказов
    public static final int COURIER_ID_DATA = 1;
    public static final String METRO_STATION_ONE_DATA = "1";
    public static final String METRO_STATION_TWO_DATA = "2";
    public static final int DEFAULT_LIMIT = 30;
    public static final int DEFAULT_PAGE = 0;

    //Константа на тело ответа
    public static final String DOUBLE_COURIER_MESSAGE ="Этот логин уже используется. Попробуйте другой.";
    public static final String NON_EXIST_MASSAGE ="Учетная запись не найдена";
    public static final String NEGATIVE_AUTH_MESSAGE = "Недостаточно данных для входа";







}
