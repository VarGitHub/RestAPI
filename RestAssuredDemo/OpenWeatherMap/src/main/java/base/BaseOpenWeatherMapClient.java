package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseOpenWeatherMapClient {
    private Properties properties = new Properties();
    private InputStream inputStream = null;
    protected String apiKey;
    protected String baseUri;

    public BaseOpenWeatherMapClient() {
        this.baseUri = "http://api.openweathermap.org/data/2.5";
        try {
            this.inputStream = new FileInputStream("src/main/config/auth.properties");
            // load the properties file
            this.properties.load(this.inputStream);
            // set the keys and tokens
            this.apiKey = this.properties.getProperty("apiKey");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (this.inputStream != null) {
                try {
                    this.inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getBaseUri() {
        return this.baseUri;
    }

    public String getFullUrl(String endpoint) {
        return this.baseUri + endpoint;
    }
}
