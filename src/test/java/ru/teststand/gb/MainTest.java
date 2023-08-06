package ru.teststand.gb;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class MainTest extends AbstractTest {
    public MainTest() {
    }

    @Test
    void requestToLookAtOtherPeoplesPostsWithoutSort() {
        JsonPath response = ((Response)RestAssured.given().spec(requestSpecification).queryParam("sort", new Object[]{"createdAt"}).queryParam("order", new Object[]{"ASC"}).when().get(getBaseUrl(), new Object[0])).jsonPath();
        int id = (Integer)response.get("data[1].id");
        MatcherAssert.assertThat((Integer)response.get("data[0].id"), Matchers.lessThan(id));
    }

    @Test
    void requestToLookAtOtherPeoplesPostsLessToMore() {
        ((ValidatableResponse)((Response)RestAssured.given().queryParam("owner", new Object[]{"notMe"}).queryParam("sort", new Object[]{"createdAt"}).queryParam("order", new Object[]{"ASC"}).queryParam("page", new Object[]{"1"}).when().get(getBaseUrl() + "api/posts", new Object[0])).then()).spec(responseSpecification);
    }

    @Test
    void requestToLookAtOtherPeoplesPostsMoreToLess() {
        ((ValidatableResponse)((Response)RestAssured.given().queryParam("owner", new Object[]{"notMe"}).queryParam("sort", new Object[]{"createdAt"}).queryParam("order", new Object[]{"DESC"}).queryParam("page", new Object[]{"1"}).when().get(getBaseUrl() + "api/posts", new Object[0])).then()).spec(responseSpecification);
    }

    @Test
    void requestToLookAtALLOtherPeoplesPosts() {
        ((ValidatableResponse)((Response)RestAssured.given().queryParam("owner", new Object[]{"notMe"}).queryParam("sort", new Object[]{"createdAt"}).queryParam("order", new Object[]{"ALL"}).queryParam("page", new Object[]{"1"}).when().get(getBaseUrl() + "api/posts", new Object[0])).then()).spec(responseSpecification);
    }

    @Test
    void requestToLookAtOtherPeoplesPostsNonExistentPage() {
        ((ValidatableResponse)((Response)RestAssured.given().queryParam("owner", new Object[]{"notMe"}).queryParam("sort", new Object[]{"createdAt"}).queryParam("order", new Object[]{"ASC"}).queryParam("page", new Object[]{"10000"}).when().get(getBaseUrl() + "api/posts", new Object[0])).then()).spec(responseSpecification);
    }

    @Test
    void requestToLookAtOtherPeoplesPostsNonExistentPage2() {
        ((ValidatableResponse)((Response)RestAssured.given().queryParam("owner", new Object[]{"notMe"}).queryParam("sort", new Object[]{"createdAt"}).queryParam("order", new Object[]{"ASC"}).queryParam("page", new Object[]{"999"}).when().get(getBaseUrl() + "api/posts", new Object[0])).then()).statusCode(200);
    }

    @Test
    void requestToLookMyPostsWithoutSort() {
        ((ValidatableResponse)((Response)RestAssured.given().queryParam("order", new Object[]{"ASC"}).queryParam("page", new Object[]{"1"}).when().get(getBaseUrl() + "api/posts", new Object[0])).then()).spec(responseSpecification);
    }

    @Test
    void requestToLookMyPostsLessToMore() {
        ((ValidatableResponse)((Response)RestAssured.given().queryParam("sort", new Object[]{"createdAt"}).queryParam("order", new Object[]{"ASC"}).queryParam("page", new Object[]{"1"}).when().get(getBaseUrl() + "api/posts", new Object[0])).then()).spec(responseSpecification);
    }

    @Test
    void requestToLookMyPostsMoreToLess() {
        ((ValidatableResponse)((Response)RestAssured.given().queryParam("sort", new Object[]{"createdAt"}).queryParam("order", new Object[]{"DESC"}).queryParam("page", new Object[]{"1"}).when().get(getBaseUrl() + "api/posts", new Object[0])).then()).spec(responseSpecification);
    }

    @Test
    void requestToLookMyPostsNonExistentPage() {
        ((ValidatableResponse)((Response)RestAssured.given().queryParam("sort", new Object[]{"createdAt"}).queryParam("order", new Object[]{"ASC"}).queryParam("page", new Object[]{"10000"}).when().get(getBaseUrl() + "api/posts", new Object[0])).then()).spec(responseSpecification);
    }
}
