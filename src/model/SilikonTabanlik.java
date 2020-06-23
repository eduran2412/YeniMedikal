/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import util.DosyaIslemleri;

/**
 *
 * @author erend
 */
public class SilikonTabanlik extends Ortopedik implements IToplam {
    
    public SilikonTabanlik(int id, String isim, int fiyat, int adet, String tur) {
        super(id, isim, fiyat, adet, tur);
    }

    public SilikonTabanlik() {
    }
    
    
    
    @Override
    public String toString() {
        return getId() + "\t" + getIsim() + "\t" + getFiyat() + "\t" + getAdet() + "\t" + getTur();
    }

    @Override
    public int toplamKayit() {
         int geciciSayac = 0;
        try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir("ortopedik");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                if(satir[4].equals("Silikon Tabanlık")){
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
