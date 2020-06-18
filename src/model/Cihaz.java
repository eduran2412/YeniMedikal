
package model;


abstract public class Cihaz {
    private int id;
    private String isim;
    private int fiyat;
    private int adet;

    public Cihaz(int id, String isim, int fiyat, int adet) {
        this.id = id;
        this.isim = isim;
        this.fiyat = fiyat;
        this.adet = adet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    @Override
    public String toString() {
        return getId() + "\t" + getIsim() + "\t" + getFiyat() + "\t" + getAdet();
    }
    
    
}
