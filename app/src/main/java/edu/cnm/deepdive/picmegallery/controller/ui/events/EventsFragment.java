package edu.cnm.deepdive.picmegallery.controller.ui.events;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.picmegallery.R;
import edu.cnm.deepdive.picmegallery.adapter.EventAdapter;
import edu.cnm.deepdive.picmegallery.controller.MainActivity;
import edu.cnm.deepdive.picmegallery.controller.MainViewModel;
import edu.cnm.deepdive.picmegallery.controller.ui.defineEvent.DefineEventFragment;
import edu.cnm.deepdive.picmegallery.databinding.FragmentEventsBinding;
import edu.cnm.deepdive.picmegallery.model.Event;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class EventsFragment extends Fragment {

  private FragmentEventsBinding binding;
  private MainViewModel viewModel;
  private EventAdapter adapter;
  private List<Event> events;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentEventsBinding.inflate(inflater);
    binding.addEventButton.setOnClickListener((v) -> {
      DefineEventFragment fragment = new DefineEventFragment();
      getParentFragmentManager().beginTransaction()
          .replace(R.id.event_fragment_container, fragment)
          .commit();
    });
    // THe stuff down bellow allows us to display multiple variations of a fragment.
//    Variation variation = MatchFragmentArgs.fromBundle(getArguments()).getVariation();
//    binding.placeholder.setText(variation.toString());
    // Access references in binding to set contents of view objects, as appropriate.

    //EventsFragmentDirections.actionNavHomeToDefineEventFragment()
    events = new ArrayList<>();
    adapter = new EventAdapter(getContext(), events);
    binding.eventList.setAdapter(adapter);
    binding.eventList.setOnItemClickListener((parent, view, position, id) -> {
      Intent intent = new Intent(getContext(), MainActivity.class);
      Event event = (Event) parent.getItemAtPosition(position);
      intent.putExtra(MainActivity.EVENT_ID_KEY, event.getExternalId());
      intent.putExtra(MainActivity.EVENT_PASSKEY_KEY, event.getPasskey());
      startActivity(intent);
//      viewModel.setEvent((Event) parent.getItemAtPosition(position));
//      Navigation.findNavController(binding.getRoot()).navigate(EventsFragmentDirections.showEventPhotos());
    });

    binding.joinEventButton.setOnClickListener((v) -> {
      long eventKey;
      String eventPasskey = binding.eventPasskey.toString().trim();
      eventKey = Long.parseLong(binding.eventKey.getText().toString().trim());
      viewModel.fetchEvent(eventKey, eventPasskey);

//      String eventName;
//      String eventPasskey = binding.eventPasskey.getText().toString().trim();
//      eventName = binding.eventKey.getText().toString().trim();
//      viewModel.getEventByName(eventName,eventPasskey);
    });
    return binding.getRoot();
  }


  @Override
  public void onViewCreated(@NonNull @NotNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    viewModel.getEvents().observe(getViewLifecycleOwner(), (events) -> {
      this.events.clear();
      this.events.addAll(events);
      adapter.notifyDataSetChanged();
    });
    viewModel.getEvent().observe(getViewLifecycleOwner(), (event) -> {
      events.add(event);
      events.sort(null);
      adapter.notifyDataSetChanged();
    });
  }
}
