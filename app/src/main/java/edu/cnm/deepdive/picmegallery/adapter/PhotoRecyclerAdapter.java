package edu.cnm.deepdive.picmegallery.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.picmegallery.adapter.PhotoRecyclerAdapter.Holder;
import edu.cnm.deepdive.picmegallery.databinding.FragmentEventPhotosBinding;
import edu.cnm.deepdive.picmegallery.databinding.ItemEventPhotoBinding;
import edu.cnm.deepdive.picmegallery.model.Photo;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class PhotoRecyclerAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final List<Photo> photos;
  private final LayoutInflater inflater;
  private final Picasso picasso;

  public PhotoRecyclerAdapter(Context context, Picasso picasso) {
    this.context = context;
    this.picasso = picasso;
    photos = new ArrayList<Photo>();
    inflater = LayoutInflater.from(context);
  }

  public List<Photo> getPhotos() {
    return photos;
  }

  @NonNull
  @NotNull
  @Override
  public Holder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    ItemEventPhotoBinding binding = ItemEventPhotoBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return photos.size();
  }



  class Holder extends RecyclerView.ViewHolder {

    private final ItemEventPhotoBinding binding;

    private Holder(@NonNull ItemEventPhotoBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      }

    private void bind(int position) {
      Photo photo = photos.get(position);
      String url = photo.getHref() + "/content";
      picasso.load(url).into(binding.imageView);
    }
  }
}
