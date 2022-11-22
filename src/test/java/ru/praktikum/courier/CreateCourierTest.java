package ru.praktikum.courier;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

public class CreateCourierTest {
    private CourierClient courierClient;
    private int id;
    private Courier courier;

    @Before
    public void setUp(){
        courierClient = new CourierClient();
        courier = CourierGenerator.getDefaultCourier();
    }

    @After
    public void cleanUp(){
        courierClient.deleteCourier(id);
    }

    @Test
    @DisplayName("Creating a courier")
    @Description("Checking the possibility of creating a courier")
    public void courierCanBeCreated(){
        ValidatableResponse response = courierClient.createCourier(courier);
        ValidatableResponse responseLoginCourier = courierClient.loginCourier(Credentials.from(courier));
        id = responseLoginCourier.extract().path("id");
        response.assertThat().body("ok", equalTo(true)).and().statusCode(201);
    }

    @Test
    @DisplayName("Creation of couriers with identical logins")
    @Description("Checking the impossibility of creating two couriers with the same login")
    public void courierCannotBeCreatedWithDuplicateLogin(){
        courierClient.createCourier(courier);
        ValidatableResponse response = courierClient.createCourier(courier);
        ValidatableResponse responseLoginCourier = courierClient.loginCourier(Credentials.from(courier));
        id = responseLoginCourier.extract().path("id");
        response.assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой.")).and().statusCode(409);
    }

}