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
import model.Dezenfektan;
import model.EkgJel;
import model.MedikalKimyasal;
import model.Sabun;
import util.DosyaIslemleri;

public class MedikalKimyasalDuzenleController implements Initializable {

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

    private ObservableList<MedikalKimyasal> medikalKimyasalListesi = FXCollections.observableArrayList();
    ObservableList<String> tur = FXCollections.observableArrayList("El Dezenfektan", "Sabun", "Ekg Jel");
    @FXML
    private Label lblAlkolMiktari;
    @FXML
    private Label lblPhDegeri;
    @FXML
    private TextField txtAlkolMikari;
    @FXML
    private TextField txtPhDegeri;

    public void setMedikalKimyasalListesi(ObservableList<MedikalKimyasal> medikalKimyasalListesi) {
        this.medikalKimyasalListesi = medikalKimyasalListesi;
    }

    MedikalKimyasal secilenKimyasal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTur.setItems(tur);

        lblAlkolMiktari.setVisible(false);
        lblPhDegeri.setVisible(false);
        txtAlkolMikari.setVisible(false);
        txtPhDegeri.setVisible(false);
        txtAlkolMikari.setText("0.0");
        txtAlkolMikari.setText("0.0");

        cmbTur.setOnAction(e -> {
            if (cmbTur.getValue().contains("El Dezenfektan")) {
                lblAlkolMiktari.setVisible(true);
                txtAlkolMikari.setVisible(true);
            } else if (cmbTur.getValue().contains("Sabun")) {
                lblPhDegeri.setVisible(true);
                txtPhDegeri.setVisible(true);
            }
        });
    }

    void duzenleyeDegerGonder(MedikalKimyasal mk) {
        secilenKimyasal = mk;
        txtId.setText(String.valueOf(secilenKimyasal.getId()));
        txtIsim.setText(secilenKimyasal.getIsim());
        txtAdet.setText(String.valueOf(secilenKimyasal.getAdet()));
        txtFiyat.setText(String.valueOf(secilenKimyasal.getFiyat()));
        cmbTur.setValue(secilenKimyasal.getTur());
        if (secilenKimyasal.getTur().equals("Sabun")) {
            lblPhDegeri.setVisible(true);
            txtPhDegeri.setVisible(true);
        } else if (secilenKimyasal.getTur().equals("El Dezenfektan")) {
            lblAlkolMiktari.setVisible(true);
            txtAlkolMikari.setVisible(true);
        } else {
            lblAlkolMiktari.setVisible(false);
            lblPhDegeri.setVisible(false);
            txtAlkolMikari.setVisible(false);
            txtPhDegeri.setVisible(false);
            txtAlkolMikari.setText("0.0");
            txtAlkolMikari.setText("0.0");
        }
    }

    @FXML
    private void medikalKimyasalDuzenleKaydet(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText().trim());
        String isim = txtIsim.getText().trim();
        int fiyat = Integer.parseInt(txtFiyat.getText().trim());
        int adet = Integer.parseInt(txtAdet.getText().trim());
        double phDegeri = Double.parseDouble(txtPhDegeri.getText().trim());
        double alkolOrani = Double.parseDouble(txtAlkolMikari.getText().trim());
        String tur = cmbTur.getValue();
        if (tur.equals("El Dezenfektan")) {
            medikalKimyasalListesi.remove(secilenKimyasal);
            MedikalKimyasal dezenfektan = new Dezenfektan(id, isim, fiyat, adet, tur, alkolOrani);
            medikalKimyasalListesi.add(dezenfektan);
            DosyaIslemleri.dosyayaYaz(medikalKimyasalListesi, "medikalkimyasal");
        } else if (tur.equals("Sabun")) {
            medikalKimyasalListesi.remove(secilenKimyasal);
            MedikalKimyasal sabun = new Sabun(id, isim, fiyat, adet, tur, phDegeri);
            medikalKimyasalListesi.add(sabun);
            DosyaIslemleri.dosyayaYaz(medikalKimyasalListesi, "medikalkimyasal");
        } else if (tur.equals("Ekg Jel")) {
            medikalKimyasalListesi.remove(secilenKimyasal);
            MedikalKimyasal ekgJel = new EkgJel(id, isim, fiyat, adet, tur);
            medikalKimyasalListesi.add(ekgJel);
            DosyaIslemleri.dosyayaYaz(medikalKimyasalListesi, "medikalkimyasal");
        } else {
            medikalKimyasalListesi.remove(secilenKimyasal);
            MedikalKimyasal medikalKimyasal = new MedikalKimyasal(id, isim, fiyat, adet, tur);
            medikalKimyasalListesi.add(medikalKimyasal);
            DosyaIslemleri.dosyayaYaz(medikalKimyasalListesi, "medikalkimyasal");
        }
        kapat(event);
    }

    @FXML
    private void medikalKimyasalDuzenleIptal(ActionEvent event) {
        kapat(event);
    }

    private void kapat(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
