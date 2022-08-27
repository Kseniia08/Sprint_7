package TestsData;
import static Constants.Constants.*;

public class CourierGenerator {
    //правильный курьер
    public static Courier getDefault() {
        return new Courier(LOGIN_DATA, PASSWORD_DATA, FINAL_NAME_DATA);
    }

    //курьез без пароля
    public static Courier getCourierWithoutPassword() {
        return new Courier (LOGIN_DATA, "", FINAL_NAME_DATA);
    }

    //курьез без логина
    public static Courier getCourierWithoutLogin() {
        return new Courier ("", PASSWORD_DATA, FINAL_NAME_DATA);
    }


    //зарегестрированный курьер
    public static Courier getRightCourier() {
        return new Courier (LOGIN_DATA_FOR_LOG_IN, PASSWORD_DATA_FOR_LOG_IN, "");
    }

    //незарегестрированный курьер
    public static Courier getNonExistCourier() {
        return new Courier(NON_EXISTENT_LOGIN_DATA, NON_EXISTENT_PASSWORD_DATA, "");
    }
}
