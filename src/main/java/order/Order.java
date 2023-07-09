package order;

import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class Order {
    private List<String> colour;

    private static final Faker FAKER = new Faker();
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    public Order(List<String> colour) {

        FAKER.name().firstName();
        FAKER.name().lastName();
        FAKER.address().fullAddress();
        FAKER.name().firstName();
        FAKER.phoneNumber().cellPhone();
        FAKER.number().randomDigitNotZero();
        FORMATTER.format(Calendar.getInstance().getTime());
        FAKER.gameOfThrones().dragon();
        this.colour = colour;
    }
}