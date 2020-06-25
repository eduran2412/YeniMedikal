package model;

import java.io.BufferedReader;
import util.DosyaIslemleri;

public class Sabun extends MedikalKimyasal implements IToplam {

    private double phDegeri;

    public Sabun(int id, String isim, int fiyat, int adet, String tur, double phDegeri) {
        super(id, isim, fiyat, adet, tur);
        this.phDegeri = phDegeri;
    }

    public Sabun() {
    }

    @Override
    public String toString() {
        return getId() + "\t" + getIsim() + "\t" + getFiyat() + "\t" + getAdet() + "\t" + getTur()+ "\t" + getPhDegeri();
    }

    @Override
    public int toplamKayit() {
        int geciciSayac = 0;
        try {
            BufferedReader br = DosyaIslemleri.dosyayiCagir("medikalkimyasal");
            String line;
            String[] satir;
            while ((line = br.readLine()) != null) {
                satir = line.split("\t");
                if (satir[4].equals("Sabun")) {
                    geciciSayac++;
                }
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return geciciSayac;
    }

    public double getPhDegeri() {
        return phDegeri;
    }

    public void setPhDegeri(double phDegeri) {
        this.phDegeri = phDegeri;
    }

}
