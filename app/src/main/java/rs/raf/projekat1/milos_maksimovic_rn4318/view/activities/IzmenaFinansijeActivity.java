package rs.raf.projekat1.milos_maksimovic_rn4318.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.PrihodViewModel;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.RashodViewModel;
import timber.log.Timber;

public class IzmenaFinansijeActivity extends AppCompatActivity {

    public static final String FINANSIJA_PRIHOD_KEY = "finansijaPrihodKey";
    public static final String FINANSIJA_RASHOD_KEY = "finansijaRashodKey";

    private Prihod prihod;
    private Rashod rashod;

    private EditText naslovEt;
    private EditText kolicinaEt;
    private EditText opisEt;

    private ImageView audioIv;

    private Button odustaniBtn;
    private Button izmeniBtn;

    //private PrihodViewModel prihodViewModel;
    //private RashodViewModel rashodViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izmena_finansije);

        // prihodViewModel = new ViewModelProvider(this).get(PrihodViewModel.class);
        //rashodViewModel = new ViewModelProvider(this).get(RashodViewModel.class);

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
        audioIv = findViewById(R.id.audioIvIzmeniFinanseijActivity);
        opisEt = findViewById(R.id.opisIzmeniFinansijeActivity);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            this.prihod = (Prihod) intent.getExtras().getSerializable(FINANSIJA_PRIHOD_KEY);
            if (prihod != null) {
                naslovEt.setText(prihod.getNaslov());
                kolicinaEt.setText(Integer.toString(prihod.getKolicina()));
                if (prihod.getAudioZapis() != null) {
                    opisEt.setVisibility(View.GONE);
                    audioIv.setVisibility(View.VISIBLE);
                } else {
                    opisEt.setText(prihod.getOpis());
                    audioIv.setVisibility(View.GONE);
                }
            } else {
                this.rashod = (Rashod) intent.getExtras().get(FINANSIJA_RASHOD_KEY);
                naslovEt.setText(rashod.getNaslov());
                kolicinaEt.setText(Integer.toString(rashod.getKolicina()));
                if (rashod.getAudioZapis() != null) {
                    opisEt.setVisibility(View.GONE);
                    audioIv.setVisibility(View.VISIBLE);
                } else {
                    opisEt.setText(rashod.getOpis());
                    audioIv.setVisibility(View.GONE);
                }
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
            if (prihod != null) {
                String naslov = naslovEt.getText().toString();
                String opis = opisEt.getText().toString();
                if (naslov.isEmpty() || opis.isEmpty() || kolicinaEt.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Popunite sva polja", Toast.LENGTH_SHORT).show();
                } else {

                    Prihod p = new Prihod(prihod.getId(), naslov, Integer.parseInt(kolicinaEt.getText().toString()), opis, null);
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra(MainActivity.UPDATE_PRIHOD_KEY, p);
                    startActivity(intent);
                    //prihodViewModel.updatePrihod(prihod.getId(), naslov, Integer.parseInt(kolicinaEt.getText().toString()), opis);
                }
            }
        });
    }

}