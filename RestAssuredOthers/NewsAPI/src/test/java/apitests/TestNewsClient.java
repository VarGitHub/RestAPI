package apitests;

import api.NewsClient;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestNewsClient {

    NewsClient newsClient;

    @BeforeClass
    public void initialize() {
        newsClient = new NewsClient();
    }

    @Test
    public void testGetWebNewsForANewsSite() {
        ValidatableResponse validatableResponse =
                this.newsClient.getWebNewsForANewsSite("cnn.com");
        List<String> jsonResponse = new ArrayList<>();
        Response response = validatableResponse.extract().response();
        for (int i = 0; i < response.jsonPath().getList("articles").size(); i++)
            jsonResponse.add(response.jsonPath().getList("articles").get(i).toString());
        for (String str : jsonResponse) {
            System.out.println(str);
        }
    }

    @Test
    public void TestGetWebNewsForASite() {
        Response response = this.newsClient.getWebNewsForASite("cnn.com");
        //System.out.println(response.getBody().prettyPeek());
        List<String> jsonResponse = new ArrayList<>();
        for (int i = 0; i < response.jsonPath().getList("articles").size(); i++) {
            jsonResponse.add(response.jsonPath().getList("articles").get(i).toString());
            //System.out.println(response.jsonPath().getList("articles").get(i));
        }

        for (String str : jsonResponse) {
            System.out.println(str);
        }
    }

    @Test
    public void testGetWebNewsForCNN() {
        ValidatableResponse validatableResponse =
                this.newsClient.getWebNewsForANewsSite("cnn.com");
        assertThat(validatableResponse.extract().statusCode(), is(HttpStatus.SC_OK));
    }

    @Test
    public void testGetWebNewsForWSJ() {
        ValidatableResponse validatableResponse =
                this.newsClient.getWebNewsForANewsSite("wsj.com");
        assertThat(validatableResponse.extract().statusCode(), is(HttpStatus.SC_OK));
    }
}
