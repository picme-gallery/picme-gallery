package edu.cnm.deepdive.picmegallery.controller.ui.defineEvent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.picmegallery.R;
import edu.cnm.deepdive.picmegallery.controller.MainViewModel;
import edu.cnm.deepdive.picmegallery.databinding.FragmentDefineEventBinding;
import org.jetbrains.annotations.NotNull;

public class DefineEventFragment extends Fragment {

  private FragmentDefineEventBinding binding;
  private MainViewModel viewModel;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater,
      @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    binding = FragmentDefineEventBinding.inflate(inflater);
    binding.createEventButton.setOnClickListener((v) -> {


    });
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);
  }
}


