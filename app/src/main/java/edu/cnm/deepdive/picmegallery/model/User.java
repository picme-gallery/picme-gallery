package edu.cnm.deepdive.picmegallery.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//TODO Make any additions to turn this into an Entity class if appropriate.
public class User {

  @Expose
  @SerializedName("id")
  private long externalId;

  @Expose
  private String displayName;

  /**
   *
   * @return the externalId or id of the user.
   */
  public long getExternalId() {
    return externalId;
  }

  /**
   *
   * @param externalId , a long, which represents the id of the User.
   */
  public void setExternalId(long externalId) {
    this.externalId = externalId;
  }

  /**
   *
   * @return , a String, which represents the user's display name within the application
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   *  A setter used to change the user's display name
   * @param displayName , a String, which represents the User's display name.
   *
   */
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
