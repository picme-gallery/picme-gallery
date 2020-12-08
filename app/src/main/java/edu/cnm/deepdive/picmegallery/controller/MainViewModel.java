package edu.cnm.deepdive.picmegallery.controller;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import edu.cnm.deepdive.picmegallery.model.Event;
import edu.cnm.deepdive.picmegallery.service.EventRepository;
import io.reactivex.disposables.CompositeDisposable;

/**
 * This class is where all the data is stored.
 */
public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final EventRepository eventRepository;
  //TODO Add more repositories as needed.
  private final MutableLiveData<Event> event;
  private final MutableLiveData<Throwable> throwable;
  //TODO Add more live data fields as needed.
  private final CompositeDisposable pending;


  public MainViewModel(
      @NonNull Application application) {
    super(application);
    eventRepository = new EventRepository(application);
    //TODO initialize additional repositories as needed.
    event = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    //TODO initialize additional livedata fields as needed.
    pending = new CompositeDisposable();
    //TODO perform any additional initialization of the viewModel.
  }

  /**
   * This is the getter for event.
   */
  public LiveData<Event> getEvent() {
    return event;
  }

  /**
   * THis is the getter for throwable.
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  //TODO generate additional getters as needed.

  /**
   * This method is used yo fetch a single event.
   * @param id is the id of a specific event.
   * @param passkey is the string password to get into an event.
   */
  public void fetchEvent(long id, String passkey){
    pending.add(
        eventRepository.getEvent(id, passkey)
            .subscribe(
                event::postValue,
                throwable::postValue
            )
    );
  }

  /**
   * This method is used to to fetch a single event that the user has created.
   * @param id is an id that corresponds to a specific event.
   */
  public void fetchOwnEvent(long id){
    pending.add(
        eventRepository.getOwnEvent(id)
            .subscribe(
                event::postValue, //If the task is a completable then the form is () -> {}
                throwable::postValue
            )
    );
  }

  /**
   * This method is usd to create an event.
   */
  public void createEvent(Event event){
    pending.add(
        eventRepository.createEvent(event)
        .subscribe(
            () -> {},
            throwable::postValue
        )
    );
  }


  //TODO public methods that will be invoked by the controller to update the viewModel

  @OnLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}
