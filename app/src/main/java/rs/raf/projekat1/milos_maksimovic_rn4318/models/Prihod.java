package rs.raf.projekat1.milos_maksimovic_rn4318.models;

import java.io.Serializable;

public class Prihod implements Serializable {

    private int id;
    private String naslov;
    private int kolicina;

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

    public Prihod(int id, String naslov, int kolicina) {
        this.id = id;
        this.naslov = naslov;
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return "Prihod{" +
                "id=" + id +
                ", naslov='" + naslov + '\'' +
                ", kolicina=" + kolicina +
                '}';
    }
}
