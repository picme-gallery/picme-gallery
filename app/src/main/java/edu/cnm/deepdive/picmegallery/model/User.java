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

  public long getExternalId() {
    return externalId;
  }

  public void setExternalId(long externalId) {
    this.externalId = externalId;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
