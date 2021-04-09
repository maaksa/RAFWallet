package rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.load.data.FileDescriptorAssetPathFetcher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;

public class RashodViewModel extends ViewModel {


    private final MutableLiveData<List<Rashod>> rashodi = new MutableLiveData<>();
    private final ArrayList<Rashod> rashodiList = new ArrayList<>();

    private final MutableLiveData<Integer> sum = new MutableLiveData<>();

    public LiveData<Integer> getSum() {
        return sum;
    }

    public void setSum() {
        int kolicina = 0;
        for (Rashod rashod : rashodiList) {
            kolicina += rashod.getKolicina();
        }
        this.sum.setValue(kolicina);
    }

    public RashodViewModel() {
        createDummyData();
        this.setSum();
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodiList);
        rashodi.setValue(listToSubmit);
    }

    private void createDummyData() {
        Random random = new Random();
        for (int i = 0; i <= 3; i++) {
            int kolicina = random.nextInt((10 - 1) + 1) + 1;
            Rashod r = new Rashod(UUID.randomUUID(), "IT Oprema " + i, kolicina * 100, "Opis " + i, null);
            rashodiList.add(r);
        }
    }

    public LiveData<List<Rashod>> getRashodi() {
        return rashodi;
    }

    public void addRashod(String naslov, int kolicina, String opis) {
        Rashod rashod = new Rashod(UUID.randomUUID(), naslov, kolicina, opis, null);
        rashodiList.add(rashod);
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodiList);
        rashodi.setValue(listToSubmit);
    }


    public void addRashodAudio(String naslov, int kolicina, String opis, File file, UUID id) {
        Rashod rashod = new Rashod(id, naslov, kolicina, opis, file);
        rashodiList.add(rashod);
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodiList);
        rashodi.setValue(listToSubmit);
    }

    public void updateRashod(UUID id, String naslov, int kolicina, String opis) {
        for (Rashod r : rashodiList) {
            if (r.getId() == id) {
                r.setKolicina(kolicina);
                r.setNaslov(naslov);
                r.setOpis(opis);
            }
        }
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodiList);
        rashodi.setValue(listToSubmit);
    }

    public void updateRashodAudio(UUID id, String naslov, int kolicina, String opis, File file) {
        for (Rashod r : rashodiList) {
            if (r.getId() == id) {
                r.setKolicina(kolicina);
                r.setNaslov(naslov);
                r.setOpis(opis);
                r.setAudioZapis(file);
            }
        }
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodiList);
        rashodi.setValue(listToSubmit);
    }

    public void deleteRashod(Rashod rashod) {
        rashodiList.remove(rashod);
        ArrayList<Rashod> listToSubmit = new ArrayList<>(rashodiList);
        rashodi.setValue(listToSubmit);
        this.setSum();
    }

    public int getUkupnaKolicina() {
        int kolicina = 0;
        for (Rashod rashod : rashodiList) {
            kolicina += rashod.getKolicina();
        }
        return kolicina;
    }
}