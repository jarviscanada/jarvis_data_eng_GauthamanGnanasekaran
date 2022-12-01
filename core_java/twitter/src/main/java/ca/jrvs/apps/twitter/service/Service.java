package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.model.Tweet;
import java.util.List;

public interface Service {

  /**
   * Post a tweet inputted by the user
   * @param tweet tweet to be created
   * @return created tweet
   */
  Tweet postTweet(Tweet tweet);

  /**
   * Search a tweet by its ID
   * @param id id of the tweet
   * @param fields
   * @return tweet object to be returned
   */
  Tweet showTweet(String id, String[] fields);

  /**
   * delete a list of tweets by its id
   * @param ids List of ids to be deleted
   * @return
   */
  List<Tweet> deleteTweet(String[] ids);



}
