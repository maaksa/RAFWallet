package rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;

public class PrihodViewModel extends ViewModel {

    public static int counter = 11;

    private final MutableLiveData<List<Prihod>> prihodi = new MutableLiveData<>();
    private final ArrayList<Prihod> prihodiList = new ArrayList<>();

    public PrihodViewModel() {
        createDummyData();
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiList);
        prihodi.setValue(listToSubmit);
    }

    private void createDummyData() {
        Random random = new Random();
        for (int i = 0; i <= 10; i++) {
            int kolicina = random.nextInt((10 - 1) + 1) + 1;
            Prihod p = new Prihod(i, "IT Firma " + i, kolicina * 100);
            prihodiList.add(p);
        }
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

    public void deletePrihod(Prihod prihod) {
        prihodiList.remove(prihod);
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
