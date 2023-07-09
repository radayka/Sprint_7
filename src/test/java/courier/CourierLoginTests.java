package courier;

import asserts.CourierAssert;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;

public class CourierLoginTests {

    private int courierId;
    private Courier courier;
    private CourierSteps courierSteps;

    @Before
    public void setUp() {
        courierSteps = new CourierSteps();
        courier = new Courier();
        courierSteps.createCourier(courier);
    }

    @After
    public void cleanUp() {
        if (courierId != 0) {
            courierSteps.deleteCourier(courierId);
        }
    }

    @Test
    @DisplayName("Courier successful login.")
    public void courierLoginSuccess() {
        ValidatableResponse response = courierSteps.loginCourier(courier);
        courierId = courierSteps.loginCourier(courier).extract().path("id");
        CourierAssert.loginCourierSuccess(response);
        CourierAssert.idIsNotNull(courierId);
    }

    @Test
    @DisplayName("Courier login with empty login.")
    public void courierLoginErrorEmptyLogin() {
        courier.setLogin("");
        ValidatableResponse response = courierSteps.loginCourier(courier);
        CourierAssert.loginCourierNotEnoughData(response);
    }

    @Test
    @DisplayName("Courier login with empty password.")
    public void courierLoginErrorEmptyPassword() {
        courier.setPassword("");
        ValidatableResponse response = courierSteps.loginCourier(courier);
        CourierAssert.loginCourierNotEnoughData(response);
    }

    @Test
    @DisplayName("Courier login with empty login and password")
    public void courierLoginErrorEmptyLoginAndPassword() {
        courier.setLogin("");
        courier.setPassword("");
        ValidatableResponse response = courierSteps.loginCourier(courier);
        CourierAssert.loginCourierNotEnoughData(response);
    }

    @Test
    @DisplayName("Courier login with not valid login.")
    public void courierLoginErrorAccountNotFound() {
        courier.setRandomLogin();
        ValidatableResponse response = courierSteps.loginCourier(courier);
        CourierAssert.loginCourierErrorAccountNotFound(response);
    }
}