package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.utils.TweetUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TwitterServiceIntTest {
  private static TwitterService service;

  @BeforeClass
  public static void setUp() throws Exception{
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    System.out.println("CONSUMER_KEY: "+CONSUMER_KEY);
    System.out.println("CONSUMER_SECRET: "+CONSUMER_SECRET);
    System.out.println("ACCESS_TOKEN: "+ACCESS_TOKEN);
    System.out.println("TOKEN_SECRET: "+TOKEN_SECRET);

    TwitterHttpHelper helper=new TwitterHttpHelper(CONSUMER_KEY,CONSUMER_SECRET,ACCESS_TOKEN,TOKEN_SECRET);
    TwitterDao dao=new TwitterDao(helper);
    service=new TwitterService(dao);

  }

  @Test
  public void postTweet() {
    String hashtag="#Coding";
    String text="Tweet for CLI Testing..... "+hashtag+" at "+java.time.LocalTime.now();
    Double lat=1d;
    Double lon=-1d;
    Tweet postTweet= TweetUtil.createTweet(text,lon,lat);
    System.out.println(postTweet + "coordinates" );
    System.out.println(postTweet.getCoordinates().getCoordinates()[0]);
    System.out.println(postTweet.getCoordinates().getCoordinates()[1]);
    Tweet response=service.postTweet(postTweet);
    System.out.println(response);
    assertEquals(postTweet.getText(),response.getText());
    assertArrayEquals(postTweet.getCoordinates().getCoordinates(),response.getCoordinates().getCoordinates());
  }

}
