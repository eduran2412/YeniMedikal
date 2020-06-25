package model;

import java.io.BufferedReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DosyaIslemleri;

public class MedikalKimyasal extends Malzeme implements IToplam {

    private String tur;

    public MedikalKimyasal(int id, String isim, int fiyat, int adet, String tur) {
        super(id, isim, fiyat, adet);
        this.tur = tur;
    }

    public MedikalKimyasal() {
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
    
    public static ObservableList<MedikalKimyasal> dosyadanMedikalTekstilGetir(){
         ObservableList<MedikalKimyasal> geciciListe = FXCollections.observableArrayList();
         try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir("medikalkimyasal");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                if(satir[4].equals("Sabun")){
                    MedikalKimyasal sabun = new Sabun(Integer.parseInt(satir[0]), satir[1], Integer.parseInt(satir[2]), Integer.parseInt(satir[3]), satir[4], Double.parseDouble(satir[5]));
                    geciciListe.add(sabun);
                } else if(satir[4].equals("El Dezenfektan")){
                    MedikalKimyasal dezenfektan = new Dezenfektan(Integer.parseInt(satir[0]), satir[1], Integer.parseInt(satir[2]), Integer.parseInt(satir[3]), satir[4], Double.parseDouble(satir[5]));
                    geciciListe.add(dezenfektan);
                } else {
                    MedikalKimyasal mk = new MedikalKimyasal(Integer.parseInt(satir[0]), satir[1], Integer.parseInt(satir[2]), Integer.parseInt(satir[3]), satir[4]);
                    geciciListe.add(mk);
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
            BufferedReader br = DosyaIslemleri.dosyayiCagir("medikalkimyasal");
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
