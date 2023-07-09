package asserts;

import io.restassured.response.ValidatableResponse;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.*;
public class OrderAssert {

    public static void createOrderSuccess(ValidatableResponse response){
        response.assertThat()
                .statusCode(SC_CREATED)
                .body("track", is(notNullValue()));
    }
    public static void getOrderListSuccess(ValidatableResponse response){
        response.assertThat()
                .statusCode(SC_OK)
                .body("orders", notNullValue());
    }
}