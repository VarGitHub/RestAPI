package apitests;

import api.CurrentWeatherModule;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TestCurrentWeatherData {

    CurrentWeatherModule currentWeatherModule;

    @BeforeClass
    public void initialize() {
        this.currentWeatherModule = new CurrentWeatherModule();
    }

    @Test
    public void testGetCurrentWeatherDataByZipCode() {
        String zipCode = "98075";
        ValidatableResponse validatableResponse =
                this.currentWeatherModule.getCurrentWeatherDataByZipCode(zipCode);
        System.out.println(validatableResponse.extract().response().getBody().prettyPeek());
        assertThat(validatableResponse.extract().statusCode(), is(equalTo(HttpStatus.SC_OK)));
    }
}
