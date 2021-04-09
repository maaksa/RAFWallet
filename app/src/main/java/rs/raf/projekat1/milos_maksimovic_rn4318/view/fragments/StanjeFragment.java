package rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import java.util.ArrayList;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.PrihodViewModel;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.RashodViewModel;

public class StanjeFragment extends Fragment {

    private PrihodViewModel prihodViewModel;
    private RashodViewModel rashodViewModel;

    private TextView prihodTv;
    private TextView rashodTv;
    private TextView razlikaTv;

    private TextView rashraz;
    private TextView prihraz;

    public boolean prihodKolBool;
    public boolean rashodKolBool;

    public int prihodKol;
    public int rashodKol;

    public StanjeFragment() {
        super(R.layout.fragment_stanje);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prihodViewModel = new ViewModelProvider(requireActivity()).get(PrihodViewModel.class);
        rashodViewModel = new ViewModelProvider(requireActivity()).get(RashodViewModel.class);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initObservers();
    }

    private void initView(View view) {
        prihodTv = view.findViewById(R.id.prihodStanjeFragment);
        rashodTv = view.findViewById(R.id.rashodStanjeFragment);
        razlikaTv = view.findViewById(R.id.razlikaStanjeFragment);

        prihraz = view.findViewById(R.id.prihraz);
        rashraz = view.findViewById(R.id.rashraz);
    }

    private void initObservers() {
        prihodViewModel.getSum().observe(getViewLifecycleOwner(), prihodSum -> {
            prihodKolBool = true;
            prihodKol = prihodSum;
            if (rashodKolBool) {
                if (prihodKol - rashodKol < 0) {
                    razlikaTv.setTextColor(Color.RED);
                } else {
                    razlikaTv.setTextColor(Color.GREEN);
                }
                razlikaTv.setText(Integer.toString(prihodKol - rashodKol));
            }
        });

        rashodViewModel.getSum().observe(getViewLifecycleOwner(), rashodSum -> {
            rashodKolBool = true;
            rashodKol = rashodSum;
            if (prihodKolBool) {
                if (prihodKol - rashodKol < 0) {
                    razlikaTv.setTextColor(Color.RED);
                } else {
                    razlikaTv.setTextColor(Color.GREEN);
                }
                razlikaTv.setText(Integer.toString(prihodKol - rashodKol));
            }
        });

        prihodViewModel.getPrihodi().observe(getViewLifecycleOwner(), prihodi -> {
            int kolicinaPrihodi = 0;
            for (Prihod p : prihodi) {
                kolicinaPrihodi += p.getKolicina();
            }
            prihodTv.setText(Integer.toString(kolicinaPrihodi));
            prihodTv.setTextColor(Color.GREEN);
        });

        rashodViewModel.getRashodi().observe(getViewLifecycleOwner(), rashodi -> {
            int kolicinaRashodi = 0;
            for (Rashod r : rashodi) {
                kolicinaRashodi += r.getKolicina();
            }
            rashodTv.setText(Integer.toString(kolicinaRashodi));
            rashodTv.setTextColor(Color.RED);
        });
    }

}

