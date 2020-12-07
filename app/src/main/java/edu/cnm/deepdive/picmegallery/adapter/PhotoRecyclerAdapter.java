package edu.cnm.deepdive.picmegallery.adapter;

import android.content.Context;
import android.provider.ContactsContract.Contacts.Photo;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.picmegallery.adapter.PhotoRecyclerAdapter.Holder;
import edu.cnm.deepdive.picmegallery.databinding.FragmentEventPhotosBinding;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class PhotoRecyclerAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final List<Photo> photos;
  private final LayoutInflater inflater;

  public PhotoRecyclerAdapter(Context context, LayoutInflater inflater) {
    this.context = context;
    photos = new ArrayList<Photo>();
    this.inflater = inflater;
  }

  public List<Photo> getPhotos() {
    return photos;
  }

  @NonNull
  @NotNull
  @Override
  public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    FragmentEventPhotosBinding binding = FragmentEventPhotosBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }



  class Holder extends RecyclerView.ViewHolder {

    private final FragmentEventPhotosBinding binding;

    private Holder(@NonNull FragmentEventPhotosBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }


  }
}
