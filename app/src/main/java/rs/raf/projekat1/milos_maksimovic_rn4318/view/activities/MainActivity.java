package rs.raf.projekat1.milos_maksimovic_rn4318.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.viewpager.PagerAdapter;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.PrihodViewModel;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    public static final String UPDATE_PRIHOD_KEY = "updatePrihod";
    private PrihodViewModel prihodViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prihodViewModel = new ViewModelProvider(this).get(PrihodViewModel.class);

        init();
    }

    private void init() {
        initView();
        initViewPager();
        initNavigation();
    }

    private void initView() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            Prihod p = (Prihod) intent.getExtras().getSerializable(UPDATE_PRIHOD_KEY);
            prihodViewModel.updatePrihod(p.getId(), p.getNaslov(), p.getKolicina(), p.getOpis());
        }
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.customViewPager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);
    }

    private void initNavigation() {
        ((BottomNavigationView) findViewById(R.id.bottomNavigation)).setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                // setCurrentItem metoda viewPager samo obavesti koji je Item trenutno aktivan i onda metoda getItem u adapteru setuje odredjeni fragment za tu poziciju
                case R.id.navigation_stanje:
                    //na ovaj nacin ce nas customViewPager da prikazuje odredjene fragmente
                    viewPager.setCurrentItem(PagerAdapter.FRAGMENT_STANJE, false);
                    break;
                case R.id.navigation_unos:
                    viewPager.setCurrentItem(PagerAdapter.FRAGMENT_UNOS, false);
                    break;
                case R.id.navigation_liste:
                    viewPager.setCurrentItem(PagerAdapter.FRAGMENT_LISTE, false);
                    break;
                case R.id.navigation_profil:
                    viewPager.setCurrentItem(PagerAdapter.FRAGMENT_PROFIL, false);
                    break;
            }
            return true;
        });
    }

}