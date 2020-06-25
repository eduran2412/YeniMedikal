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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.IsitmeCihazlari;
import model.KulakDisiCihaz;
import model.KulakIciCihaz;
import model.SarjEdilebilirCihaz;
import util.DosyaIslemleri;

public class IsitmeCihazlariDuzenleController implements Initializable {

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

    ObservableList<String> tur = FXCollections.observableArrayList("Kulak Dışı Cihaz", "Kulak İçi Cihaz", "Sarj Edilebilir Cihaz");
    private ObservableList<IsitmeCihazlari> isitmeCihazlariListesi = FXCollections.observableArrayList();

    IsitmeCihazlari secilenCihaz;
    @FXML
    private Label lblSarjSuresi;
    @FXML
    private TextField txtSarjSuresi;
    @FXML
    private Label lblPilAdeti;
    @FXML
    private TextField txtPilAdet;

    public void setIsitmeCihazlariListesi(ObservableList<IsitmeCihazlari> isitmeCihazlariListesi) {
        this.isitmeCihazlariListesi = isitmeCihazlariListesi;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTur.setItems(tur);
        
        lblPilAdeti.setVisible(false);
        lblSarjSuresi.setVisible(false);
        txtPilAdet.setVisible(false);
        txtSarjSuresi.setVisible(false);
        txtSarjSuresi.setText("0");
        txtPilAdet.setText("0");
        
        cmbTur.setOnAction(e->{
            if(cmbTur.getValue().contains("Kulak İçi Cihaz") || cmbTur.getValue().contains("Kulak Dışı Cihaz")){
            lblPilAdeti.setVisible(true);
            txtPilAdet.setVisible(true);
        } else if(cmbTur.getValue().contains("Sarj Edilebilir Cihaz")){
            lblSarjSuresi.setVisible(true);
            txtSarjSuresi.setVisible(true);
        }
        });
    }

    @FXML
    private void isitmeCihazlariDuzenleKaydet(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText().trim());
        String isim = txtIsim.getText().trim();
        int fiyat = Integer.parseInt(txtFiyat.getText().trim());
        int adet = Integer.parseInt(txtAdet.getText().trim());
        String tur = cmbTur.getValue();
        int sarjSuresi = Integer.parseInt(txtSarjSuresi.getText().trim());
        int pilAdeti = Integer.parseInt(txtPilAdet.getText().trim());
        if (tur.equals("Kulak Dışı Cihaz")) {
            isitmeCihazlariListesi.remove(secilenCihaz);
            IsitmeCihazlari kulakDisiCihaz = new KulakDisiCihaz(id, isim, fiyat, adet, tur, pilAdeti);
            isitmeCihazlariListesi.add(kulakDisiCihaz);
            DosyaIslemleri.dosyayaYaz(isitmeCihazlariListesi, "isitmecihazlari");
        } else if (tur.equals("Kulak İçi Cihaz")) {
            isitmeCihazlariListesi.remove(secilenCihaz);
            IsitmeCihazlari kulakIciCihaz = new KulakIciCihaz(id, isim, fiyat, adet, tur, pilAdeti);
            isitmeCihazlariListesi.add(kulakIciCihaz);
            DosyaIslemleri.dosyayaYaz(isitmeCihazlariListesi, "isitmecihazlari");
        } else if (tur.equals("Sarj Edilebilir Cihaz")) {
            isitmeCihazlariListesi.remove(secilenCihaz);
            IsitmeCihazlari sarjEdilebilir = new SarjEdilebilirCihaz(id, isim, fiyat, adet, tur, sarjSuresi);
            isitmeCihazlariListesi.add(sarjEdilebilir);
            DosyaIslemleri.dosyayaYaz(isitmeCihazlariListesi, "isitmecihazlari");
        } else {
            isitmeCihazlariListesi.remove(secilenCihaz);
            IsitmeCihazlari isitmeCihazlari = new IsitmeCihazlari(id, isim, fiyat, adet, "İşitme Cihazı");
            isitmeCihazlariListesi.add(isitmeCihazlari);
            DosyaIslemleri.dosyayaYaz(isitmeCihazlariListesi, "isitmecihazlari");
        }
        kapat(event);
    }

    @FXML
    private void isitmeCihazlariDuzenleIptal(ActionEvent event) {
        kapat(event);
    }

    void duzenleyeDegerGonder(IsitmeCihazlari ic) {
        secilenCihaz = ic;
        txtId.setText(String.valueOf(secilenCihaz.getId()));
        txtIsim.setText(secilenCihaz.getIsim());
        txtAdet.setText(String.valueOf(secilenCihaz.getAdet()));
        txtFiyat.setText(String.valueOf(secilenCihaz.getFiyat()));
        cmbTur.setValue(secilenCihaz.getTur());
        if(secilenCihaz.getTur().equals("Kulak Dışı Cihaz")){
            lblPilAdeti.setVisible(true);
            txtPilAdet.setVisible(true);
        } else{
            lblSarjSuresi.setVisible(true);
            txtSarjSuresi.setVisible(true);
        }
    }

    private void kapat(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
