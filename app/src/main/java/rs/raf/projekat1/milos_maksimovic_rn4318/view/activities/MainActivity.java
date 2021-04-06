package rs.raf.projekat1.milos_maksimovic_rn4318.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.viewpager.PagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initViewPager();
        initNavigation();
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.customViewPager);
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
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