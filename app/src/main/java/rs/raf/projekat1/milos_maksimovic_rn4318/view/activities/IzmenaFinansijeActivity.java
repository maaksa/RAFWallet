package rs.raf.projekat1.milos_maksimovic_rn4318.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;
import timber.log.Timber;

public class IzmenaFinansijeActivity extends AppCompatActivity {

    public static final String FINANSIJA_PRIHOD_KEY = "finansijaPrihodKey";
    public static final String FINANSIJA_RASHOD_KEY = "finansijaRashodKey";

    private Prihod prihod;
    private Rashod rashod;
    private EditText naslovEt;
    private EditText kolicinaEt;
    private Button odustaniBtn;
    private Button izmeniBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izmena_finansije);
        init();
    }

    private void init() {
        initView();
        initListeners();
    }

    private void initView() {
        izmeniBtn = findViewById(R.id.izmeniBtnIzmaneFinansijeActivity);
        odustaniBtn = findViewById(R.id.odustaniBtnIzmaneFinansijeActivity);
        naslovEt = findViewById(R.id.naslovEdIzmenaFinansijaActivity);
        kolicinaEt = findViewById(R.id.kolicinaEdIzmenaFinansijaActivity);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            this.prihod = (Prihod) intent.getExtras().getSerializable(FINANSIJA_PRIHOD_KEY);
            if (prihod != null) {
                naslovEt.setText(prihod.getNaslov());
                kolicinaEt.setText(Integer.toString(prihod.getKolicina()));
            } else {
                this.rashod = (Rashod) intent.getExtras().get(FINANSIJA_RASHOD_KEY);
                naslovEt.setText(rashod.getNaslov());
                kolicinaEt.setText(Integer.toString(rashod.getKolicina()));
            }
        }
    }

    private void initListeners() {
        odustaniBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        izmeniBtn.setOnClickListener(v -> {

        });
    }
}