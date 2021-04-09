package rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;

public class RashodIzmenaFragment extends Fragment {

    public RashodIzmenaFragment() {
        super(R.layout.fragment_rashod_izmene);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        initFragment();
    }

    private void initFragment() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.izmenaFinansijaRashodFragment, new RashodFragment());
        transaction.commit();
    }
}
