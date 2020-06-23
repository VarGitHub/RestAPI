package apitests;

import apiclients.SingleEmployeeData;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestSingleEmployeeData {

    SingleEmployeeData singleEmployeeData;

    @BeforeClass
    public void initialize() {
        this.singleEmployeeData = new SingleEmployeeData();
    }

    @Test
    public void testSingleEmployeeResources() {
        ValidatableResponse validatableResponse =
                singleEmployeeData.getSingleEmployeeResources("590a4acd35522970c7956cdf");
        System.out.println(validatableResponse.extract().jsonPath().get().toString());
        assertThat(validatableResponse.extract().statusCode(), is(HttpStatus.SC_OK));
    }

    @Test
    public void testDeleteAnEmployeeRecord() {
        String id = "5cb57c822fc213338d20c2e8";
        ValidatableResponse validatableResponse =
                this.singleEmployeeData.deleteAnEmployeeRecord(id);
        assertThat(validatableResponse.extract().statusCode(), is(HttpStatus.SC_NO_CONTENT));
    }
}
