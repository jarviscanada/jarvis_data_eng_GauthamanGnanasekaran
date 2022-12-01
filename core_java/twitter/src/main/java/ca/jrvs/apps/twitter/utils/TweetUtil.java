package ca.jrvs.apps.twitter.utils;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.model.Entities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class TweetUtil {
  /**
   * create a new tweet with text and coordinates
   *
   * @param text status
   * @param lon longitude
   * @param lat latitude
   * @return a new tweet
   */
  public static Tweet createTweet(String text, Double lon, Double lat) {
    Tweet tweet = new Tweet();
    Entities entities = new Entities();
    entities.setHashtags(new ArrayList<>());
    entities.setUserMention(new ArrayList<>());

    Coordinates coordinates = new Coordinates();
    Double coords[] = new Double[2];
    coords[0]=lon;
    coords[1]=lat;
    coordinates.setCoordinates(coords);
    coordinates.setType("Point");

    tweet.setText(text);
    tweet.setCoordinates(coordinates);
    tweet.setEntities(entities);
    tweet.setRetweet_count(0);
    tweet.setRetweet_count(0);
    tweet.setFavorited(false);
    tweet.setRetweeted(false);
    return tweet;
  }
}
