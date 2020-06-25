package model;

import java.io.BufferedReader;
import util.DosyaIslemleri;

public class Dezenfektan extends MedikalKimyasal implements IToplam {
    
    private double alkolOrani;

    public Dezenfektan(int id, String isim, int fiyat, int adet, String tur,double alkolOrani) {
        super(id, isim, fiyat, adet, tur);
        this.alkolOrani = alkolOrani;
    }

    public Dezenfektan() {
    }

    
    @Override
    public String toString() {
        return getId() + "\t" + getIsim() + "\t" + getFiyat() + "\t" + getAdet() + "\t" + getTur()+ "\t" + getAlkolOrani();
    }
    
    @Override
    public int toplamKayit(){
        int geciciSayac = 0;
        try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir("medikalkimyasal");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                if (satir[4].equals("El Dezenfektan")) {
                    geciciSayac++;
                }
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return geciciSayac;
    }

    public double getAlkolOrani() {
        return alkolOrani;
    }

    public void setAlkolOrani(double alkolOrani) {
        this.alkolOrani = alkolOrani;
    }
    
    

}
