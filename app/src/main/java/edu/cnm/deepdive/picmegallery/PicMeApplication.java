package edu.cnm.deepdive.picmegallery;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.picmegallery.service.GoogleSignInService;

public class PicMeApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    GoogleSignInService.setContext(this);

  }
}
