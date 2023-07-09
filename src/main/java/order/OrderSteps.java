package order;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static constants.ScooterEndpoints.*;
import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Create order.")
    public ValidatableResponse createNewOrder(Order order) {
        return requestSpecification()
                .body(order)
                .when()
                .post(ORDER_POST_CREATE)
                .then();
    }

    @Step("Get order list.")
    public ValidatableResponse getOrderList() {
        return requestSpecification()
                .when()
                .get(ORDER_GET_LIST)
                .then();
    }

    @Step("Cancel order.")
    public ValidatableResponse cancelOrder(int number) {
        return requestSpecification()
                .body(number)
                .when()
                .put(ORDER_PUT_CANCEL)
                .then();
    }

    private static RequestSpecification requestSpecification() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(PAGE_URL);
    }
}
