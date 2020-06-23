package openweathermapclients;

import base.BaseOpenWeatherMapClient;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CurrentWeatherData extends BaseOpenWeatherMapClient {

    protected final static String WEATHER_ENDPOINT = "/weather";

    public Response getCurrentWeatherDataForACity(String city) throws Exception {
       return given()
               .param("appid", this.apiKey)
                //.header("x-api-key", this.apiKey)
                .queryParam("q", city)
                .get(this.getFullUrl(WEATHER_ENDPOINT));
    }


    public ValidatableResponse getCurrentWeatherDataForACityValidatableResponse(String city) throws Exception {
        return given()
                .param("appid", this.apiKey)
                //.header("x-api-key", this.apiKey)
                .queryParam("q", city)
                .get(this.getFullUrl(WEATHER_ENDPOINT))
                .then();
    }
}
