package edu.cnm.deepdive.picmegallery.service;

import android.content.Context;
import edu.cnm.deepdive.picmegallery.model.Event;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * This is the repository for an event.
 * This is the class holds the logic of the quarries to the server side.
 */
public class EventRepository {

  private final Context context;
  private  final WebServiceProxy webService;
  private final GoogleSignInService signInService;

  /**
   * This constructor is where the fields are assigned a value.
   * @param context
   */
  public EventRepository(Context context) {
    this.context = context;
    webService = WebServiceProxy.getInstance();
    signInService = GoogleSignInService.getInstance();
  }

  /**
   * This method is used to get an a single event back from the server side.
   * @param id is the identifier for a specific event.
   * @param passkey is the password to gain access to an event.
   * @return an event.
   */
  public Single<Event> getEvent(long id, String passkey) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((token) -> webService.getEvent(token, passkey, id));
  }

  /**
   * This method is used to get an event that the user has created.
   * @param id is the identifier for a specific event.
   * @return an event that a user has created.
   */
  public Single<Event> getOwnEvent(long id) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((token) -> webService.getOwnEvent(token, id));
  }

  /**
   * This is the method used to post an event to the server side.
   * @param event is a reference to a event object.
   * @return a response code.
   */
  public Single<Event> createEvent(Event event) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((token) -> webService.createEvent(token, event));
  }

}
