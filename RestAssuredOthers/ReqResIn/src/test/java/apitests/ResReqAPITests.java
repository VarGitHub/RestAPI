package apitests;

import apiclients.ResReqRequests;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ResReqAPITests {
    private final static String SINGLE_USER = "/api/users/2";
    private final static String LIST_USERS = "/api/users?page=2";
    private final static String CREATE_USER = "/api/users";
    private final static String UPDATE_USER = "/api/users/2";
    private final static String DELETE_USER = "/api/users/2";
    ResReqRequests resReqRequests;

    @BeforeClass
    public void initialize() {
        resReqRequests = new ResReqRequests();
    }

    @Test
    public void testGetSingleUser() {
        ValidatableResponse validatableResponse =
                resReqRequests.getSingleUser(SINGLE_USER);
        assertThat(validatableResponse.extract().statusCode(), equalTo(HttpStatus.SC_OK));
        assertThat(validatableResponse.extract().body().path("data.first_name"), equalTo("Janet"));
        System.out.println(validatableResponse.extract().response().getBody().prettyPrint());
    }

    @Test
    public void testGetListUsers() {
        ValidatableResponse validatableResponse =
                resReqRequests.getListUsers(LIST_USERS);
        assertThat(validatableResponse.extract().statusCode(), is(HttpStatus.SC_OK));
        assertThat(validatableResponse.extract().body().path("data[1].last_name"), is("Morris"));
    }

    @Test
    public void testCreateUser() {
        ValidatableResponse validatableResponse =
                resReqRequests.createUser("{\n" +
                        "\"name\": \"moi\",\n" +
                        "\"job\": \"myself\"\n" +
                        "}", CREATE_USER);
        assertThat(validatableResponse.extract().statusCode(), is(HttpStatus.SC_CREATED));
        System.out.println(validatableResponse.extract().response().getBody().prettyPeek());
    }

    @Test
    public void testCreateUserWithStringPayload() throws IOException {
        String payload = getPayloadAsString();
        ValidatableResponse validatableResponse =
                resReqRequests.createUser(payload, CREATE_USER);
        assertThat(validatableResponse.extract().statusCode(), is(HttpStatus.SC_CREATED));
        System.out.println(validatableResponse.extract().response().getBody().prettyPeek());
    }

    public String getPayloadAsString() throws IOException {
        String path = "../ReqResIn/src/test/resources/newUser.json";
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
