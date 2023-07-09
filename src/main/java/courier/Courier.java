package courier;

import com.github.javafaker.Faker;

public class Courier{
    private String login;
    private String password;
    private static final Faker FAKER = new Faker();

    public Courier(){
        this.login = FAKER.name().username();
        this.password = FAKER.internet().password();
    }

    public Courier(String login, String password){
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRandomLogin(){
        this.login = FAKER.name().username();
    }
}