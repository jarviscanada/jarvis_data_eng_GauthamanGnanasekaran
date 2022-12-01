package ca.jrvs.apps.twitter.dao;
import ca.jrvs.apps.twitter.utils.JsonParser;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import ca.jrvs.apps.twitter.model.Tweet;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;

@Repository
public class TwitterDao implements CrdDao<Tweet, String>{
  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy";

  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  private static final int HTTP_OK = 200;
  private HttpHelper httpHelper;

  @Autowired
  public TwitterDao(HttpHelper httpHelper){
    this.httpHelper = httpHelper;
  }

  /**
   * @param tweet tweet entity that will be passed in
   * @return
   */
  @Override
  public Tweet create(Tweet tweet) {
    URI uri;
    try{
      uri = getPostUri(tweet);
    }catch(URISyntaxException e){
      throw new IllegalArgumentException("Invalid tweet input");
    }
    HttpResponse response = httpHelper.httpPost(uri);
    return parseResponseBody(response,HTTP_OK);
  }


  /**
   * @param  id of the entity
   * @return
   */
  @Override
  public Tweet findById(String id) {
    URI uri;
    try{
      String uri_str = API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + id;
      uri = new URI(uri_str);
    } catch(URISyntaxException e){
      throw new IllegalArgumentException("Tweet ID is not valid " + e);
    }
    HttpResponse response= httpHelper.httpGet(uri);
    System.out.println(uri);
    return parseResponseBody(response, HTTP_OK);
  }

  /**
   * @param  id to delete
   * @return
   */
  @Override
  public Tweet deleteById(String id) {
    URI uri;
    try{
      String uri_str = API_BASE_URI + DELETE_PATH + "/" + id + ".json";
      uri = new URI(uri_str);
    } catch (URISyntaxException e){
      throw new RuntimeException("Error constructing URI", e);
    }
    HttpResponse response = httpHelper.httpPost(uri);
    return parseResponseBody(response, HTTP_OK);
  }

  public Tweet parseResponseBody(HttpResponse response, int expectedStatusCode){
    Tweet tweet;
    //Check response status
    int status = response.getStatusLine().getStatusCode();
    if(status != expectedStatusCode){
      try{
        System.out.println(EntityUtils.toString(response.getEntity()));
      }catch(IOException e){
        System.out.println("Response has no entity");
      }
      throw new RuntimeException("Unexpected HTTP status:" + status);
    }
    if(response.getEntity() == null){
    throw new RuntimeException("Empty Response body");
    }


    //Convert Response Entity to String
    String jsonStr;
    try{
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e){
      throw new RuntimeException("failed to convert entity to string", e);
    }

    //Deserialize JSON String to Tweet object
    try{
      tweet = JsonParser.toObjectFromJson(jsonStr,Tweet.class);
    } catch(IOException e){
      throw new RuntimeException("Unable to convert JSON str to Object, e");
    }
    return tweet;
  }

  private URI getPostUri(Tweet tweet) throws URISyntaxException{
    String text = tweet.getText();
    PercentEscaper percentEscaper = new PercentEscaper("",false);
    String uri_text = API_BASE_URI+POST_PATH+QUERY_SYM+"status"+EQUAL+percentEscaper.escape(text);

    if(tweet.getCoordinates()!=null){
      uri_text = uri_text + AMPERSAND+ "long" + EQUAL+ percentEscaper.escape(String.valueOf(tweet.getCoordinates().getCoordinates()[0]))  +
          AMPERSAND + "lat" + EQUAL + percentEscaper.escape(String.valueOf(tweet.getCoordinates().getCoordinates()[1]));
    }
    return new URI(uri_text);
  }

}
