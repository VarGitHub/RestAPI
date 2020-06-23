package apiclient;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class EmployeeResources {

    private String baseUri;
    private static final String ALL_EMPLOYEE_RESOURCES = "/AllEmployeeResources";
    private static final String SINGLE_EMPLOYEE_RESOURCES = "/AllEmployeeResources";

    public EmployeeResources() {
        this.baseUri = "http://info.venturepulse.org:8080/service-webapp/api";
    }

    public ValidatableResponse getAllEmployeesData() {
        return given()
                .when()
                .get(this.baseUri + ALL_EMPLOYEE_RESOURCES)
                .then();
    }
}
