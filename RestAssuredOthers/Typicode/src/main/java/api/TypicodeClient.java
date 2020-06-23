package api;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TypicodeClient {

    private String baseUri;
    private final static String POSTS_ENDPOINT = "/posts";

    public TypicodeClient() {
        this.baseUri = "https://jsonplaceholder.typicode.com";
    }

    public void getAllPosts() {
        given()
                .when()
                .get(this.baseUri + POSTS_ENDPOINT)
                .then()
                .statusCode(200);
    }

    public ValidatableResponse createPost(Object payload) {
        return given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(this.baseUri + POSTS_ENDPOINT)
                .then();
    }
}
