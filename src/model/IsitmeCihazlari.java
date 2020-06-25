package model;

import java.io.BufferedReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DosyaIslemleri;

public class IsitmeCihazlari extends Cihaz implements IToplam {

    private String tur;

    public IsitmeCihazlari(int id, String isim, int fiyat, int adet, String tur) {
        super(id, isim, fiyat, adet);
        this.tur = tur;
    }

    public IsitmeCihazlari() {
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
    
     public static ObservableList<IsitmeCihazlari> dosyadanIsitmeCihaziGetir(){
         ObservableList<IsitmeCihazlari> geciciListe = FXCollections.observableArrayList();
         try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir("isitmecihazlari");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                if(satir[4].equals("Kulak Dışı Cihaz") || satir[4].equals("Kulak İçi Cihaz")){
                    IsitmeCihazlari kulakİcDis = new KulakDisiCihaz(Integer.parseInt(satir[0]), satir[1], Integer.parseInt(satir[2]), Integer.parseInt(satir[3]), satir[4], Integer.parseInt(satir[5]));
                    geciciListe.add(kulakİcDis);
                } else {
                    IsitmeCihazlari sarjEdilebilir = new SarjEdilebilirCihaz(Integer.parseInt(satir[0]), satir[1], Integer.parseInt(satir[2]), Integer.parseInt(satir[3]), satir[4], Integer.parseInt(satir[5]));
                    geciciListe.add(sarjEdilebilir);
                }
                
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return geciciListe;
    }

    @Override
    public int toplamKayit() {
        int geciciSayac = 0;
        try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir("isitmecihazlari");
            String line;
            while ((line = br.readLine()) != null) {
                    geciciSayac++;    
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return geciciSayac;
    }
        
    
}
