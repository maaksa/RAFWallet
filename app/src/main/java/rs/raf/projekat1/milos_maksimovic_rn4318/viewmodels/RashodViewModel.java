package rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

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
        Rashod rashod1 = new Rashod(1, "Kola", 11000);
        Rashod rashod2 = new Rashod(2, "Odeca", 1000);
        Rashod rashod3 = new Rashod(3, "IT Oprema", 2500);

        rashodiList.add(rashod1);
        rashodiList.add(rashod2);
        rashodiList.add(rashod3);
    }

    public LiveData<List<Rashod>> getRashodi() {
        return rashodi;
    }

    public void addRashod(String naslov, int kolicina) {
        Rashod rashod = new Rashod(counter++, naslov, kolicina);
        rashodiList.add(rashod);
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