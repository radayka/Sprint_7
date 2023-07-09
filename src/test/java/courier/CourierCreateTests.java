package courier;

import asserts.CourierAssert;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;


public class CourierCreateTests {
    private int courierId;
    private CourierSteps courierSteps;
    private Courier courier;

    @Before
    public void setUp() {
        courierSteps = new CourierSteps();
        courier = new Courier();
    }

    @After
    public void cleanUp() {
        if (courierId != 0) {
            courierSteps.deleteCourier(courierId);
        }
    }

    @Test
    @DisplayName("Create new courier.")
    public void courierCanBeCreated() {
        ValidatableResponse response = courierSteps.createCourier(courier);
        courierId = courierSteps.loginCourier(courier).extract().path("id");

        CourierAssert.createCourierSuccess(response);
        CourierAssert.idIsNotNull(courierId);
    }

    @Test
    @DisplayName("Create new courier with empty login.")
    public void courierCreateErrorEmptyLogin() {
        courier.setLogin("");
        ValidatableResponse response = courierSteps.createCourier(courier);
        CourierAssert.createCourierErrorNotEnoughData(response);
    }

    @Test
    @DisplayName("Create new courier with empty password.")
    public void courierCreateErrorEmptyPassword() {
        courier.setPassword("");
        ValidatableResponse response = courierSteps.createCourier(courier);
        CourierAssert.createCourierErrorNotEnoughData(response);
    }

    @Test
    @DisplayName("Create new courier with empty login and password.")
    public void courierCreateErrorEmptyLoginAndPassword() {
        courier = new Courier("", "");
        ValidatableResponse response = courierSteps.createCourier(courier);
        CourierAssert.createCourierErrorNotEnoughData(response);
    }

    @Test
    @DisplayName("Create new courier that already registered.")
    public void courierCreateErrorSameLogin() {
        courierSteps.createCourier(courier);
        ValidatableResponse response = courierSteps.createCourier(courier);
        CourierAssert.createCourierWithSameLogin(response);
    }
}