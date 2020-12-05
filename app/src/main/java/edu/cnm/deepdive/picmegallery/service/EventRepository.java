package edu.cnm.deepdive.picmegallery.service;

import android.content.Context;
import edu.cnm.deepdive.picmegallery.model.Event;
import io.reactivex.Single;


public class EventRepository {

  private Context context;
  private WebService webService;

  public EventRepository(Context context) {
    this.context = context;
    webService = WebService.getInstance();
  }



}
