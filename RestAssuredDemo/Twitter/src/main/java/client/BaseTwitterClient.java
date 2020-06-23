package client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTwitterClient {

    private Properties properties = new Properties();
    private InputStream inputStream = null;
    protected String apiKey;
    protected String apiSecretKey;
    protected String accessToken;
    protected String accessTokenSecret;
    protected String baseUri;

    /**
     * This constructor sets the base URI and
     * reads in the keys from the properties file
     * and sets them
     * This will be instantiated typically in the BeforeClass of a test class
     */
    public BaseTwitterClient() {
        this.baseUri = "https://api.twitter.com/1.1";
        try {
            this.inputStream = new FileInputStream("src/main/config/auth.properties");
            // load the properties file
            this.properties.load(this.inputStream);
            // set the keys and tokens
            this.apiKey = this.properties.getProperty("apiKey");
            this.apiSecretKey = this.properties.getProperty("apiSecretKey");
            this.accessToken = this.properties.getProperty("accessToken");
            this.accessTokenSecret = this.properties.getProperty("accessTokenSecret");
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

    private String getBaseUri() {
        return this.baseUri;
    }

    public String getFullUrl(String endpoint) {
        return this.baseUri + endpoint;
    }
}
