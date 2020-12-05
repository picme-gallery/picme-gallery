package edu.cnm.deepdive.picmegallery.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import edu.cnm.deepdive.picmegallery.databinding.FragmentCameraBinding;
import edu.cnm.deepdive.picmegallery.databinding.FragmentEventPhotosBinding;

public class CameraFragment extends Fragment {

  FragmentCameraBinding binding;


  public static EventFragment createInstance(/* parameters to pass to fragment*/) {
    EventFragment fragment = new EventFragment();
    Bundle args = new Bundle();
    // Add parameter values to args, using args.put???().
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    // DO whatever necessary with args.
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    binding = FragmentCameraBinding.inflate(inflater);

    //Access references in binding to set contents of view objects, as appropriate

    //The stuff down below allows us to display multiple variations of a fragment, without having to
    //use extra resources. We can just use the same layout. But we need to make the additional fragments
    // a part of our navigation screen through adding them to the mobile_navigation.xml set up.
    //In codebreaker, we also added items to the activity_main_drawer, in PicMe we won't use this functionality for now.
    //We had to then add tall the fragments to our setUpNavigation method in our Navigation Activity Class in the controller


    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable  Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    //Get references to a ViewModel instance, set observers on LiveData.
  }





}
