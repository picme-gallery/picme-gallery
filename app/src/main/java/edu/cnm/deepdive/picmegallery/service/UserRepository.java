package edu.cnm.deepdive.picmegallery.service;

import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.picmegallery.model.User;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class UserRepository {

  private final Context context;
  private final WebService webService;
  private final GoogleSignInService signInService;
  //TODO Add fields as appropriate for access to DAOs etc.

  public UserRepository(Context context) {
    this.context = context;
    webService = WebService.getInstance();
    signInService = GoogleSignInService.getInstance();
  }

  public Single<User> getProfileFromServer() {
    return  signInService.refresh()
        .observeOn(Schedulers.io())
        .flatMap((account) -> webService.getProfile(getBearerToken(account)));
    //TODO Add additional logic for persistence if appropriate
  }

  private String getBearerToken(GoogleSignInAccount account) {
    return String.format("Bearer %s", account.getIdToken());
  }
}
