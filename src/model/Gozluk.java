
package model;


public class Gozluk extends MedikalTekstil{
    
    public Gozluk(int id, String isim, int fiyat, int adet, String tur) {
        super(id, isim, fiyat, adet, tur);
    }
    
     @Override
    public String toString() {
        return getId() + "\t" + getIsim() + "\t" + getFiyat() + "\t" + getAdet() + "\t" + getTur();
    }
}
