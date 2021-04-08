package rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;

public class RashodViewModel extends ViewModel {

    public static int counter = 4;

    private final MutableLiveData<List<Rashod>> rashodi = new MutableLiveData<>();
    private final ArrayList<Rashod> rashodiList = new ArrayList<>();

    public RashodViewModel() {
        createDummyData();
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodiList);
        rashodi.setValue(listToSubmit);
    }

    private void createDummyData() {
        Random random = new Random();
        for (int i = 0; i <= 3; i++) {
            int kolicina = random.nextInt((10 - 1) + 1) + 1;
            Rashod r = new Rashod(i, "IT Oprema " + i, kolicina * 100, "Opis " + i);
            rashodiList.add(r);
        }
    }

    public LiveData<List<Rashod>> getRashodi() {
        return rashodi;
    }

    public void addRashod(String naslov, int kolicina, String opis) {
        Rashod rashod = new Rashod(counter++, naslov, kolicina, opis);
        rashodiList.add(rashod);
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodiList);
        rashodi.setValue(listToSubmit);
    }

    public void deleteRashod(Rashod rashod) {
        rashodiList.remove(rashod);
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodiList);
        rashodi.setValue(listToSubmit);
    }

    public int getUkupnaKolicina() {
        int kolicina = 0;
        for (Rashod rashod : rashodiList) {
            kolicina += rashod.getKolicina();
        }
        return kolicina;
    }
}