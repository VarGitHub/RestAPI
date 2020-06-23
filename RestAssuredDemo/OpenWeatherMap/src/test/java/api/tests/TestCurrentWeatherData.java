package api.tests;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import openweathermapclients.CurrentWeatherData;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestCurrentWeatherData {
    protected CurrentWeatherData currentWeatherData;

    @BeforeClass
    public void setUp() {
        currentWeatherData = new CurrentWeatherData();
    }

    @Test
    public void testGetCurrentWeatherDataForACity() throws Exception {
        String city = "Seattle";
        Response response = this.currentWeatherData.getCurrentWeatherDataForACity(city);
        response
                .then()
                .statusCode(HttpStatus.SC_OK);
        System.out.println(response.path("cod"));
        System.out.println(response.path("name"));
        System.out.println(response.contentType());
        System.out.println(response.path("main.temp"));
        System.out.println(response.path("wind.speed"));
    }

    @Test
    public void testGetCurrentWeatherDataForAnInvalidCity() throws Exception {
        String city = "Invalid";
        Response response = this.currentWeatherData.getCurrentWeatherDataForACity(city);
        response
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
        assertThat((String)response.path("name"), equalTo(city));
        System.out.println(response.path("cod"));
        System.out.println(response.path("name"));
    }

    @Test
    public void testGetCurrentWeatherDataForABlankCity() throws Exception {
        String city = "";
        Response response = this.currentWeatherData.getCurrentWeatherDataForACity(city);
        response
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

        System.out.println(response.path("cod"));
        System.out.println(response.path("name"));
    }

    @Test
    public void testGetCurrentWeatherDataForACityValidatableResponse() throws Exception {
        String city = "Seattle";
        ValidatableResponse validatableResponse = this.currentWeatherData.getCurrentWeatherDataForACityValidatableResponse(city);
        System.out.println(validatableResponse.extract().body().path("name"));
        assertThat((String) validatableResponse.extract().body().path("name"), equalTo(city));
        System.out.println(validatableResponse.extract().body().path("wind.speed"));
    }
}
