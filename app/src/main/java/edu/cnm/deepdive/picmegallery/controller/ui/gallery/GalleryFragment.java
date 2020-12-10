package edu.cnm.deepdive.picmegallery.controller.ui.gallery;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.picmegallery.R;
import edu.cnm.deepdive.picmegallery.controller.MainViewModel;
import edu.cnm.deepdive.picmegallery.databinding.FragmentGalleryBinding;
import java.io.IOException;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public class GalleryFragment extends Fragment {

  public static final int PICK_IMAGE_REQUEST = 1000;
  public static final int PERMISSIONS_REQUEST = 2000;

  private MainViewModel viewModel;
  private FragmentGalleryBinding binding;
  private Uri imageUri;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentGalleryBinding.inflate(inflater, container, false);
    binding.buttonGetGal.setOnClickListener(view -> pickImage());
    binding.imageViewGal.setOnClickListener((v) -> viewModel.savePhoto(imageUri));
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view,
      @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    //noinspection ConstantConditions
    viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    viewModel.getImageUri().observe(
        getViewLifecycleOwner(), (imageUri) -> {
          this.imageUri = imageUri;
          binding.imageViewGal.setImageURI(imageUri);
        });
  }

  private void pickImage() {
    //noinspection ConstantConditions
    int permissionCheck = ContextCompat.checkSelfPermission(
        getActivity(),
        Manifest.permission.READ_EXTERNAL_STORAGE);

    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
      startGallery();
    } else {
      ActivityCompat.requestPermissions(getActivity(),
          new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
          PERMISSIONS_REQUEST);
    }
  }


  private void startGallery() {
    Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
    cameraIntent.setType("image/*");
    if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
      startActivityForResult(cameraIntent, PICK_IMAGE_REQUEST);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    //super method removed
    if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST && data != null) {
        viewModel.setImageUri(data.getData());

    }

//    Glide.with(this)
//        .load(returnUri)
//        .override(1280, 1280)
//        .centerCrop()
//        .crossFade()
//        .into(mImageview);
  }
}