package asserts;

import constants.ReturnMessages;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.apache.http.HttpStatus.*;

import org.junit.Assert;

public class CourierAssert {

    public static void createCourierErrorNotEnoughData(ValidatableResponse response){
        response.assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo(ReturnMessages.NOT_ENOUGH_DATA_CREATE));
    }

    public static void createCourierSuccess(ValidatableResponse response){
        response.assertThat().statusCode(SC_CREATED)
                .body("ok", is(true));
    }

    public static void idIsNotNull(Integer id){
        Assert.assertNotNull("Courier id is null.", id);
    }

    public static void createCourierWithSameLogin(ValidatableResponse response){
        response.assertThat()
                .statusCode(SC_CONFLICT)
                .body("message", equalTo(ReturnMessages.CONFLICT));
    }

    public static void loginCourierSuccess(ValidatableResponse response) {
        response.assertThat()
                .statusCode(SC_OK)
                .body("id", greaterThan(0))
                .extract()
                .path("id");
    }
    public static void loginCourierNotEnoughData(ValidatableResponse response) {
        response.assertThat()
                .statusCode(SC_BAD_REQUEST)
                .body("message", equalTo(ReturnMessages.NOT_ENOUGH_DATA_LOGIN));
    }
    public static void loginCourierErrorAccountNotFound(ValidatableResponse response) {
        response.assertThat()
                .statusCode(SC_NOT_FOUND)
                .body("message", equalTo(ReturnMessages.NOT_FOUND));
    }
}