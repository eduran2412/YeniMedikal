
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.IsitmeCihazlari;
import model.KulakDisiCihaz;
import model.KulakIciCihaz;
import model.MedikalKimyasal;
import model.SarjEdilebilirCihaz;
import util.DosyaIslemleri;


public class IsitmeCihazlariController implements Initializable {

    @FXML
    private TableColumn<IsitmeCihazlari, Integer> tblId;
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
    private TableView<IsitmeCihazlari> tblIsitmeCihazlari;
    @FXML
    private TableColumn<IsitmeCihazlari, String> tblIsim;
    @FXML
    private TableColumn<IsitmeCihazlari, Integer> tblAdet;
    @FXML
    private TableColumn<IsitmeCihazlari, Integer> tblFiyat;
    @FXML
    private TableColumn<IsitmeCihazlari, String> tblTur;

    ObservableList<String> tur = FXCollections.observableArrayList("Kulak Dışı Cihaz", "Kulak İçi Cihaz", "Sarj Edilebilir Cihaz");
    KulakDisiCihaz kd = new KulakDisiCihaz();
    KulakIciCihaz ki = new KulakIciCihaz();
    SarjEdilebilirCihaz sarjEdilebilir = new SarjEdilebilirCihaz();
    IsitmeCihazlari ic = new IsitmeCihazlari();
    ObservableList<IsitmeCihazlari> isitmeCihazlariList = FXCollections.observableArrayList();
    @FXML
    private Label toplamKulakDisi;
    @FXML
    private Label toplamKulakIci;
    @FXML
    private Label toplamSarjEdilebilir;
    @FXML
    private Label toplamKayit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTur.setValue("Seçiniz");
        cmbTur.setItems(tur);
        
        isitmeCihazlariList = IsitmeCihazlari.dosyadanIsitmeCihaziGetir();
        
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblIsim.setCellValueFactory(new PropertyValueFactory<>("isim"));
        tblAdet.setCellValueFactory(new PropertyValueFactory<>("adet"));
        tblFiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
        tblTur.setCellValueFactory(new PropertyValueFactory<>("tur"));
        tblIsitmeCihazlari.setItems(IsitmeCihazlari.dosyadanIsitmeCihaziGetir());
        tblIsitmeCihazlari.setItems(isitmeCihazlariList);
        toplamKayıtGuncelle();
    }    

    @FXML
    private void isitmeCihazlariEkle(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText().trim());
        String isim = txtIsim.getText().trim();
        int fiyat = Integer.parseInt(txtFiyat.getText().trim());
        int adet = Integer.parseInt(txtAdet.getText().trim());
        String tur = cmbTur.getValue();
        if (tur.equals("Kulak Dışı Cihaz")) {
            IsitmeCihazlari kulakDisiCihaz = new KulakDisiCihaz(id, isim, fiyat, adet, tur);
            isitmeCihazlariList.add(kulakDisiCihaz);
            DosyaIslemleri.dosyayaYaz(isitmeCihazlariList, "isitmecihazlari");
        } else if (tur.equals("Kulak İçi Cihaz")) {
            IsitmeCihazlari kulakIciCihaz = new KulakIciCihaz(id, isim, fiyat, adet, tur);
            isitmeCihazlariList.add(kulakIciCihaz);
            DosyaIslemleri.dosyayaYaz(isitmeCihazlariList, "isitmecihazlari");
        } else if (tur.equals("Sarj Edilebilir Cihaz")) {
            IsitmeCihazlari sarjEdilebilir = new SarjEdilebilirCihaz(id, isim, fiyat, adet, tur);
            isitmeCihazlariList.add(sarjEdilebilir);
            DosyaIslemleri.dosyayaYaz(isitmeCihazlariList, "isitmecihazlari");
        } else {
            IsitmeCihazlari isitmeCihazlari = new IsitmeCihazlari(id, isim, fiyat, adet, "İşitme Cihazı");
            isitmeCihazlariList.add(isitmeCihazlari);
            DosyaIslemleri.dosyayaYaz(isitmeCihazlariList, "isitmecihazlari");
        }
        toplamKayıtGuncelle();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bilgilendirme");
        alert.setHeaderText(null);
        alert.setContentText("Ürün eklendi");

        alert.showAndWait();
        temizle();
    }

    @FXML
    private void isitmeCihazlariSil(ActionEvent event) {
        IsitmeCihazlari seciliIsitmeCihazi = tblIsitmeCihazlari.getSelectionModel().getSelectedItem();
        tblIsitmeCihazlari.getItems().remove(seciliIsitmeCihazi);
        isitmeCihazlariList.remove(seciliIsitmeCihazi);
        DosyaIslemleri.dosyayaYaz(isitmeCihazlariList, "isitmecihazlari");
        toplamKayıtGuncelle();
    }

    @FXML
    private void isitmeCihazlariDuzenle(ActionEvent event) throws IOException {
        if (tblIsitmeCihazlari.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("isitmeCihazlariDuzenle.fxml"));
        Parent parent = loader.load();
        IsitmeCihazlariDuzenleController isitmeCihaziDuzenle = loader.<IsitmeCihazlariDuzenleController>getController();
        IsitmeCihazlari ic = tblIsitmeCihazlari.getSelectionModel().getSelectedItem();
        loader.setController(isitmeCihaziDuzenle);
        isitmeCihaziDuzenle.duzenleyeDegerGonder(ic);
        Stage duzenleStage = new Stage();
        Scene scene = new Scene(parent);
        duzenleStage.setTitle("İşitme Cihazi Düzenle");
        isitmeCihaziDuzenle.setIsitmeCihazlariListesi(isitmeCihazlariList);

        duzenleStage.initModality(Modality.APPLICATION_MODAL);
        duzenleStage.setScene(scene);
        duzenleStage.showAndWait();
    }

    @FXML
    private void menuyeDon(ActionEvent event) {
    }
    
    private void temizle() {
        txtId.clear();
        txtIsim.clear();
        txtAdet.clear();
        txtFiyat.clear();
        cmbTur.setValue("Seçiniz");
    }
    
     private void toplamKayıtGuncelle(){
        toplamKulakDisi.setText(String.valueOf(kd.toplamKayit()));
        toplamKulakIci.setText(String.valueOf(ki.toplamKayit()));
        toplamSarjEdilebilir.setText(String.valueOf(sarjEdilebilir.toplamKayit()));
        toplamKayit.setText(String.valueOf(ic.toplamKayit()));
        
    }
    
}
