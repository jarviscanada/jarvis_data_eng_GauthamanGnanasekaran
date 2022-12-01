package ca.jrvs.apps.twitter.dao.helper;

import com.google.gdata.util.common.base.PercentEscaper;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.net.URI;

public class TwitterHttpHelperTest {

  @Test
  public void httpPost() throws URISyntaxException {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey + ":" + consumerSecret + ":" + accessToken + ":" + tokenSecret);
    //Creating components
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    String testString = "Tesing JUNIT";
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    URI uri = new URI("https://api.twitter.com/1.1/statuses/update.json?status="+percentEscaper.escape(testString));
    HttpResponse response = httpHelper.httpPost(uri);
  }
}