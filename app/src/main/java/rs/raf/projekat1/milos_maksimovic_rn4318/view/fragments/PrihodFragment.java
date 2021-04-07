package rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.PrihodViewModel;

public class PrihodFragment extends Fragment {

    private PrihodViewModel prihodViewModel;

    public PrihodFragment() {
        super(R.layout.fragment_prihodi);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prihodViewModel = new ViewModelProvider(requireActivity()).get(PrihodViewModel.class);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initObservers();
        initRecycler();
    }

    private void initView(View view) {
    }

    private void initObservers() {
    }

    private void initRecycler() {
    }
}
