package ru.teststand.gb;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

public class AuthTest {
    public AuthTest() {
    }

    @Test
    void getXAuthToken() {
        Object response = ((ValidatableResponse)((Response)RestAssured.given().contentType("application/x-www-form-urlencoded").formParam("username", new Object[]{"vistasaxs"}).formParam("password", new Object[]{"03d6ac54a0"}).when().post("https://test-stand.gb.ru/gateway/login", new Object[0])).then()).extract().jsonPath().get("token").toString();
        System.out.println("token" + response);
    }

    @Test
    void noGetXAuthTokenWithInvalidLogin() {
        ((ValidatableResponse)((Response)RestAssured.given().contentType("application/x-www-form-urlencoded").formParam("username", new Object[]{"vistasaxs"}).formParam("password", new Object[]{"03d6ac54a0"}).when().post("https://test-stand.gb.ru/gateway/login", new Object[0])).then()).statusCode(401);
    }

    @Test
    void noGetXAuthTokenWithInvalidPassword() {
        ((ValidatableResponse)((Response)RestAssured.given().contentType("application/x-www-form-urlencoded").formParam("username", new Object[]{"vistasaxs"}).formParam("password", new Object[]{"1234"}).when().post("https://test-stand.gb.ru/gateway/login", new Object[0])).then()).statusCode(401);
    }
}

