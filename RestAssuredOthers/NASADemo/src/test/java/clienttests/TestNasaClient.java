package clienttests;

import client.NasaClient;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNasaClient {

    protected NasaClient nasaClient;

    @BeforeClass
    public void initialize() {
        nasaClient = new NasaClient();
    }

    @Test
    public void testGetAsteroidDataSet() {
        ValidatableResponse validatableResponse =
                nasaClient.getAsteroidDataSet();
        System.out.println(validatableResponse.extract().body().path("near_earth_objects.size()").toString());
        //System.out.println(validatableResponse.extract().body()
          //      .path("near_earth_object[0].is_potentially_hazardous_asteroid"));
        System.out.println(validatableResponse.extract().response().body().prettyPeek());
    }

}
