package edu.cnm.deepdive.picmegallery.ui.events;

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

public class EventsFragment extends Fragment {

  private EventViewModel eventViewModel;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    eventViewModel =
       new ViewModelProvider(this).get(EventViewModel.class);
    View root = inflater.inflate(R.layout.fragment_events, container, false);
    final TextView textView = root.findViewById(R.id.nav_events);
    eventViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        textView.setText(s);
      }
    });
    return root;
  }
}