package ru.praktikum.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.client.Client;
import static io.restassured.RestAssured.given;

public class CourierClient extends Client {
    private static final String CREATE_PATH = "/api/v1/courier";
    private static final String LOGIN_PATH = "/api/v1/courier/login";
    private static final String DELETE_PATH = "/api/v1/courier/";

    @Step("Create a new courier")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .spec(getSpec())
                .body(courier)
                .when()
                .post(CREATE_PATH)
                .then();
    }

    @Step("Authorization of the courier and obtaining a client id")
    public ValidatableResponse loginCourier(Credentials credentials){
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(LOGIN_PATH)
                .then();
    }

    @Step("Deleting a created courier")
    public ValidatableResponse deleteCourier(int id){
        return given()
                .spec(getSpec())
                .when()
                .delete(DELETE_PATH+id)
                .then();
    }
}
