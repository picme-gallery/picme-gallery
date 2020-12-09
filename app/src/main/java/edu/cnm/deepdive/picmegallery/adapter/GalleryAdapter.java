package edu.cnm.deepdive.picmegallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.picmegallery.BuildConfig;
import edu.cnm.deepdive.picmegallery.databinding.ItemGalleryBinding;
import edu.cnm.deepdive.picmegallery.model.Photo;
import edu.cnm.deepdive.picmegallery.adapter.GalleryAdapter.Holder;
import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final LayoutInflater inflater;
  private final List<Photo> photos;

  public GalleryAdapter(Context context) {
    this.context = context;
    inflater = LayoutInflater.from(context);
    photos = new ArrayList<>();
  }

  public List<Photo> getPhotos() {
    return photos;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemGalleryBinding binding = ItemGalleryBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(photos.get(position));
  }

  @Override
  public int getItemCount() {
    return photos.size();
  }

  static class Holder extends RecyclerView.ViewHolder {

    private final ItemGalleryBinding binding;

    private Holder(ItemGalleryBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(Photo photo) {
      Picasso.get()
          .load(String.format(BuildConfig.CONTENT_FORMAT, photo.getHref()))
          .into(binding.photo);
      String title = photo.getTitle();
      String filename = photo.getName();
      String description = photo.getDescription();
      binding.title.setText((title != null) ? title : filename);
      if (description != null) {
        binding.photo.setContentDescription(description);
        binding.photo.setTooltipText(description);
      }
    }

  }

}
