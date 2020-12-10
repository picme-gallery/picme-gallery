package edu.cnm.deepdive.picmegallery.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.snackbar.Snackbar;
import edu.cnm.deepdive.picmegallery.R;
import edu.cnm.deepdive.picmegallery.databinding.ActivityEventBinding;

public class EventActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityEventBinding binding = ActivityEventBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.getThrowable().observe(this, (throwable) -> {
      if (throwable != null) {
        Snackbar.make(binding.getRoot(), throwable.getMessage(), Snackbar.LENGTH_INDEFINITE).show();
      }
    });
  }
}