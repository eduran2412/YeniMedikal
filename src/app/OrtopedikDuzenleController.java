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
import model.Boyunluk;
import model.KoltukDegnegi;
import model.Ortopedik;
import model.SilikonTabanlik;
import util.DosyaIslemleri;

public class OrtopedikDuzenleController implements Initializable {

    private ObservableList<Ortopedik> ortopedikListesi = FXCollections.observableArrayList();
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

    ObservableList<String> tur = FXCollections.observableArrayList("Boyunluk", "Silikon Tabanlık", "Koltuk Değneği");

    public void setOrtopedikListesi(ObservableList<Ortopedik> ortopedikListesi) {
        this.ortopedikListesi = ortopedikListesi;
    }

    Ortopedik secilenOrtopedik;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTur.setItems(tur);
    }

    void duzenleyeDegerGonder(Ortopedik o) {
        secilenOrtopedik = o;
        txtId.setText(String.valueOf(secilenOrtopedik.getId()));
        txtIsim.setText(secilenOrtopedik.getIsim());
        txtAdet.setText(String.valueOf(secilenOrtopedik.getAdet()));
        txtFiyat.setText(String.valueOf(secilenOrtopedik.getFiyat()));
        cmbTur.setValue(secilenOrtopedik.getTur());

    }

    @FXML
    private void ortopedikDuzenleKaydet(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText().trim());
        String isim = txtIsim.getText().trim();
        int fiyat = Integer.parseInt(txtFiyat.getText().trim());
        int adet = Integer.parseInt(txtAdet.getText().trim());
        String tur = cmbTur.getValue();
        if (tur.equals("Boyunluk")) {
            ortopedikListesi.remove(secilenOrtopedik);
            Ortopedik b = new Boyunluk(id, isim, fiyat, adet, tur);
            ortopedikListesi.add(b);
            DosyaIslemleri.dosyayaYaz(ortopedikListesi, "ortopedik");
        } else if (tur.equals("Silikon Tabanlık")) {
            ortopedikListesi.remove(secilenOrtopedik);
            Ortopedik st = new SilikonTabanlik(id, isim, fiyat, adet, tur);
            ortopedikListesi.add(st);
            DosyaIslemleri.dosyayaYaz(ortopedikListesi, "ortopedik");
        } else {
            ortopedikListesi.remove(secilenOrtopedik);
            Ortopedik kd = new KoltukDegnegi(id, isim, fiyat, adet, tur);
            ortopedikListesi.add(kd);
            DosyaIslemleri.dosyayaYaz(ortopedikListesi, "ortopedik");
        }
        
        kapat(event);
        
    }

    @FXML
    private void ortopedikDuzenleIptal(ActionEvent event) {
        kapat(event);
    }
    
    private void kapat(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
