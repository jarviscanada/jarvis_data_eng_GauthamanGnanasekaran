package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
@org.springframework.stereotype.Service
public class TwitterService implements Service{

  private TwitterDao dao;
  @Autowired
  public TwitterService(TwitterDao dao){
    this.dao  = dao;
  }
  /**
   * @param tweet tweet to be created
   * @return
   */
  @Override
  public Tweet postTweet(Tweet tweet) {
    this.validatePostTweet(tweet);
    return (Tweet)dao.create(tweet);
  }

  /**
   * @param id     id of the tweet
   * @param fields
   * @return
   */
  @Override
  public Tweet showTweet(String id, String[] fields) {
    validateID(id);
    Tweet response = dao.findById(id);
    if(fields != null){
      response = filterTweet(response, fields);

    }
    return response;

  }

  private Tweet filterTweet(Tweet response, String[] fields){
    Class tweetClass = Tweet.class;
    Field f[] = tweetClass.getDeclaredFields();
    HashSet<String> toSet = new HashSet<String>();
    Collections.addAll(toSet, fields);
    try{
      for(Field field:f){
        if (!toSet.contains(field.getName())) {

          char[] getAccess = field.getName().toCharArray();
          getAccess[0] = Character.toUpperCase(getAccess[0]);
          String setMethodName = "set" + String.valueOf(getAccess);
          Class c=field.getType();
          Method method = tweetClass.getDeclaredMethod(setMethodName,c);
          Object obj=null;
          method.invoke(response,obj);
        }
      }
    }
    catch (NoSuchMethodException | IllegalAccessException |
           InvocationTargetException e) {
      throw new IllegalArgumentException("Invalid Field at Filtering", e);
    }
    return response;

  }

  private void validateID(String id){
    try{
      Long.parseLong(id);
    }
    catch(Exception e){
      throw new IllegalArgumentException("Invalid ID format");
    }
  }

  /**
   * Delete tweets from a list of id, and returns a list of deleted tweets
   * @param ids array of id
   * @return List of deleted tweets
   */
  @Override
  public List<Tweet> deleteTweet(String[] ids) {
    List<Tweet> delTweets = new ArrayList<>();
    for(String id : ids){
      validateID(id);
      delTweets.add((Tweet)dao.deleteById(id));
    }
    return delTweets;
  }

  private void validatePostTweet(Tweet tweet){
    int length = tweet.getText().length();
    if(length == 0 || length > 140){
      throw new IllegalArgumentException("Tweet Size should not be zero or exceed 140 characters");
    }
    if(tweet.getCoordinates()!= null && tweet.getCoordinates().getCoordinates()!=null){
      Double lon = tweet.getCoordinates().getCoordinates()[0];
      Double lat = tweet.getCoordinates().getCoordinates()[1];

      if (lon < -180.0 || lon > 180.0) {
        throw new IllegalArgumentException("Longitude out of Range");
      }

      if (lat < -90.0 || lat > 90.0) {
        throw new IllegalArgumentException("Latitude out of Range");
      }
    }
  }
}
