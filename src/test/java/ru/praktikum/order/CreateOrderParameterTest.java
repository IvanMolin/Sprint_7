package ru.praktikum.order;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.equalTo;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

@RunWith(Parameterized.class)
public class CreateOrderParameterTest {
    private OrderClient orderClient;
    private int track;
    private Order order;
    private int statusCode;

    public CreateOrderParameterTest(Order order, int statusCode) {
        this.order = order;
        this.statusCode = statusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData(){
        return new Object[][]{
                {OrderGenerator.getOrderWithGrayScooter(), 201},
                {OrderGenerator.getOrderWithBlackScooter(), 201},
                {OrderGenerator.getOrderWithBlackAndGreyScooter(), 201},
                {OrderGenerator.getOrderWithoutScooterColor(), 201},
        };
    }

    @Before
    public void setUp(){
        orderClient = new OrderClient();
    }

    @After
    public void cleanUp(){
        orderClient.deleteOrder(track);
    }

    @Test
    @DisplayName("Successful order creation")
    @Description("Checking successful order creation")
    public void canCreateAnOrder(){
        ValidatableResponse response = orderClient.createOrder(order);
        track = response.extract().body().path("track");
        response.assertThat().body("track",equalTo(track)).and().statusCode(statusCode);
    }




}
