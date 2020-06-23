package client;

import org.apache.http.HttpStatus;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class TweetsClient extends BaseTwitterClient {
    protected final static String STATUSES_USER_TIMELINE_PATH = "/statuses/user_timeline.json";
    protected final static String TWEET_STATUS_UPDATE_PATH = "/statuses/update.json";
    protected final static String DESTROY_TWEET_PATH = "/statuses/destroy.json";

    // method that checks to see if the request responds with a 200 status code
    // this is not ideal since you usually want to verify your test at test class level
    // bad :(
    public void getStatusesHomeTimelineAndVerify() throws Exception {
        given()
                .auth()
                .oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when()
                .get(this.getFullUrl(STATUSES_USER_TIMELINE_PATH))
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    // method that creates tweet but its not ideal because you want to verify your
    // test at class level
    // bad :(
    public void createTweetAndVerify(String tweet) throws Exception {
        given()
                .auth()
                .oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when()
                .post(this.getFullUrl(TWEET_STATUS_UPDATE_PATH))
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    // proper way of writing a helper method
    // return type is a response and you can use this response to verify
    // in your test class
    // allows you to re-use the same method multiple times for many tests
    // good :)
    public Response createTweet(String tweet) throws Exception {
        return given()
                .auth()
                .oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when()
                .post(this.getFullUrl(TWEET_STATUS_UPDATE_PATH));
    }

    /**
     * Delete's user's tweet
     *
     * @param tweetId id of the tweet
     * @return Response of the request
     * @throws Exception
     */
    public Response deleteTweet(Long tweetId) throws Exception {
        return given()
                .auth()
                .oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when()
                .post(this.getFullUrl(DESTROY_TWEET_PATH));
    }

    /**
     * Retrieves the user's recent tweets
     *
     * @return Response of the request
     * @throws Exception
     */
    public Response getRecentTweets() throws Exception {
        return given()
                .auth()
                .oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when()
                .get(this.getFullUrl(STATUSES_USER_TIMELINE_PATH));
    }
}
