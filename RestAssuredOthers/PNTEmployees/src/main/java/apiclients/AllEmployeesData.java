package apiclients;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AllEmployeesData {

    Map<String, String> mapTest = new HashMap<>();



    private String baseUri;
    private final static String ALL_EMPLOYEE_RESOURCES_ENDPOINT = "/AllEmployeeResources";

    public AllEmployeesData() {
        this.baseUri = "http://info.venturepulse.org:8080/service-webapp/api";
    }

    public ValidatableResponse getAllEmployeeResources() {
        return given()
               .when()
                    .get(this.baseUri + ALL_EMPLOYEE_RESOURCES_ENDPOINT)
               .then();
    }

    public Response getAllEmployeeResourcesResponse() {
        return given()
                .when()
                .get(this.baseUri + ALL_EMPLOYEE_RESOURCES_ENDPOINT);
    }

    public ValidatableResponse createAnEmployeeRecord(Object newEmployee) {
        return given()
                .header("Content-Type", "application/json")
                .body(newEmployee)
                .when()
                .post(this.baseUri + ALL_EMPLOYEE_RESOURCES_ENDPOINT)
                .then();
    }
}
