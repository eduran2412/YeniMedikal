package model;

public class MedikalTekstil extends Malzeme {

    private String tur;

    public MedikalTekstil(int id, String isim, int fiyat, int adet, String tur) {
        super(id, isim, fiyat, adet);
        this.tur = tur;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    @Override
    public String toString() {
        return getId() + "\t" + getIsim() + "\t" + getFiyat() + "\t" + getAdet() + "\t" + getTur();
    }
}
