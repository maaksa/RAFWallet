package rs.raf.projekat1.milos_maksimovic_rn4318.view.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments.ListeFragment;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments.ProfilFragment;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments.StanjeFragment;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments.UnosFragment;


public class PagerAdapter extends FragmentPagerAdapter {

    private final int ITEM_COUNT = 4;
    public static final int FRAGMENT_STANJE = 0;
    public static final int FRAGMENT_UNOS = 1;
    public static final int FRAGMENT_LISTE = 2;
    public static final int FRAGMENT_PROFIL = 3;

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case FRAGMENT_STANJE:
                fragment = new StanjeFragment();
                break;
            case FRAGMENT_UNOS:
                fragment = new UnosFragment();
                break;
            case FRAGMENT_LISTE:
                fragment = new ListeFragment();
                break;
            default:
                fragment = new ProfilFragment();
                break;
        }
        return fragment;
    }

    //vraca koliko fragmenta imamo
    @Override
    public int getCount() {
        return ITEM_COUNT;
    }

    //dinamicko dodavanje title za tabItem
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case FRAGMENT_STANJE:
                return "1";
            case FRAGMENT_UNOS:
                return "2";
            case FRAGMENT_LISTE:
                return "3";
            default:
                return "4";
        }
    }
}
