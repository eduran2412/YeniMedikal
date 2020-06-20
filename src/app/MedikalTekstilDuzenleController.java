
package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Gozluk;
import model.KoruyucuMaske;
import model.KoruyucuOnluk;
import model.MedikalTekstil;
import util.DosyaIslemleri;



public class MedikalTekstilDuzenleController implements Initializable {

    @FXML
    private ComboBox<String> cmbTur;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtIsim;
    @FXML
    private TextField txtAdet;
    @FXML
    private TextField txtFiyat;
     private ObservableList<MedikalTekstil> medikalTekstilListesi = FXCollections.observableArrayList();
     ObservableList<String> tur = FXCollections.observableArrayList("Gözlük", "Koruyucu Maske", "Koruyucu Önlük");

    public void setMedikalTekstilListesi(ObservableList<MedikalTekstil> medikalTekstilListesi) {
        this.medikalTekstilListesi = medikalTekstilListesi;
    }
     

    MedikalTekstil seciliMedikalTekstil;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTur.setItems(tur);
    }    

    void duzenleyeDegerGonder(MedikalTekstil mt) {
        seciliMedikalTekstil = mt;
        txtId.setText(String.valueOf(seciliMedikalTekstil.getId()));
        txtIsim.setText(seciliMedikalTekstil.getIsim());
        txtAdet.setText(String.valueOf(seciliMedikalTekstil.getAdet()));
        txtFiyat.setText(String.valueOf(seciliMedikalTekstil.getFiyat()));
        cmbTur.setValue(seciliMedikalTekstil.getTur());
    }

    @FXML
    private void medikalTekstilDuzenleKaydet(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText().trim());
        String isim = txtIsim.getText().trim();
        int fiyat = Integer.parseInt(txtFiyat.getText().trim());
        int adet = Integer.parseInt(txtAdet.getText().trim());
        String tur = cmbTur.getValue();
        if(tur.equals("Gozluk")){
            medikalTekstilListesi.remove(seciliMedikalTekstil);
            MedikalTekstil gozluk = new Gozluk(id, isim, fiyat, adet, tur);
            medikalTekstilListesi.add(gozluk);
            DosyaIslemleri.dosyayaYaz(medikalTekstilListesi, "medikaltekstil");
        }else if(tur.equals("Koruyucu Maske")){
            medikalTekstilListesi.remove(seciliMedikalTekstil);
            MedikalTekstil koruyucuMaske = new KoruyucuMaske(id, isim, fiyat, adet, tur);
            medikalTekstilListesi.add(koruyucuMaske);
            DosyaIslemleri.dosyayaYaz(medikalTekstilListesi, "medikaltekstil");
        } else if(tur.equals("Koruyucu Önlük")){
            medikalTekstilListesi.remove(seciliMedikalTekstil);
            MedikalTekstil koruyucuOnluk = new KoruyucuOnluk(id, isim, fiyat, adet, tur);
            medikalTekstilListesi.add(koruyucuOnluk);
            DosyaIslemleri.dosyayaYaz(medikalTekstilListesi, "medikaltekstil");
        } else{
            medikalTekstilListesi.remove(seciliMedikalTekstil);
            MedikalTekstil medikalTekstil = new MedikalTekstil(id, isim, fiyat, adet, tur);
            medikalTekstilListesi.add(medikalTekstil);
            DosyaIslemleri.dosyayaYaz(medikalTekstilListesi, "medikaltekstil");
        }
        
        kapat(event);
    }

    
    private void kapat(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void medikalTekstilDuzenleIptal(ActionEvent event) {
        kapat(event);
    }
    
}
