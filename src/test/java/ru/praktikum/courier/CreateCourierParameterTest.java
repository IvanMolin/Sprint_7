package ru.praktikum.courier;

import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.hamcrest.CoreMatchers.equalTo;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;

@RunWith(Parameterized.class)
public class CreateCourierParameterTest {
    private CourierClient courierClient;
    private Courier courier;
    private int errorCode;
    private String message;

    public CreateCourierParameterTest(Courier courier, int errorCode, String message) {
        this.courier = courier;
        this.errorCode = errorCode;
        this.message = message;
    }

    @Parameterized.Parameters
    public static Object[][] getDataCourierTest(){
        return new Object[][]{
                {CourierGenerator.getOnlyLoginCourier(), 400, "Недостаточно данных для создания учетной записи"},
                {CourierGenerator.getOnlyPasswordCourier(), 400, "Недостаточно данных для создания учетной записи"},
                {CourierGenerator.getOnlyLastNameCourier(), 400, "Недостаточно данных для создания учетной записи"},
        };
    }

    @Before
    public void setUp(){
      courierClient = new CourierClient();
    }
// Не использую удаление данных, так как в данном тесте проверяются только негативные сценарии по созданию курьера
    @Test
    @DisplayName("Unable to create courier")
    @Description("Unable to create a courier without filling in the fields 'Login','Password'")
    public void cannotCreateCourierNoData(){
        ValidatableResponse response = courierClient.createCourier(courier);
        response.assertThat().body("message", equalTo(message)).and().statusCode(errorCode);
    }





}
