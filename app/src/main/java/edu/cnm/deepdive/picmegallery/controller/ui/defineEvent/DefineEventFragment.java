package edu.cnm.deepdive.picmegallery.controller.ui.defineEvent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.picmegallery.R;
import edu.cnm.deepdive.picmegallery.controller.MainViewModel;
import edu.cnm.deepdive.picmegallery.controller.ui.events.EventsFragment;
import edu.cnm.deepdive.picmegallery.databinding.FragmentDefineEventBinding;
import edu.cnm.deepdive.picmegallery.model.Event;
import org.jetbrains.annotations.NotNull;

/**
 * This class is a fragment and where the layouts are inflated using binding along with any button logic.
 */
public class DefineEventFragment extends Fragment {

  private FragmentDefineEventBinding binding;
  private MainViewModel viewModel;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentDefineEventBinding.inflate(inflater);
    binding.createEventButton.setOnClickListener((v) -> {
      String eventName = binding.eventName.getText().toString().trim();
      String eventAddress = binding.eventAddress.getText().toString().trim();
      String eventDescription = binding.eventDescription.getText().toString().trim();
      String passkey = binding.eventPasskey.getText().toString().trim();
      Event event = new Event();
      event.setName(eventName);
      event.setAddress(eventAddress);
      event.setDescription(eventDescription);
      event.setPasskey(passkey);
      viewModel.createEvent(event);
    });


    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    viewModel.getEvent().observe(getViewLifecycleOwner(), (event) -> {
      EventsFragment fragment = new EventsFragment();
      getParentFragmentManager().beginTransaction()
          .replace(R.id.event_fragment_container, fragment)
          .commit();
    });
  }
}


