package apiclients;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class SingleEmployeeData {

    private String baseUri;
    private final static String SINGLE_EMPLOYEE_RESOURCES_ENDPOINT = "/SingleEmployeeResources";

    public SingleEmployeeData() {
        this.baseUri = "http://info.venturepulse.org:8080/service-webapp/api";
    }

    public ValidatableResponse getSingleEmployeeResources(String id) {
        return given()
                .when()
                .get(this.baseUri + SINGLE_EMPLOYEE_RESOURCES_ENDPOINT + "/" + id)
                .then();
    }

    public ValidatableResponse deleteAnEmployeeRecord(String id) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .delete(this.baseUri + SINGLE_EMPLOYEE_RESOURCES_ENDPOINT + "/" + id)
                .then();
    }
}
