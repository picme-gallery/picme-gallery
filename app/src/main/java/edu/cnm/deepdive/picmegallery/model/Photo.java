package edu.cnm.deepdive.picmegallery.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 * The photo entity class, which contains all the data regarding photos which can be posted to an event.
 */
public class Photo {

  @Expose
  @SerializedName("id")
  private long externalId;


  @Expose
  private String event;

  @Expose
  private User user;

  /**
   * The latitude of where a photo is taken
   */
  @Expose
  private Double latitude;

  /**
   *  The longitude of where a photo is taken
   */
  @Expose
  private Double longitude;

  /**
   *  A string of captions associated with a photo
   */
  @Expose
  private String caption;

  /**
   * A date timestamp of when a photo is taken
   */
  @Expose
  private Date uploaded;

  @Expose
  private String href;

  public long getExternalId() {
    return externalId;
  }

  public void setExternalId(long externalId) {
    this.externalId = externalId;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public String getCaption() {
    return caption;
  }

  public void setCaption(String caption) {
    this.caption = caption;
  }

  public Date getUploaded() {
    return uploaded;
  }

  public void setUploaded(Date uploaded) {
    this.uploaded = uploaded;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }
}
