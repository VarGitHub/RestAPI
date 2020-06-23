package tests.api;

import api.PostPojo;
import api.TypicodeClient;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestTypicode {

    protected TypicodeClient typicodeClient;

    @BeforeClass
    public void initialize() {
        this.typicodeClient = new TypicodeClient();
    }

    @Test
    public void testGetAllPosts() {
        typicodeClient.getAllPosts();
    }

    @Test
    public void testCreatePost() {
        ValidatableResponse validatableResponse =
                this.typicodeClient.createPost("{\n" +
                "\t\"title\": \"varkey\",\n" +
                "\t\"body\": \"test123\",\n" +
                "\t\"userId\": 100\n" +
                "}");
        validatableResponse.statusCode(201);
        System.out.println(validatableResponse.extract().jsonPath().get().toString());
    }

    @Test
    public void testCreatePostWithPojo() {
        PostPojo pojo = new PostPojo();
        pojo.setTitle("From IJ");
        pojo.setBody("Body from IJ");
        pojo.setUserId(111);
        ValidatableResponse validatableResponse =
                this.typicodeClient.createPost(pojo);
        System.out.println(validatableResponse.extract().statusCode());
        assertThat(validatableResponse.extract().statusCode(), equalTo(HttpStatus.SC_CREATED));
        String expectedTitle = validatableResponse.extract().body().path("title");
        System.out.println(expectedTitle + " " +pojo.getTitle());
        assertThat(expectedTitle, is(pojo.getTitle()));
        assertThat(validatableResponse.extract().body().path("body"),
                equalTo(pojo.getBody()));
        assertThat(validatableResponse.extract().body().path("userId"),
                equalTo(pojo.getUserId()));
    }
}
