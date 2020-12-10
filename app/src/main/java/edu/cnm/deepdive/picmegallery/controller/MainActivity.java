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

  private UserRepository userRepository; //FIXME
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
        R.id.navigation_events, R.id.navigation_gallery, R.id.navigation_camera, R.id.event_photos, R.id.define_event)
        .build();
    NavController navController = ((NavHostFragment) getSupportFragmentManager()
        .findFragmentById(R.id.nav_host_fragment)).getNavController();
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    NavigationUI.setupWithNavController(navView, navController);
    //FIX ME This is just temporary to verify round trip.
    userRepository = new UserRepository(this);
    userRepository.getProfileFromServer()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            (user) -> Toast.makeText(this, user.getDisplayName(), Toast.LENGTH_LONG).show(),
            (throwable) -> Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG ).show()
        );
    MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    getLifecycle().addObserver(mainViewModel);
    mainViewModel.getThrowable().observe(this, (throwable) -> {
      if (throwable != null) {
        Snackbar.make(binding.getRoot(), throwable.getMessage(), Snackbar.LENGTH_LONG).show();
      }
    });
  }

}