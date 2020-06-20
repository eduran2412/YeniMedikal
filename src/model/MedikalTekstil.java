package model;

import java.io.BufferedReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DosyaIslemleri;

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
    
    public static ObservableList<MedikalTekstil> dosyadanMedikalTekstilGetir(){
         ObservableList<MedikalTekstil> geciciListe = FXCollections.observableArrayList();
         try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir("medikaltekstil");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                MedikalTekstil mt = new MedikalTekstil(Integer.parseInt(satir[0]), satir[1],Integer.parseInt(satir[2]), Integer.parseInt(satir[3]), satir[4]);
                geciciListe.add(mt);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return geciciListe;
    }
}
