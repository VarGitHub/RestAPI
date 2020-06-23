package api;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CurrentWeatherModule {

    private String baseUri;
    private static final String API_KEY = "ea19f24714efd984d474dfc2ad0c5d42";
    private static final String WEATHER_ENDPOINT = "/weather";

    public CurrentWeatherModule() {
        this.baseUri = "http://api.openweathermap.org/data/2.5";
    }

    public ValidatableResponse getCurrentWeatherDataByZipCode(String zip) {
        return given()
                .queryParam("zip", zip)
                .param("appid", API_KEY)
                .when()
                .get(this.baseUri + WEATHER_ENDPOINT)
                .then();
    }
}
