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
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.activities.PrikazFinansijeActivity;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.recycler.adapter.PrihodAdapter;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.recycler.differ.PrihodDiffItemCallback;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.PrihodViewModel;

public class PrihodFragment extends Fragment {

    private PrihodViewModel prihodViewModel;
    private PrihodAdapter prihodAdapter;
    private RecyclerView recyclerView;
    private Prihod prihodToSend;

    private final String SECOND_FRAGMENT_TAG = "secondFragment";
    private final String FIRST_FRAGMENT_TAG = "firstFragment";

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
        recyclerView = view.findViewById(R.id.listRvPrihodFragment);
    }

    private void initObservers() {
        prihodViewModel.getPrihodi().observe(getViewLifecycleOwner(), prihodi -> {
            prihodAdapter.submitList(prihodi);
        });
    }

    private void initRecycler() {
        prihodAdapter = new PrihodAdapter(new PrihodDiffItemCallback(), prihod -> {
            prihodToSend = prihod;
            return null;
        }, action -> {
            switch (action) {
                case EDIT:
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                    IzmenaFinansijaFragment izf = new IzmenaFinansijaFragment();
                    Bundle args = new Bundle();
                    args.putSerializable(IzmenaFinansijaFragment.FINANSIJA_PRIHOD_KEY, prihodToSend);
                    izf.setArguments(args);

                    transaction.replace(R.id.izmenaFinansijaFragment, izf, FIRST_FRAGMENT_TAG);
                    transaction.commit();

                    break;
                case SHOW:
                    Intent intent2 = new Intent(getActivity(), PrikazFinansijeActivity.class);
                    intent2.putExtra(PrikazFinansijeActivity.FINANSIJA_PRIHOD_KEY, prihodToSend);
                    startActivity(intent2);
                    break;
                case DELETE:
                    prihodViewModel.deletePrihod(prihodToSend);
                    break;
            }
            return null;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(prihodAdapter);
    }
}
