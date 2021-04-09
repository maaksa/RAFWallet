package rs.raf.projekat1.milos_maksimovic_rn4318.view.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments.ListeFragment;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments.PrihodFragment;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments.PrihodIzmenaFragment;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments.ProfilFragment;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments.RashodFragment;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments.RashodIzmenaFragment;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments.StanjeFragment;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments.UnosFragment;

public class PagerAdapterListe extends FragmentPagerAdapter {

    private final int ITEM_COUNT = 2;
    public static final int FRAGMENT_PRIHOD = 0;
    public static final int FRAGMENT_RASHOD = 1;

    public PagerAdapterListe(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case FRAGMENT_PRIHOD:
                fragment = new PrihodIzmenaFragment();
                break;
            default:
                fragment = new RashodIzmenaFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case FRAGMENT_PRIHOD:
                return "PRIHODI";
            default:
                return "RASHODI";
        }
    }
}