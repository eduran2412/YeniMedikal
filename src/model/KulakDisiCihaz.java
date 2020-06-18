
package model;


public class KulakDisiCihaz extends IsitmeCihazlari{
    
    public KulakDisiCihaz(int id, String isim, int fiyat, int adet, String tur) {
        super(id, isim, fiyat, adet, tur);
    }
    
    @Override
    public String toString() {
        return getId() + "\t" + getIsim() + "\t" + getFiyat() + "\t" + getAdet() + "\t" + getTur();
    }
    
}
