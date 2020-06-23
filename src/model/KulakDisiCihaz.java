
package model;

import java.io.BufferedReader;
import util.DosyaIslemleri;


public class KulakDisiCihaz extends IsitmeCihazlari implements IToplam{
    
    public KulakDisiCihaz(int id, String isim, int fiyat, int adet, String tur) {
        super(id, isim, fiyat, adet, tur);
    }

    public KulakDisiCihaz() {
    }
    
    
    
    @Override
    public String toString() {
        return getId() + "\t" + getIsim() + "\t" + getFiyat() + "\t" + getAdet() + "\t" + getTur();
    }

    @Override
    public int toplamKayit() {
        int geciciSayac = 0;
        try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir("isitmecihazlari");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                if(satir[4].equals("Kulak Dışı Cihaz")){
                    geciciSayac++;
                }
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return geciciSayac;
    }
    
}
