package api;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class NewsClient {

    private String baseUri;
    private final static String EVERYTHING_ENDPOINT = "/everything";
    private final static String API_KEY = "9aa0d51ede5f4d45893aa07b6df2b40f";

    public NewsClient() {
        this.baseUri = "https://newsapi.org/v2";
    }

    public ValidatableResponse getWebNewsForANewsSite(String newsSiteUrl) {
        return given()
                .queryParam("domains", newsSiteUrl)
                .param("apiKey", API_KEY)
                .when()
                .get(this.baseUri + EVERYTHING_ENDPOINT)
                .then();
    }

    public Response getWebNewsForASite(String newsSite) {
        return given()
                .queryParam("domains", newsSite)
                .param("apiKey", API_KEY)
                .when()
                .get(this.baseUri + EVERYTHING_ENDPOINT);
    }
}
