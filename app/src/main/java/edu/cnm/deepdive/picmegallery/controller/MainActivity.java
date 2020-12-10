package edu.cnm.deepdive.picmegallery.controller;

import android.os.Bundle;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.snackbar.Snackbar;
import edu.cnm.deepdive.picmegallery.R;
import edu.cnm.deepdive.picmegallery.databinding.ActivityMainBinding;
import edu.cnm.deepdive.picmegallery.service.UserRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Thi main activity class is what sets up all the fragments in the ui.
 */
public class MainActivity extends AppCompatActivity {

  public static final String EVENT_ID_KEY = "event_id";
  public static final String EVENT_PASSKEY_KEY = "event_passkey";

  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    BottomNavigationView navView = binding.navView;
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        R.id.navigation_gallery, R.id.navigation_camera, R.id.event_photos)
        .build();
    NavController navController = ((NavHostFragment) getSupportFragmentManager()
        .findFragmentById(R.id.nav_host_fragment)).getNavController();
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    NavigationUI.setupWithNavController(navView, navController);
    MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    getLifecycle().addObserver(mainViewModel);
    long eventId = getIntent().getLongExtra(EVENT_ID_KEY, 0);
    String passkey = getIntent().getStringExtra(EVENT_PASSKEY_KEY);
    mainViewModel.fetchEvent(eventId, passkey);
    mainViewModel.getThrowable().observe(this, (throwable) -> {
      if (throwable != null) {
        Snackbar.make(binding.getRoot(), throwable.getMessage(), Snackbar.LENGTH_LONG).show();
      }
    });
  }

}