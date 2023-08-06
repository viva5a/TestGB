package ru.teststand.gb;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

public class RequestsFromNoAuthorizedUsersTest {
    public RequestsFromNoAuthorizedUsersTest() {
    }

    @Test
    void failRequestToLookAtOtherPeoplesPostsNoAuthorizedUser() {
        ((ValidatableResponse)((Response)RestAssured.given().queryParam("owner", new Object[]{"notMe"}).queryParam("sort", new Object[]{"createdAt"}).queryParam("order", new Object[]{"ASC"}).queryParam("page", new Object[]{"1"}).when().get("https://test-stand.gb.ru/api/posts", new Object[0])).then()).statusCode(401);
    }

    @Test
    void failRequestToLookMyPostsNoAuthorizedUser() {
        ((ValidatableResponse)((Response)RestAssured.given().queryParam("sort", new Object[]{"createdAt"}).queryParam("order", new Object[]{"ASC"}).queryParam("page", new Object[]{"1"}).when().get("https://test-stand.gb.ru/api/posts", new Object[0])).then()).statusCode(401);
    }
}

