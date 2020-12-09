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
import java.io.IOException;
import java.util.Objects;

public class GalleryFragment extends Fragment {

  private GalleryViewModel galleryViewModel;
  ImageView mImageview;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    galleryViewModel = new ViewModelProvider(this).get(GalleryViewModel.class);
    View root = inflater.inflate(R.layout.fragment_gallery, container, false);
    mImageview = (ImageView) root.findViewById(R.id.image_view_gal);

    root.findViewById(R.id.button_get_gal).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
  Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        int permissionCheck = ContextCompat.checkSelfPermission(
            Objects.requireNonNull(getActivity()),
            Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
          startGallery();
        } else {
          ActivityCompat.requestPermissions(getActivity(),
              new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
              2000);
        }
      }
    });


    return root;
  }


  private void startGallery() {
    Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
    cameraIntent.setType("image/*");
    if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
      startActivityForResult(cameraIntent, 1000);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    //super method removed
    if(resultCode == RESULT_OK) {
      if(requestCode == 1000){
        Uri returnUri = data.getData();
        Bitmap bitmapImage = null;
        try {
          bitmapImage = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), returnUri);
        } catch (IOException e) {
          e.printStackTrace();
        }
        mImageview.setImageBitmap(bitmapImage);
      }
    }
    //Uri returnUri;
    //returnUri = data.getData();

//    Glide.with(this)
//        .load(returnUri)
//        .override(1280, 1280)
//        .centerCrop()
//        .crossFade()
//        .into(mImageview);
  }
}