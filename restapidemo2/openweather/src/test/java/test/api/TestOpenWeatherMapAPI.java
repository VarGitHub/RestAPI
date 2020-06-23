package test.api;

import apiclient.OWMapClient;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestOpenWeatherMapAPI {

    OWMapClient owMapClient;

    @BeforeClass
    public void initialize() {
        this.owMapClient = new OWMapClient();
    }

    @Test
    public void testGetCurrentWeatherDataForAValidCity() throws Exception {
        String validCity = "Seattle";
        HttpResponse<JsonNode> response = this.owMapClient.getCurrentWeatherDataForCity(validCity);
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK);
        String actualCityName = response.getBody().getObject().get("name").toString();
        Assert.assertEquals(actualCityName, validCity);
    }

    @Test
    public void testGetCurrentWeatherDataForAnInvalidCity() throws Exception {
        String validCity = "INVALID";
        HttpResponse<JsonNode> response = this.owMapClient.getCurrentWeatherDataForCity(validCity);
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_NOT_FOUND);
        String actualMessage = response.getBody().getObject().get("message").toString();
        System.out.println(response.getBody().toString());
        //String expectedMesage = "city not found";
        //Assert.assertEquals(actualMessage, expectedMesage);
    }

    @Test
    public void testGetCurrentWeatherDataForABlankCity() throws Exception {
        String blankCity = "";
        HttpResponse<JsonNode> response = this.owMapClient.getCurrentWeatherDataForCity(blankCity);
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_BAD_REQUEST);
        String actualMessage = response.getBody().getObject().get("message").toString();
        String expectedMesage = "Nothing to geocode";
        Assert.assertEquals(actualMessage, expectedMesage);
    }
}
