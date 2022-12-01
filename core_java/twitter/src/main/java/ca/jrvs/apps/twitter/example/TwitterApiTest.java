package ca.jrvs.apps.twitter.example;

import com.google.gdata.util.common.base.PercentEscaper;
import java.util.Arrays;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public class TwitterApiTest {
  private static String CONSUMER_KEY = System.getenv("consumerKey");
  //private static String CONSUMER_KEY = "e6da9fxbhAK0AyqEf87vbRvMB";
  private static String CONSUMER_SECRET = System.getenv("consumerSecret");
  //private static String CONSUMER_SECRET = "dSpa2dV80HdVeRen82MNdW2r5jcWcKqHWiyKzhcfgc8b4kkyNn";
  private static String ACCESS_TOKEN = System.getenv("accessToken");
  //private static String ACCESS_TOKEN = "1451354783315550212-m6nFFRZlllOyh32kzm6ZHrmcbOsawJ;";
  private static String TOKEN_SECRET = System.getenv("tokenSecret");
  //private static String TOKEN_SECRET ="Xm5ApoA57Vx80NUAtFaYcIvSs7U5mNpAy5LYEdkqQWMzb";

  public static void main(String[] args) throws Exception {

    //Setup OAuth
    OAuthConsumer consumer= new CommonsHttpOAuthConsumer(CONSUMER_KEY,CONSUMER_SECRET);
    consumer.setTokenWithSecret(ACCESS_TOKEN,TOKEN_SECRET);

    //Create a HTTP request
    String Status="testing 123 123";
    PercentEscaper percentEscaper = new PercentEscaper("",false);
    HttpPost request = new HttpPost("https://api.twitter.com/1.1/statuses/update.json?status=" + percentEscaper.escape(Status));

    //sign req
    consumer.sign(request);
    System.out.println("Http Request Header:");
    Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

    //Send req
    HttpClient httpClient= HttpClientBuilder.create().build();
    HttpResponse httpResponse= httpClient.execute(request);
    System.out.println(EntityUtils.toString(httpResponse.getEntity()));
  }



}
