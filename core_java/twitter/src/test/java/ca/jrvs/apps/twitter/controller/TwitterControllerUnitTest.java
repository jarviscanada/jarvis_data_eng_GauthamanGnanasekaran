package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {
  @Mock
  TwitterService service;

  @InjectMocks
  TwitterController twitterController;

  @Test
  public void postTweet(){
    when(service.postTweet(any())).thenReturn(new Tweet());
    String args[]={"post","text","1d:2d"};
    Tweet post=twitterController.postTweet(args);
    assertNull(post.getText());
  }
  @Test
  public void showTweet() {
    when(service.showTweet(any(),any())).thenReturn(new Tweet());
    String args[]={"show","123","fields"};
    Tweet get=twitterController.showTweet(args);
    assertNull(get.getText());
  }

  @Test
  public void deleteTweet() {
    when(service.deleteTweet(any())).thenReturn(new ArrayList<>());
    String args[]={"delete","123,2334"};
    List<Tweet> delete=twitterController.deleteTweet(args);
    assertEquals(0,delete.size());

  }
}
