package ru.praktikum.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.client.Client;
import static io.restassured.RestAssured.given;

public class OrderClient extends Client {
    private static final String ORDER_PATH = "/api/v1/orders";
    private static final String DELETE_ORDER_PATH = "/api/v1/orders/cancel";

    @Step("Create order")
    public ValidatableResponse createOrder(Order order){
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(ORDER_PATH)
                .then();
    }

    @Step("Delete created order")
    public ValidatableResponse deleteOrder(int track){
        return given()
                .spec(getSpec())
                .when()
                .queryParam("track", track)
                .put(DELETE_ORDER_PATH)
                .then();
    }

    @Step("Get list of orders without courierId")
    public ValidatableResponse getListOfOrders(){
        return given()
                .spec(getSpec())
                .when()
                .get(ORDER_PATH)
                .then();
    }
}
