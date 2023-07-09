package order;

import asserts.OrderAssert;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;


public class OrderListTests {
    @Test
    @DisplayName("Get order list")
    public void getOrderListSuccess() {
        OrderSteps orderSteps = new OrderSteps();
        ValidatableResponse response = orderSteps.getOrderList();

        OrderAssert.getOrderListSuccess(response);
    }
}