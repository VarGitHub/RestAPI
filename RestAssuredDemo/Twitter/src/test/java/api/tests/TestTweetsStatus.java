package api.tests;

import client.TweetsClient;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;

public class TestTweetsStatus {
    protected TweetsClient tweetsClient;
    protected long tweetId;
    protected List<Long> tweetIdList; //where all tweets will be stored

    @BeforeClass
    public void setUp() {
        this.tweetsClient = new TweetsClient();
    }

    @Test
    public void testGetStatusesUserTimeline() throws Exception {
        this.tweetsClient.getStatusesHomeTimelineAndVerify();
    }

    @Test
    public void testPostTweet() throws Exception {
        String tweet = "Should learn to refrain from making unnecessary comments";
        Response response = this.tweetsClient.createTweet(tweet);
        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testUserCannotTweetTheSameTweetTwice() throws Exception {
        String tweet = "My tweet" + UUID.randomUUID();
        System.out.println("First attempt");
        Response response = this.tweetsClient.createTweet(tweet);
        response.then()
                .statusCode(HttpStatus.SC_OK);
        System.out.println("Second attempt");
        response = this.tweetsClient.createTweet(tweet);
        response.then()
                .statusCode(HttpStatus.SC_FORBIDDEN)
                .and()
                .body("errors[0].message", equalTo("Status is a duplicate."));
    }

    // This test just tests if user can create a tweet but
    // we're adding the tweet to tweetIdList
    @Test
    public void testUserTweet() throws Exception {
        String tweet = "My tweet " + UUID.randomUUID();
        Response response = this.tweetsClient.createTweet(tweet);
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
        this.tweetId = response.path("id");
        assertThat((Integer)response.path("status"), equalTo(HttpStatus.SC_ACCEPTED));
        System.out.println(response.body().peek());
        System.out.println(response.path("status"));
    }

    // This test depends on testUserTweet() to run first
    // since we need to know the tweet ID in order to delete
    @Test(dependsOnMethods = {"testUserTweet"})
    public void deleteTweet() throws Exception {
        Response response = this.tweetsClient.deleteTweet(this.tweetId);
        response.then()
                .statusCode(HttpStatus.SC_OK);
        response = this.tweetsClient.getRecentTweets();
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("$", not(hasValue(this.tweetId)));
    }

    // We can also implement rest api for cleaning up our tests
    // if we were to keep track of all tweets and store them to a list
    // then we can use deleteTweet() method to delete all user's tweets
    @AfterClass
    public void cleanUp() throws Exception {
        // delete tweets
        if (this.tweetIdList !=  null) {
            for (Long tweetId : this.tweetIdList) {
                this.tweetsClient.deleteTweet(tweetId);
            }
        }
    }
}
