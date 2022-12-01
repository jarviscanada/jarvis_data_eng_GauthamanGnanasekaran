package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

public interface HttpHelper {

  /**
   * HTTP post call
   * @param uri
   * @return
   */
  HttpResponse httpPost(URI uri);

  /**
   * Http Get call
   * @param uri
   * @return
   */
  HttpResponse httpGet(URI uri);
}
