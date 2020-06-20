package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Boyunluk;
import model.KoltukDegnegi;
import model.Ortopedik;
import model.SilikonTabanlik;
import util.DosyaIslemleri;

public class OrtopedikController implements Initializable {

    @FXML
    private TableColumn<Ortopedik, Integer> tblId;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtIsim;
    @FXML
    private TextField txtAdet;
    @FXML
    private TextField txtFiyat;
    @FXML
    private TableView<Ortopedik> tblOrtopedik;
    @FXML
    private TableColumn<Ortopedik, String> tblIsim;
    @FXML
    private TableColumn<Ortopedik, Integer> tblAdet;
    @FXML
    private TableColumn<Ortopedik, Integer> tblFiyat;
    @FXML
    private TableColumn<Ortopedik, String> tblTur;
    @FXML
    private ComboBox<String> cmbTur;

    ObservableList<String> tur = FXCollections.observableArrayList("Boyunluk", "Silikon Tabanlık", "Koltuk Değneği");
    ObservableList<Ortopedik> ortopedikList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTur.setValue("Seçiniz");
        cmbTur.setItems(tur);
        ortopedikList = Ortopedik.dosyadanOrtopedikGetir();

        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblIsim.setCellValueFactory(new PropertyValueFactory<>("isim"));
        tblAdet.setCellValueFactory(new PropertyValueFactory<>("adet"));
        tblFiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
        tblTur.setCellValueFactory(new PropertyValueFactory<>("tur"));
        tblOrtopedik.setItems(Ortopedik.dosyadanOrtopedikGetir());
        tblOrtopedik.setItems(ortopedikList);

    }

    @FXML
    private void ortopedikEkle(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText().trim());
        String isim = txtIsim.getText().trim();
        int fiyat = Integer.parseInt(txtFiyat.getText().trim());
        int adet = Integer.parseInt(txtAdet.getText().trim());
        String tur = cmbTur.getValue();
        if (tur.equals("Boyunluk")) {
            Ortopedik b = new Boyunluk(id, isim, fiyat, adet, tur);
            ortopedikList.add(b);
            DosyaIslemleri.dosyayaYaz(ortopedikList, "ortopedik");
        } else if (tur.equals("Silikon Tabanlık")) {
            Ortopedik st = new SilikonTabanlik(id, isim, fiyat, adet, tur);
            ortopedikList.add(st);
            DosyaIslemleri.dosyayaYaz(ortopedikList, "ortopedik");
        } else {
            Ortopedik kd = new KoltukDegnegi(id, isim, fiyat, adet, "Ortopedik");
            ortopedikList.add(kd);
            DosyaIslemleri.dosyayaYaz(ortopedikList, "ortopedik");
        }
        
        temizle();

    }

    @FXML
    private void ortopedikSil(ActionEvent event) {
        Ortopedik seciliOrtopedik = tblOrtopedik.getSelectionModel().getSelectedItem();
        tblOrtopedik.getItems().remove(seciliOrtopedik);
        ortopedikList.remove(seciliOrtopedik);
        DosyaIslemleri.dosyayaYaz(ortopedikList, "ortopedik");
    }

    @FXML
    private void ortopedikDuzenle(ActionEvent event) throws IOException {
        if (tblOrtopedik.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ortopedikDuzenle.fxml"));
                Parent parent = loader.load();
                OrtopedikDuzenleController ortopedikDuzenle = loader.<OrtopedikDuzenleController>getController();
                Ortopedik o = tblOrtopedik.getSelectionModel().getSelectedItem();
                loader.setController(ortopedikDuzenle);
                ortopedikDuzenle.duzenleyeDegerGonder(o);
                Stage duzenleStage = new Stage();
                Scene scene = new Scene(parent);
                duzenleStage.setTitle("Ortopedik Düzenle");
                ortopedikDuzenle.setOrtopedikListesi(ortopedikList);

                duzenleStage.initModality(Modality.APPLICATION_MODAL);
                duzenleStage.setScene(scene);
                duzenleStage.showAndWait();
        
    }

    @FXML
    private void menuyeDon(ActionEvent event) {
    }
    
    private void temizle(){
        txtId.clear();
        txtIsim.clear();
        txtAdet.clear();
        txtFiyat.clear();
        cmbTur.setValue("Seçiniz");
    }

}
