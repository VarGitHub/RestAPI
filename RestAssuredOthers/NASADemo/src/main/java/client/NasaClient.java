package client;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class NasaClient {

    protected String baseUri;
    protected String apiKey = "";
    protected final static String NEO_BROWSE = "/neo/browse";

    public NasaClient() {
        this.baseUri = "https://api.nasa.gov/neo/rest/v1";
        this.apiKey = "DEMO_KEY";
    }

    public ValidatableResponse getAsteroidDataSet() {
        return
                given()
                        .param("api_key", apiKey)
                .when()
                    .get(baseUri + NEO_BROWSE)
                .then();

    }

}
