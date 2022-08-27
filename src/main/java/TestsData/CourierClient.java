package TestsData;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static Constants.Constants.*;
import static io.restassured.RestAssured.given;


public class CourierClient  extends  RestClient {

    //create
    @Step("Создание нового курьера {courier}")
    public ValidatableResponse create (Courier courier) {
        return given()
                .spec(getBaseSpec())
                .body(courier)
                .when()
                .post(COURIER_CREATE_PATH)
                .then();
    }

    //login
    @Step("Авторизацию курьера")
    public ValidatableResponse login (CourierCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(COURIER_AUTH_PATH)
                .then();
    }

    //delete
    @Step("Удаление курьера")
    public static ValidatableResponse delete(int id) {
        return given()
                .spec(getBaseSpec())
                .pathParam("id", id)
                .when()
                .delete(COURIER_DELETE_PATH + "{id}")
                .then();
    }

}
