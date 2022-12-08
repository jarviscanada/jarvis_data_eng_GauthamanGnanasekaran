package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import java.util.List;

public interface Controller {

  Tweet postTweet(String[] args);

  Tweet showTweet(String[] args);

  List<Tweet> deleteTweet(String[] args);
}
