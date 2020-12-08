package edu.cnm.deepdive.picmegallery.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.List;

/**
 * This is an entity class that represents the info we store on the client-side for an event
 */
public class Event implements Comparable<Event>{

  /**
   * The
   */
  @SerializedName("id")
  private long externalId;

  @Expose
  private User user;

  @Expose
  private String name;

  @Expose
  private String address;

  @Expose
  private Date time;

  @Expose
  private Date updated;

  @Expose
  private String description;

  @Expose
  private String passkey;

  @Expose
  private Double longitude;

  @Expose
  private Double latitude;

  @Expose
  private List<User> users;

  @Expose
  private List<Photo> photos;

  public long getExternalId() {
    return externalId;
  }

  public void setExternalId(long externalId) {
    this.externalId = externalId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPasskey() {
    return passkey;
  }

  public void setPasskey(String passkey) {
    this.passkey = passkey;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public List<Photo> getPhotos() {
    return photos;
  }

  public void setPhotos(List<Photo> photos) {
    this.photos = photos;
  }

  @Override
  public int compareTo(Event other) {
    return other.time.compareTo(time);
  }
}
