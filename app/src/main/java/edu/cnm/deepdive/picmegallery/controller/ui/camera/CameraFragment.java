package edu.cnm.deepdive.picmegallery.controller.ui.camera;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.picmegallery.R;
import edu.cnm.deepdive.picmegallery.databinding.FragmentCameraBinding;
import java.util.ArrayList;
import java.util.HashMap;

public class CameraFragment extends Fragment {
  public static final String EXTRA_INFO = "default";
  private Button btnCapture;
  private ImageView imgCapture;
  private static final int Image_Capture_Code = 1;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_camera, container, false);
    btnCapture =(Button) view.findViewById(R.id.captureButton);

    btnCapture.setOnClickListener(v -> {
      Intent cInt = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      startActivityForResult(cInt,Image_Capture_Code);
    });

    return view;
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == Image_Capture_Code) {
      if (resultCode == RESULT_OK) {
        Bitmap bp = (Bitmap) data.getExtras().get("data");
        imgCapture.setImageBitmap(bp);
      } else if (resultCode == RESULT_CANCELED) {
        Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_LONG).show();
      }
    }
  }}