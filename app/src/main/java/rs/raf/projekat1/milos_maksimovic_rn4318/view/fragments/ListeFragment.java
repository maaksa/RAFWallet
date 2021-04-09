package rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.view.viewpager.PagerAdapterListe;

public class ListeFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public ListeFragment() {
        super(R.layout.fragment_liste);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initTabs();
    }

    private void initView(View view) {
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
    }

    private void initTabs() {
        viewPager.setAdapter(new PagerAdapterListe(getActivity().getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }
}
