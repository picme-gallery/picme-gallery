package edu.cnm.deepdive.picmegallery.controller.ui.eventsPhoto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import edu.cnm.deepdive.picmegallery.databinding.FragmentEventPhotosBinding;
import org.jetbrains.annotations.NotNull;

public class EventPhotoFragment extends Fragment {

 private FragmentEventPhotosBinding binding;




  @Nullable
  @Override
  public View onCreateView( LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentEventPhotosBinding.inflate(inflater);
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


  }

}
