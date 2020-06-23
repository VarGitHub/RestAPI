package apiclients;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ResReqRequests {
    private String baseUri = "https://reqres.in";

    public ValidatableResponse getSingleUser(String endpoint) {
      return  given()
             .when()
                .get(this.baseUri + endpoint)
             .then();
    }

    public ValidatableResponse getListUsers(String endpoint) {
        return given()
               .when()
                    .get(this.baseUri + endpoint)
                .then();
    }

    public ValidatableResponse createUser(Object payload, String endpoint) {
        return given()
                .header("Content-Type", "application/json")
                .body(payload)
               .when()
                .post(this.baseUri + endpoint)
               .then();
    }
}
