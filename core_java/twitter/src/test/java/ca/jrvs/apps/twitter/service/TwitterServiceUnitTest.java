package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.utils.TweetUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;


import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  TwitterDao dao;

  @InjectMocks
  TwitterService service;

  @Test
  public void postTweet() {
    Tweet tweet = TweetUtil.createTweet("testing service...", 2d, -2d);
    System.out.println(tweet);
    when(dao.create(any())).thenReturn(new Tweet());
    Tweet reTweet=service.postTweet(tweet);
    System.out.println(reTweet);
    assertNull(reTweet.getText());

  }
}
