package rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;

public class PrihodViewModel extends ViewModel {

    public static int counter = 4;

    private final MutableLiveData<List<Prihod>> prihodi = new MutableLiveData<>();
    private final ArrayList<Prihod> prihodiList = new ArrayList<>();

    public PrihodViewModel() {
        createDummyData();
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiList);
        prihodi.setValue(listToSubmit);
    }

    private void createDummyData() {
        Prihod prihod1 = new Prihod(1, "IT firam", 10000);
        Prihod prihod2 = new Prihod(2, "Poljoprivreda", 3500);
        Prihod prihod3 = new Prihod(3, "Nekretnine", 2800);

        prihodiList.add(prihod1);
        prihodiList.add(prihod2);
        prihodiList.add(prihod3);
    }

    public LiveData<List<Prihod>> getPrihodi() {
        return prihodi;
    }

    public void addPrihod(String naslov, int kolicina) {
        Prihod prihod = new Prihod(counter++, naslov, kolicina);
        prihodiList.add(prihod);
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiList);
        prihodi.setValue(listToSubmit);
    }

    public int getUkupnaKolicina() {
        int kolicina = 0;
        for (Prihod prihod : prihodiList) {
            kolicina += prihod.getKolicina();
        }
        return kolicina;
    }
}
