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

public class PrihodViewModel extends ViewModel {


    private final MutableLiveData<List<Prihod>> prihodi = new MutableLiveData<>();
    private final ArrayList<Prihod> prihodiList = new ArrayList<>();

    private final MutableLiveData<Integer> sum = new MutableLiveData<>();

    public LiveData<Integer> getSum() {
        return sum;
    }

    public void setSum() {
        int kolicina = 0;
        for (Prihod prihod : prihodiList) {
            kolicina += prihod.getKolicina();
        }
        this.sum.setValue(kolicina);
    }

    public PrihodViewModel() {
        createDummyData();
        this.setSum();
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiList);
        prihodi.setValue(listToSubmit);
    }

    private void createDummyData() {
        Random random = new Random();
        for (int i = 0; i <= 6; i++) {
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
        this.setSum();
    }

    public void addPrihodAudio(String naslov, int kolicina, String opis, File file, UUID id) {
        Prihod prihod = new Prihod(id, naslov, kolicina, opis, file);
        prihodiList.add(prihod);
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiList);
        prihodi.setValue(listToSubmit);
        this.setSum();
    }

    public void updatePrihod(UUID id, String naslov, int kolicina, String opis) {
        for (Prihod p : prihodiList) {
            if (p.getId() == id) {
                p.setKolicina(kolicina);
                p.setNaslov(naslov);
                p.setOpis(opis);
            }
        }
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiList);
        prihodi.setValue(listToSubmit);
        this.setSum();
    }

    public void updatePrihodAudio(UUID id, String naslov, int kolicina, String opis, File file) {
        for (Prihod p : prihodiList) {
            if (p.getId() == id) {
                p.setKolicina(kolicina);
                p.setNaslov(naslov);
                p.setOpis(opis);
                p.setAudioZapis(file);
            }
        }
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiList);
        prihodi.setValue(listToSubmit);
        this.setSum();
    }

    public void deletePrihod(Prihod prihod) {
        prihodiList.remove(prihod);
        ArrayList<Prihod> listToSubmit = new ArrayList<>(prihodiList);
        prihodi.setValue(listToSubmit);
        this.setSum();
    }

    public int getUkupnaKolicina() {
        int kolicina = 0;
        for (Prihod prihod : prihodiList) {
            kolicina += prihod.getKolicina();
        }
        return kolicina;
    }
}
