package edu.cnm.deepdive.picmegallery.controller.ui.eventsPhoto;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import edu.cnm.deepdive.picmegallery.BuildConfig;
import edu.cnm.deepdive.picmegallery.R;
import edu.cnm.deepdive.picmegallery.adapter.PhotoRecyclerAdapter;
import edu.cnm.deepdive.picmegallery.controller.MainViewModel;
import edu.cnm.deepdive.picmegallery.databinding.FragmentEventPhotosBinding;
import edu.cnm.deepdive.picmegallery.service.GoogleSignInService;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import org.jetbrains.annotations.NotNull;

public class EventPhotoFragment extends Fragment {

  private FragmentEventPhotosBinding binding;
  private MainViewModel viewModel;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentEventPhotosBinding.inflate(inflater);
    Context context = getContext();
    int span = (int) Math.floor(context.getResources().getDisplayMetrics().widthPixels / context.getResources().getDimension(
        R.dimen.gallery_item_width));
    binding.eventPhotos.setLayoutManager(new GridLayoutManager(context, span));
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
    viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    viewModel.getEvent().observe(getViewLifecycleOwner(), (event) -> {
      Picasso picasso  = buildPicasso(event.getPasskey());
      viewModel.getPhotos().observe(getViewLifecycleOwner(), (photos) -> {
        PhotoRecyclerAdapter adapter = new PhotoRecyclerAdapter(getContext(), picasso);
        adapter.getPhotos().addAll(photos);
        binding.eventPhotos.setAdapter(adapter);
      });
      viewModel.loadPhotos(event);
    });
  }

  private Picasso buildPicasso(String passkey) {
    String token = "Bearer " + GoogleSignInService.getInstance().getAccount().getIdToken();//FIXME
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(BuildConfig.DEBUG ? Level.HEADERS : Level.NONE);
    OkHttpClient client = new OkHttpClient.Builder()
        .addInterceptor((chain) -> {
              Request request = chain.request().newBuilder()
                  .addHeader("Authorization", token)
                  .addHeader("Passkey", passkey)
                  .build();
              return chain.proceed(request);
            }
        )
        .addInterceptor(interceptor)
        .build();
    return new Picasso.Builder(getContext())
        .downloader(new OkHttp3Downloader(client))
        .loggingEnabled(BuildConfig.DEBUG)
        .build();
  }
}
