package edu.cnm.deepdive.picmegallery.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Photo {

  @Expose
  @SerializedName("id")
  private long externalId;

  @Expose
  private String event;

  @Expose
  private String user;

  @Expose
  private Double latitude;

  @Expose
  private Double longitude;

  @Expose
  private String caption;

  @Expose
  private Date uploaded;

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

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
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
}
