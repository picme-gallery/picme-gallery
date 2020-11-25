package edu.cnm.deepdive.picmegallery.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.picmegallery.R;

public class GalleryFragment extends Fragment {

  private GalleryViewModel dashboardViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    dashboardViewModel =
        new ViewModelProvider(this).get(GalleryViewModel.class);
    View root = inflater.inflate(R.layout.fragment_gallery, container, false);
    final TextView textView = root.findViewById(R.id.nav_gallery);
    dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });
    return root;
  }
}