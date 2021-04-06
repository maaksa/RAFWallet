package rs.raf.projekat1.milos_maksimovic_rn4318.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;

public class IzmenaProfilActivity extends AppCompatActivity {

    private EditText name;
    private EditText surname;
    private EditText bankName;

    private Button izmeniBtn;
    private Button odustaniBtn;

    private String nameToDisplay;
    private String surnameToDisplay;
    private String bankNameToDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izmena_profil);
        init();
    }

    private void init() {
        initView();
        initListeners();
    }

    private void initView() {
        name = findViewById(R.id.imeIzmenaProfilActivity);
        surname = findViewById(R.id.prezimaIzmenaProfilActivity);
        bankName = findViewById(R.id.bankaIzmenaProfilActivity);

        izmeniBtn = findViewById(R.id.izmeniBtnIzmaneProfilActivity);
        odustaniBtn = findViewById(R.id.odustaniBtnIzmaneProfilActivity);

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        nameToDisplay = sharedPreferences.getString(LoginActivity.USERNAME, null);
        surnameToDisplay = sharedPreferences.getString(LoginActivity.SURNAME, null);
        bankNameToDisplay = sharedPreferences.getString(LoginActivity.BANK_NAME, null);

        name.setText(nameToDisplay);
        surname.setText(surnameToDisplay);
        bankName.setText(bankNameToDisplay);
    }

    private void initListeners() {
        izmeniBtn.setOnClickListener(v -> {
            if (name.getText().toString().isEmpty() || surname.getText().toString().isEmpty() || bankName.getText().toString().isEmpty()) {
                Toast.makeText(this, "Popunite sva polja", Toast.LENGTH_LONG).show();
            } else {
                SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                sharedPreferences
                        .edit()
                        .putString(LoginActivity.USERNAME, name.getText().toString())
                        .putString(LoginActivity.SURNAME, surname.getText().toString())
                        .putString(LoginActivity.BANK_NAME, bankName.getText().toString())
                        .apply();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        odustaniBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}