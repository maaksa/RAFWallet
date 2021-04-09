package rs.raf.projekat1.milos_maksimovic_rn4318.models;

import java.io.File;
import java.io.Serializable;
import java.util.UUID;

public class Rashod implements Serializable {

    private UUID id;
    private String naslov;
    private int kolicina;
    private String opis;
    private File audioZapis;

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Rashod(UUID id, String naslov, int kolicina, String opis, File audioZapis) {
        this.id = id;
        this.naslov = naslov;
        this.kolicina = kolicina;
        this.opis = opis;
        this.audioZapis = audioZapis;
    }

    public File getAudioZapis() {
        return audioZapis;
    }

    public void setAudioZapis(File audioZapis) {
        this.audioZapis = audioZapis;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

}
