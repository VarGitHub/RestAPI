package apiclient;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

/**
 * This class is an api client for Open Weather Map
 */

public class OWMapClient {
    private String baseUri;
    private String apiKey = "ea19f24714efd984d474dfc2ad0c5d42";
    private final static String WEATHER_ENDPOINT = "/weather";

    public OWMapClient() {
        this.baseUri = "http://api.openweathermap.org/data/2.5";
    }

    public HttpResponse<JsonNode> getCurrentWeatherDataForCity(String city) throws Exception {
        return Unirest.get(fullUrl(WEATHER_ENDPOINT))
                .queryString("q", city)
                .header("x-api-key", this.apiKey)
                .asJson();
       }

    /**
     * Gets base URI
     *
     * @return base uri
     */

    public String getBaseUri() {
        return this.baseUri;
    }

    /**
     * It returns the full url
     *
     * @param endpoint end point required for full url
     * @return String format of the full url
     */
     public String fullUrl(String endpoint) {
        return getBaseUri() + endpoint;
    }

}
