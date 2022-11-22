package ru.praktikum.order;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.notNullValue;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

public class GettingListOrdersTest {
    private OrderClient orderClient;

    @Before
    public void setUp(){
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Getting a list of orders")
    @Description("Checking for receiving a list of orders without courierId")
    public void getListOrders(){
        ValidatableResponse response = orderClient.getListOfOrders();
        response.assertThat().body("orders", notNullValue()).and().statusCode(200);
    }
}
