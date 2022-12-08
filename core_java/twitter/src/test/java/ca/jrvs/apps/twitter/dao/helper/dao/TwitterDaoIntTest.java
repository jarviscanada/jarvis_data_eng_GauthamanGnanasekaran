package ca.jrvs.apps.twitter.dao.helper.dao;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.utils.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.utils.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gdata.util.common.base.PercentEscaper;
import net.minidev.json.JSONUtil;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TwitterDaoIntTest {

  private TwitterDao dao;
  private JsonParser jsonParser;

  @Before
  public void setUp() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);
    //Set up dependency
    TwitterHttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    //pass dependency
    this.dao = new TwitterDao(httpHelper);
    this.jsonParser = new JsonParser();
  }
  @Test
  public void create() throws Exception{
    String hashtag = "#abcd";
    String text = "@Unit Test Creating Twitter Test " + hashtag + " " + java.time.LocalTime.now();
    Double lat = 1d;
    Double lon = -1d;

    Tweet postTweet = TweetUtil.createTweet(text, lon, lat);
    System.out.println("Creating tweet");
    System.out.println(JsonParser.toJson(postTweet, true, false));
    Tweet tweet = dao.create(postTweet);
    System.out.println("after implementing create: " + tweet);
    assertEquals(text, tweet.getText());
    assertNotNull(tweet.getCoordinates().getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().length);
    assertEquals(lon, tweet.getCoordinates().getCoordinates()[0]);
    assertEquals(lat, tweet.getCoordinates().getCoordinates()[1]);
  }

  @Test
  public void findById() throws JsonProcessingException {
    String hashtag="#Coding";
    String text="Tweet for finding by id Testing......"+hashtag+" at "+java.time.LocalTime.now();
    Double lat=1d;
    Double lon=-1d;
    Tweet postTweet= TweetUtil.createTweet(text,lon,lat);
    System.out.println(JsonParser.toJson(postTweet, true, false));
    Tweet tweet= dao.create(postTweet);
    System.out.println("after implementing create: " + tweet);
    Tweet foundTweet= dao.findById(tweet.getId_str());

    assertEquals(foundTweet.getText(),tweet.getText());
    assertEquals(foundTweet.getId(),tweet.getId());
    assertEquals(lon,foundTweet.getCoordinates().getCoordinates()[0]);
    assertEquals(lat,foundTweet.getCoordinates().getCoordinates()[1]);



  }

//  @Test
//  public void deleteById() {
//    //update string id with newly created tweet ID
//    String id = "1595997822503075841";
//    Tweet tweet = dao.deleteById(id);
//    assertEquals(id, tweet.getId_str());
//    assertNotNull(tweet);
//    assertNotNull(tweet.getText());
//  }
}
