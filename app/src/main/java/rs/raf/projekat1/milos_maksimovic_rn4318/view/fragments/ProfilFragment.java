package rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.activities.IzmenaProfilActivity;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.activities.LoginActivity;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.activities.MainActivity;

public class ProfilFragment extends Fragment {

    private TextView name;
    private TextView surname;
    private TextView bankName;

    private Button izmeniBtn;
    private Button odjaviBtn;

    public ProfilFragment() {
        super(R.layout.fragment_profil);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initListeners();
    }

    private void initView(View view) {
        name = view.findViewById(R.id.imeProfilFragmentTv);
        surname = view.findViewById(R.id.prezimeProfilFragmentTv);
        bankName = view.findViewById(R.id.bankaProfilFragmentTv);

        izmeniBtn = view.findViewById(R.id.izmeniBtnProfilFragment);
        odjaviBtn = view.findViewById(R.id.odjaviBtnProfilFragment);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        String nameToDisplay = sharedPreferences.getString(LoginActivity.USERNAME, null);
        String surnameToDisplay = sharedPreferences.getString(LoginActivity.SURNAME, null);
        String bankNameToDisplay = sharedPreferences.getString(LoginActivity.BANK_NAME, null);

        name.setText(nameToDisplay);
        surname.setText(surnameToDisplay);
        bankName.setText(bankNameToDisplay);
    }

    private void initListeners() {
        odjaviBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
            sharedPreferences
                    .edit()
                    .putString(LoginActivity.USERNAME, null)
                    .putString(LoginActivity.SURNAME, null)
                    .putString(LoginActivity.BANK_NAME, null)
                    .apply();
            startActivity(intent);
            getActivity().finish();
        });

        izmeniBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), IzmenaProfilActivity.class);
            startActivity(intent);
        });
    }


}
