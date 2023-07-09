package courier;


import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static constants.ScooterEndpoints.*;
import static io.restassured.RestAssured.given;


public class CourierSteps{

    @Step("New courier registration.")
    public ValidatableResponse createCourier(Courier courier) {
        return requestSpecification()
                .body(courier)
                .when()
                .post(COURIER_POST_CREATE)
                .then();
    }

    @Step("Courier login.")
    public ValidatableResponse loginCourier(Courier courier) {
        return requestSpecification()
                .body(courier)
                .when()
                .post(COURIER_POST_LOGIN)
                .then();
    }

    @Step("Courier delete.")
    public void deleteCourier(int courierId) {
        requestSpecification()
                .when()
                .delete(COURIER_POST_DELETE + courierId)
                .then();
    }

    private static RequestSpecification requestSpecification() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(PAGE_URL);
    }
}