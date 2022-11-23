package ru.praktikum.courier;

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
public class LoginCourierParameterTest {
    private CourierClient courierClient;
    private Courier courier;
    private Credentials credentials;
    private int statusCode;
    private String message;
    private int id;

    public LoginCourierParameterTest(Courier courier, Credentials credentials, int statusCode, String message) {
        this.courier = courier;
        this.credentials = credentials;
        this.statusCode = statusCode;
        this.message = message;
    }

    @Parameterized.Parameters
    public static Object[][] getDataLoginCourierTest(){
        return new Object[][]{
                {CourierGenerator.getRandomCourier(), CourierGenerator.enterOnlyLoginCourier(), 400, "Недостаточно данных для входа"},
                {CourierGenerator.getRandomCourier(), CourierGenerator.enterOnlyPasswordCourier(), 400, "Недостаточно данных для входа"},
                {CourierGenerator.getRandomCourier(), CourierGenerator.enterIncorrectLoginCourier(), 404, "Учетная запись не найдена"},
                {CourierGenerator.getRandomCourier(), CourierGenerator.enterIncorrectPasswordCourier(), 404, "Учетная запись не найдена"},
                {CourierGenerator.getRandomCourier(), CourierGenerator.getNonExistentCourier(), 404, "Учетная запись не найдена"},
        };
    }

    @Before
    public void setUp(){
        courierClient = new CourierClient();
    }

    @After
    public void cleanUp(){
        courierClient.deleteCourier(id);
    }

    @Test
    @DisplayName("Unable to login")
    @Description("Unable to login with incomplete and incorrect data")
    public void courierCannotBeAuthorized(){
        courierClient.createCourier(courier);
        id = courierClient.loginCourier(Credentials.from(courier)).extract().path("id");
        ValidatableResponse response = courierClient.loginCourier(credentials);
        response.assertThat().body("message", equalTo(message)).and().statusCode(statusCode);
    }


}
