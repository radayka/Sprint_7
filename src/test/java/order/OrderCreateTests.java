package order;

import asserts.OrderAssert;
import constants.ScooterColour;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import java.util.List;

@RunWith(Parameterized.class)
public class OrderCreateTests {
    private final List<String> colour;
    private int track;
    private OrderSteps orderSteps;

    public OrderCreateTests(List<String> colour) {
        this.colour = colour;
    }

    @Parameterized.Parameters(name = "ScooterColour: {0}")
    public static Object[][] getScooterColour() {
        return new Object[][]{
                {List.of(ScooterColour.BLACK)},
                {List.of(ScooterColour.GREY)},
                {List.of(ScooterColour.GREY, ScooterColour.BLACK)},
                {List.of()}
        };
    }

    @Before
    public void setUp() {
        orderSteps = new OrderSteps();
    }

    @After
    public void cleanUp() {
        orderSteps.cancelOrder(track);
    }

    @Test
    @DisplayName("Create orders for different scooter colours.")
    public void orderScooterInDifferentColors() {
        Order order = new Order(colour);

        ValidatableResponse response = orderSteps.createNewOrder(order);
        track = response.extract().path("track");

        OrderAssert.createOrderSuccess(response);
    }
}