package ru.praktikum.courier;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

public class LoginCourierTest {
    private CourierClient courierClient;
    private int id;
    private Courier courier;
    @Before
    public void setUp(){
        courierClient = new CourierClient();
    }

    @After
    public void cleanUp(){
        courierClient.deleteCourier(id);
    }

    @Test
    @DisplayName("Successful courier authorization")
    @Description("Checking successful client authorization")
    public void successfulClientAuthorization(){
        courier = CourierGenerator.getRandomCourier();
        courierClient.createCourier(courier);
        id = courierClient.loginCourier(Credentials.from(courier)).extract().path("id");
        ValidatableResponse response = courierClient.loginCourier(Credentials.from(courier));
        response.assertThat().body("id", equalTo(id)).and().statusCode(200);
    }
}
