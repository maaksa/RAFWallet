package rs.raf.projekat1.milos_maksimovic_rn4318.models;

import java.io.Serializable;

public class Rashod implements Serializable {

    private int id;
    private String naslov;
    private int kolicina;
    private String opis;

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Rashod(int id, String naslov, int kolicina, String opis) {
        this.id = id;
        this.naslov = naslov;
        this.kolicina = kolicina;
        this.opis = opis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Rashod{" +
                "id=" + id +
                ", naslov='" + naslov + '\'' +
                ", kolicina=" + kolicina +
                '}';
    }
}
