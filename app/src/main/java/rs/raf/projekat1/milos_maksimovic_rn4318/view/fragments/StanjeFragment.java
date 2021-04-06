package rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.RandomAccess;
import java.util.concurrent.TimeoutException;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.PrihodViewModel;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.RashodViewModel;
import timber.log.Timber;

public class StanjeFragment extends Fragment {

    private PrihodViewModel prihodViewModel;
    private RashodViewModel rashodViewModel;

    private TextView prihodTv;
    private TextView rashodTv;
    private TextView razlikaTv;

    private int prihodKol;
    private int rashodKol;

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
    }

    private void initObservers() {
//        prihodTv.setText(Integer.toString(prihodViewModel.getUkupnaKolicina()));
//        prihodTv.setTextColor(Color.GREEN);
//        rashodTv.setText(Integer.toString(rashodViewModel.getUkupnaKolicina()));
//        rashodTv.setTextColor(Color.RED);



        if (prihodViewModel.getUkupnaKolicina() - rashodViewModel.getUkupnaKolicina() < 0) {
            razlikaTv.setTextColor(Color.RED);
        } else {
            razlikaTv.setTextColor(Color.GREEN);
        }

        razlikaTv.setText(Integer.toString(prihodViewModel.getUkupnaKolicina() - rashodViewModel.getUkupnaKolicina()));



        prihodViewModel.getPrihodi().observe(getViewLifecycleOwner(), prihodi -> {
            int kolicinaPrihodi = 0;
            for (Prihod p : prihodi) {
                kolicinaPrihodi += p.getKolicina();
            }
            prihodKol = kolicinaPrihodi;
            prihodTv.setText(Integer.toString(kolicinaPrihodi));
            prihodTv.setTextColor(Color.GREEN);;
        });
        rashodViewModel.getRashodi().observe(getViewLifecycleOwner(), rashodi -> {
            int kolicinaRashodi = 0;
            for (Rashod r : rashodi) {
                kolicinaRashodi += r.getKolicina();
            }
            rashodKol = kolicinaRashodi;
            rashodTv.setText(Integer.toString(kolicinaRashodi));
            rashodTv.setTextColor(Color.RED);
        });

    }
}

