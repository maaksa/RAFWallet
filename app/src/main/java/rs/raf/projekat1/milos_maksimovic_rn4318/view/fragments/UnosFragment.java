package rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.PrihodViewModel;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.RashodViewModel;

public class UnosFragment extends Fragment {

    private Spinner spinner;
    private EditText naslovEt;
    private EditText kolicinaEt;
    private Button dodajBtn;

    private PrihodViewModel prihodViewModel;
    private RashodViewModel rashodViewModel;

    public UnosFragment() {
        super(R.layout.fragment_unos);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prihodViewModel = new ViewModelProvider(requireActivity()).get(PrihodViewModel.class);
        rashodViewModel = new ViewModelProvider(requireActivity()).get(RashodViewModel.class);

        spinner = view.findViewById(R.id.spinner);
        List<String> categories = new ArrayList<String>();
        categories.add("Prihod");
        categories.add("Rashod");

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.options_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);

        init(view);
    }

    private void init(View view) {
        initView(view);
        setSpinnerListener(view);
        initListeners();
    }

    private void setSpinnerListener(View view) {
        Spinner spinner = view.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            //svaki put kad selektujemo neku od staviki iz drop down liste ucice u ovu metodu
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            }

            //ukoliko nista nismo selektovali poziva se ova metoda
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
    }

    private void initView(View view) {
        naslovEt = view.findViewById(R.id.naslovUnosFragmentEt);
        kolicinaEt = view.findViewById(R.id.kolicinaUnosFragmentEt);
        dodajBtn = view.findViewById(R.id.dodajFragmentUnosBtn);
    }

    private void initListeners() {
        dodajBtn.setOnClickListener(v -> {
            String option = (String) spinner.getSelectedItem();
            String naslov = naslovEt.getText().toString();
            if (naslov.isEmpty() || kolicinaEt.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), "Sva polja morate popuniti", Toast.LENGTH_SHORT).show();
            } else {
                int kolicina = Integer.parseInt(kolicinaEt.getText().toString());
                if (option.equals("Prihod")) {
                    prihodViewModel.addPrihod(naslov, kolicina);
                } else {
                    rashodViewModel.addRashod(naslov, kolicina);
                }
                kolicinaEt.setText("");
                naslovEt.setText("");
            }

        });
    }
}
