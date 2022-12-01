package ca.jrvs.apps.twitter.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonIgnoreProperties
@JsonInclude
public class Entities {
  private List<HashTags> hashtags;
  private List<UserMention> userMentions;

  public List<HashTags> getHashTags() {
    return hashtags;
  }

  public void setHashtags(List<HashTags> hashtags) {
    this.hashtags = hashtags;
  }

  public List<UserMention> getUserMentions() {
    return userMentions;
  }

  public void setUserMention(List<UserMention> userMentions) {
    this.userMentions = userMentions;
  }
}
