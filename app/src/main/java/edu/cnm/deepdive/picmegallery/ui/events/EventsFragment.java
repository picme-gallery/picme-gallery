package edu.cnm.deepdive.picmegallery.ui.events;

import android.os.Bundle;
import android.text.Html;
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
import edu.cnm.deepdive.picmegallery.databinding.FragmentEventsBinding;
import edu.cnm.deepdive.picmegallery.ui.camera.CameraViewModel;
import org.jetbrains.annotations.NotNull;

public class EventsFragment extends Fragment {

private FragmentEventsBinding binding;
private EventViewModel viewModel;



  @Nullable
  @Override
  public View onCreateView( LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentEventsBinding.inflate(inflater);
    // THe stuff down bellow allows us to display multiple variations of a fragment.
//    Variation variation = MatchFragmentArgs.fromBundle(getArguments()).getVariation();
//    binding.placeholder.setText(variation.toString());
    // Access references in binding to set contents of view objects, as appropriate.
    return binding.getRoot();
  }


  @Override
  public void onViewCreated(@NonNull @NotNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity()).get(EventViewModel.class);

    }
    // Get reference to view model and set observers on live data.
  }
