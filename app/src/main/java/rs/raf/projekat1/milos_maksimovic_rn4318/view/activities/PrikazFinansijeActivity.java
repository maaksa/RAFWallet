package rs.raf.projekat1.milos_maksimovic_rn4318.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;

public class PrikazFinansijeActivity extends AppCompatActivity {

    public static final String FINANSIJA_PRIHOD_KEY = "finansijaPrihodKey";
    public static final String FINANSIJA_RASHOD_KEY = "finansijaRashodKey";

    private ImageView playIv;
    private TextView naslovTv;
    private TextView kolicinaTv;
    private TextView opisTv;

    private Prihod prihod;
    private Rashod rashod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_finansije);
        init();
    }

    private void init() {
        initView();
        initListeners();
    }

    private void initView() {
        naslovTv = findViewById(R.id.naslovEdPrikazFinansijaActivity);
        kolicinaTv = findViewById(R.id.kolicinaEdPrikazFinansijaActivity);
        playIv = findViewById(R.id.playIvPrikazFinansijaActivity);
        opisTv = findViewById(R.id.opisPrikazFinansijeActivityEt);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            this.prihod = (Prihod) intent.getExtras().getSerializable(FINANSIJA_PRIHOD_KEY);
            if (prihod != null) {
                naslovTv.setText(prihod.getNaslov());
                kolicinaTv.setText(Integer.toString(prihod.getKolicina()));
                opisTv.setText(prihod.getOpis());
            } else {
                this.rashod = (Rashod) intent.getExtras().get(FINANSIJA_RASHOD_KEY);
                naslovTv.setText(rashod.getNaslov());
                kolicinaTv.setText(Integer.toString(rashod.getKolicina()));
                opisTv.setText(rashod.getOpis());
            }
        }
    }

    private void initListeners() {
    }
}