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

  /**
   * A user associated with an Event.
   */
  @Expose
  private User user;

  /**
   * The name of an event
   */
  @Expose
  private String name;

  /**
   * The time which an event was created at
   */
  @Expose
  private String address;

  @Expose
  private Date time;

  /**
   * The date which an event was updated
   */
  @Expose
  private Date updated;

  /**
   * The text description of an event
   */
  @Expose
  private String description;

  /**
   * The passkey required for a user to get access to an event.
   */
  @Expose
  private String passkey;

  /**
   * An optional longitude double, which helps us locate where an event is taking place
   */
  @Expose
  private Double longitude;

  /**
   * An optional longitude double, which helps us locate where an event is taking place
   */
  @Expose
  private Double latitude;

  /**
   * A list of all the users that are a part of an event!
   */
  @Expose
  private List<User> users;

  /**
   * A list of photos that were uploaded to an event.
   */
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
