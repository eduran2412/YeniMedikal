
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
import model.Gozluk;
import model.KoruyucuMaske;
import model.KoruyucuOnluk;
import model.MedikalTekstil;
import model.Ortopedik;
import util.DosyaIslemleri;


public class MedikalTekstilController implements Initializable {

    
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtIsim;
    @FXML
    private TextField txtAdet;
    @FXML
    private TextField txtFiyat;
    @FXML
    private ComboBox<String> cmbTur;
    @FXML
    private TableView<MedikalTekstil> tblMedikalTekstil;
    @FXML
    private TableColumn<MedikalTekstil, Integer> tblId;
    @FXML
    private TableColumn<MedikalTekstil, String> tblIsim;
    @FXML
    private TableColumn<MedikalTekstil, Integer> tblAdet;
    @FXML
    private TableColumn<MedikalTekstil, Integer> tblFiyat;
    @FXML
    private TableColumn<MedikalTekstil, String> tblTur;
    
    ObservableList<String> tur = FXCollections.observableArrayList("Gözlük", "Koruyucu Maske", "Koruyucu Önlük");
    ObservableList<MedikalTekstil> medikalTekstilList = FXCollections.observableArrayList();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTur.setValue("Seçiniz");
        cmbTur.setItems(tur);
        medikalTekstilList = MedikalTekstil.dosyadanMedikalTekstilGetir();
        
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblIsim.setCellValueFactory(new PropertyValueFactory<>("isim"));
        tblAdet.setCellValueFactory(new PropertyValueFactory<>("adet"));
        tblFiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
        tblTur.setCellValueFactory(new PropertyValueFactory<>("tur"));
        tblMedikalTekstil.setItems(MedikalTekstil.dosyadanMedikalTekstilGetir());
        tblMedikalTekstil.setItems(medikalTekstilList);
    }    

    @FXML
    private void medikalTekstilEkle(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText().trim());
        String isim = txtIsim.getText().trim();
        int fiyat = Integer.parseInt(txtFiyat.getText().trim());
        int adet = Integer.parseInt(txtAdet.getText().trim());
        String tur = cmbTur.getValue();
        if(tur.equals("Gozluk")){
            MedikalTekstil gozluk = new Gozluk(id, isim, fiyat, adet, tur);
            medikalTekstilList.add(gozluk);
            DosyaIslemleri.dosyayaYaz(medikalTekstilList, "medikaltekstil");
        }else if(tur.equals("Koruyucu Maske")){
            MedikalTekstil koruyucuMaske = new KoruyucuMaske(id, isim, fiyat, adet, tur);
            medikalTekstilList.add(koruyucuMaske);
            DosyaIslemleri.dosyayaYaz(medikalTekstilList, "medikaltekstil");
        } else if(tur.equals("Koruyucu Önlük")){
            MedikalTekstil koruyucuOnluk = new KoruyucuOnluk(id, isim, fiyat, adet, tur);
            medikalTekstilList.add(koruyucuOnluk);
            DosyaIslemleri.dosyayaYaz(medikalTekstilList, "medikaltekstil");
        } else{
            MedikalTekstil medikalTekstil = new MedikalTekstil(id, isim, fiyat, adet, "Medikal Tekstil");
            medikalTekstilList.add(medikalTekstil);
            DosyaIslemleri.dosyayaYaz(medikalTekstilList, "medikaltekstil");
        }
        
        temizle();
    }

    @FXML
    private void medikalTekstilSil(ActionEvent event) {
        MedikalTekstil seciliMedikalTekstil = tblMedikalTekstil.getSelectionModel().getSelectedItem();
        tblMedikalTekstil.getItems().remove(seciliMedikalTekstil);
        medikalTekstilList.remove(seciliMedikalTekstil);
        DosyaIslemleri.dosyayaYaz(medikalTekstilList, "medikaltekstil");
    }

    @FXML
    private void medikalTekstilDuzenle(ActionEvent event) throws IOException {
        if (tblMedikalTekstil.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("medikalTekstilDuzenle.fxml"));
                Parent parent = loader.load();
                MedikalTekstilDuzenleController medikalTekstilDuzenle = loader.<MedikalTekstilDuzenleController>getController();
                MedikalTekstil mt = tblMedikalTekstil.getSelectionModel().getSelectedItem();
                loader.setController(medikalTekstilDuzenle);
                medikalTekstilDuzenle.duzenleyeDegerGonder(mt);
                Stage duzenleStage = new Stage();
                Scene scene = new Scene(parent);
                duzenleStage.setTitle("Medikal Tekstil Düzenle");
                medikalTekstilDuzenle.setMedikalTekstilListesi(medikalTekstilList);

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
