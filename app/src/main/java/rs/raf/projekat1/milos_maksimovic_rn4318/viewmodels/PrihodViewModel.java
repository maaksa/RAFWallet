package rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;

public class PrihodViewModel extends ViewModel {

    //public static int counter = 4;

    private final MutableLiveData<List<Prihod>> prihodi = new MutableLiveData<>();
    private final ArrayList<Prihod> prihodiList = new ArrayList<>();

    public PrihodViewModel() {
        createDummyData();
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiList);
        prihodi.setValue(listToSubmit);
    }

    private void createDummyData() {
        Random random = new Random();
        for (int i = 0; i <= 3; i++) {
            int kolicina = random.nextInt((10 - 1) + 1) + 1;
            Prihod p = new Prihod(UUID.randomUUID(), "IT Firma " + i, kolicina * 100, "Opis " + i, null);
            prihodiList.add(p);
        }
    }

    public LiveData<List<Prihod>> getPrihodi() {
        return prihodi;
    }

    public void addPrihod(String naslov, int kolicina, String opis) {
        Prihod prihod = new Prihod(UUID.randomUUID(), naslov, kolicina, opis, null);
        prihodiList.add(prihod);
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiList);
        prihodi.setValue(listToSubmit);
    }

    public void addPrihodAudio(String naslov, int kolicina, String opis, File file, UUID id) {
        Prihod prihod = new Prihod(id, naslov, kolicina, opis, file);
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
