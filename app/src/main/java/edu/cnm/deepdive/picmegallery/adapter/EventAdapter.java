package edu.cnm.deepdive.picmegallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.picmegallery.R;
import edu.cnm.deepdive.picmegallery.databinding.ItemEventNameBinding;
import edu.cnm.deepdive.picmegallery.model.Event;
import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

  private final List<Event> events;
  private final LayoutInflater inflater;

  private ItemEventNameBinding binding;

  public EventAdapter(@NonNull Context context) {
    super(context, R.layout.item_event_name);
    events = new ArrayList<>();
    inflater = LayoutInflater.from(context);
  }

  public List<Event> getEvents() {
    return events;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    binding = (convertView != null)
        ? ItemEventNameBinding.bind(convertView)
        : ItemEventNameBinding.inflate(inflater, parent, false);
    Event event = events.get(position);
    binding.name.setText(event.getName());
    return binding.getRoot();
  }
}
