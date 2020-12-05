package edu.cnm.deepdive.picmegallery.service;

import android.content.Context;
import edu.cnm.deepdive.picmegallery.model.Event;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;


public class EventRepository {

  private final Context context;
  private  final WebServiceProxy webService;
  private final GoogleSignInService signInService;

  public EventRepository(Context context) {
    this.context = context;
    webService = WebServiceProxy.getInstance();
    signInService = GoogleSignInService.getInstance();
  }

  //TODO check this method with Nick
  public Single<Event> getEvent(long id, String passkey) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((token) -> webService.getEvent(token, passkey, id));
  }

  public Single<Event> getOwnEvent(long id) {
    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((token) -> webService.getOwnEvent(token, id));
  }

}
