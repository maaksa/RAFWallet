package rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.activities.IzmenaFinansijeActivity;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.recycler.adapter.PrihodAdapter;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.recycler.adapter.RashodAdapter;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.recycler.differ.PrihodDiffItemCallback;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.recycler.differ.RashodDiffItemCallback;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.PrihodViewModel;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.RashodViewModel;

public class RashodFragment extends Fragment {

    private RashodViewModel rashodViewModel;
    private RashodAdapter rashodAdapter;
    private RecyclerView recyclerView;
    private Rashod rashodToSend;

    public RashodFragment() {
        super(R.layout.fragment_rashodi);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rashodViewModel = new ViewModelProvider(requireActivity()).get(RashodViewModel.class);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initObservers();
        initRecycler();
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.listRvRashodFragment);
    }

    private void initObservers() {
        rashodViewModel.getRashodi().observe(getViewLifecycleOwner(), rashodi -> {
            rashodAdapter.submitList(rashodi);
        });
    }

    private void initRecycler() {
        rashodAdapter = new RashodAdapter(new RashodDiffItemCallback(), rashod -> {
            rashodToSend = rashod;
            return null;
        }, action -> {
            switch (action) {
                case EDIT:
                    Intent intent = new Intent(getActivity(), IzmenaFinansijeActivity.class);
                    intent.putExtra(IzmenaFinansijeActivity.FINANSIJA_RASHOD_KEY, rashodToSend);
                    startActivity(intent);
                    break;
                case DELETE:
                    rashodViewModel.deleteRashod(rashodToSend);
                    break;
            }
            return null;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(rashodAdapter);
    }

}
