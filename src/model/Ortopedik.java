package model;

import java.io.BufferedReader;
import java.io.FileReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DosyaIslemleri;

public class Ortopedik extends Malzeme {

    private String tur;

    public Ortopedik(int id, String isim, int fiyat, int adet, String tur) {
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
    
    public static ObservableList<Ortopedik> dosyadanOrtopedikGetir(){
         ObservableList<Ortopedik> geciciListe = FXCollections.observableArrayList();
         try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir("ortopedik");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                Ortopedik o = new Ortopedik(Integer.parseInt(satir[0]), satir[1],Integer.parseInt(satir[2]), Integer.parseInt(satir[3]), satir[4]);
                geciciListe.add(o);
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return geciciListe;
    }

}
