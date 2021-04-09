package rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.activities.PrikazFinansijeActivity;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.recycler.adapter.RashodAdapter;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.recycler.differ.RashodDiffItemCallback;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.RashodViewModel;

public class RashodFragment extends Fragment {

    private RashodViewModel rashodViewModel;
    private RashodAdapter rashodAdapter;
    private RecyclerView recyclerView;
    private Rashod rashodToSend;

    private final String RASHOD_FRAGMENT_TAG = "rashodFragmen";

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
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                    IzmenaFinansijaFragment izf = new IzmenaFinansijaFragment();
                    Bundle args = new Bundle();
                    args.putSerializable(IzmenaFinansijaFragment.FINANSIJA_RASHOD_KEY, rashodToSend);
                    izf.setArguments(args);

                    //zamenjujemo rv fragment rashod sa edit finansija fragment
                    transaction.replace(R.id.izmenaFinansijaRashodFragment, izf, RASHOD_FRAGMENT_TAG);
                    transaction.commit();

                    break;
                case SHOW:
                    Intent intent2 = new Intent(getActivity(), PrikazFinansijeActivity.class);
                    intent2.putExtra(PrikazFinansijeActivity.FINANSIJA_RASHOD_KEY, rashodToSend);
                    startActivity(intent2);
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
