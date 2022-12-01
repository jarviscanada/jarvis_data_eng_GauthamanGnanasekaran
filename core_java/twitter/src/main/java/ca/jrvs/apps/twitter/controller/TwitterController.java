package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.utils.TweetUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller{

  private Service service;
  private static final String COORD_SEP=":";
  private static final String COMMA=",";
  @Autowired
  public TwitterController(Service service){
    this.service = service;
  }
  /**
   * @param args
   * @return
   */
  @Override
  public Tweet postTweet(String[] args) {
    if(args.length!=3){
      throw new IllegalArgumentException("USAGE: TwitterApp \"post\" \"tweet_text\" \"latitude:longitude\"");
    }
    if(args[1].length()==0){
      throw new IllegalArgumentException(" No Text in tweet \n " +
          "                            USAGE: TwitterApp \"post\" \"tweet_text\" \"latitude:longitude\"");
    }
    String text=args[1];
    String coordinates[]=args[2].split(COORD_SEP);
    if(coordinates.length!=2){
      throw new IllegalArgumentException(" Invalid Coordinates \n " +
          "                            USAGE: TwitterApp \"post\" \"tweet_text\" \"latitude:longitude\"");
    }
    Double lon;
    Double lan;
    try {
      lan = Double.parseDouble(coordinates[0]);
      lon = Double.parseDouble(coordinates[1]);
    }catch (Exception e){
      throw new IllegalArgumentException(" Invalid Coordinates \n " +
          "                            USAGE: TwitterApp \"post\" \"tweet_text\" \"latitude:longitude\"",e);
    }
    Tweet postTweet= TweetUtil.createTweet(text, lon, lan);

    return service.postTweet(postTweet);
  }

  /**
   * @param args
   * @return
   */
  @Override
  public Tweet showTweet(String[] args) {
    if( args.length!=2 && args.length!= 3){
      throw new IllegalArgumentException("USAGE: TwitterApp show tweet_id field1,fields2,... ");
    }

    String id=args[1];
    String[] fields = (args.length == 2) ? null : args[2].split(COMMA);


    return service.showTweet(id,fields);
  }

  /**
   * @param args
   * @return
   */
  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if( args.length!=2 ){
      throw new IllegalArgumentException("USAGE: TwitterApp delete id1,id2,.. ");
    }
    String ids[]=args[1].split(COMMA);

    return service.deleteTweet(ids);
  }
}