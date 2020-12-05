package edu.cnm.deepdive.picmegallery.service;

import android.content.Context;
import edu.cnm.deepdive.picmegallery.model.Event;
import edu.cnm.deepdive.picmegallery.model.User;
import io.reactivex.Single;


public class EventRepository {

  private Context context;
  private WebService webService;

  public EventRepository(Context context) {
    this.context = context;
    webService = WebService.getInstance();
  }

  //TODO check this method with Nick
  public Single<Event> getEvent(long id, String Passkey) {
    return webService.getEvent(id, Passkey);
  }

}
