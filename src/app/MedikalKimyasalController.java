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
import model.Dezenfektan;
import model.EkgJel;
import model.MedikalKimyasal;
import model.Sabun;
import util.DosyaIslemleri;

public class MedikalKimyasalController implements Initializable {

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
    private TableView<MedikalKimyasal> tblMedikalKimyasal;
    @FXML
    private TableColumn<MedikalKimyasal, Integer> tblId;
    @FXML
    private TableColumn<MedikalKimyasal, String> tblIsim;
    @FXML
    private TableColumn<MedikalKimyasal, Integer> tblAdet;
    @FXML
    private TableColumn<MedikalKimyasal, Integer> tblFiyat;
    @FXML
    private TableColumn<MedikalKimyasal, Integer> tblTur;

    ObservableList<String> tur = FXCollections.observableArrayList("El Dezenfektan", "Sabun", "Ekg Jel");
    Dezenfektan d= new Dezenfektan();
    Sabun sb = new Sabun();
    EkgJel ej = new EkgJel();
    MedikalKimyasal mk = new MedikalKimyasal();
    ObservableList<MedikalKimyasal> medikalKimyasalList = FXCollections.observableArrayList();
    @FXML
    private Label toplamDezenfektan;
    @FXML
    private Label toplamEkgJel;
    @FXML
    private Label toplamSabun;
    @FXML
    private Label toplamKayit;
    @FXML
    private Label lblPhDegeri;
    @FXML
    private TextField txtPhDegeri;
    @FXML
    private Label lblAlkolMiktari;
    @FXML
    private TextField txtAlkolMiktari;
    @FXML
    private TableColumn<MedikalKimyasal, Double> tblAlkolOrani;
    @FXML
    private TableColumn<MedikalKimyasal, Double> tblPhDegeri;
    @FXML
    private AnchorPane panelMedikalKimyasal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbTur.setValue("Seçiniz");
        cmbTur.setItems(tur);
        
        lblAlkolMiktari.setVisible(false);
        lblPhDegeri.setVisible(false);
        txtAlkolMiktari.setVisible(false);
        txtAlkolMiktari.setText("0.0");
        txtPhDegeri.setText("0.0");
        txtPhDegeri.setVisible(false);
        
        cmbTur.setOnAction(e->{
        if(cmbTur.getValue().contains("El Dezenfektan")){
            lblAlkolMiktari.setVisible(true);
            txtAlkolMiktari.setVisible(true);
        } else if(cmbTur.getValue().contains("Sabun")){
            lblPhDegeri.setVisible(true);
            txtPhDegeri.setVisible(true);
        }
        });

        medikalKimyasalList = MedikalKimyasal.dosyadanMedikalTekstilGetir();

        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblIsim.setCellValueFactory(new PropertyValueFactory<>("isim"));
        tblAdet.setCellValueFactory(new PropertyValueFactory<>("adet"));
        tblFiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
        tblTur.setCellValueFactory(new PropertyValueFactory<>("tur"));
        tblAlkolOrani.setCellValueFactory(new PropertyValueFactory<>("alkolOrani"));
        tblPhDegeri.setCellValueFactory(new PropertyValueFactory<>("phDegeri"));
        tblMedikalKimyasal.setItems(MedikalKimyasal.dosyadanMedikalTekstilGetir());
        tblMedikalKimyasal.setItems(medikalKimyasalList);
        toplamKayıtGuncelle();
    }

    @FXML
    private void medikalKimyasalEkle(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText().trim());
        String isim = txtIsim.getText().trim();
        int fiyat = Integer.parseInt(txtFiyat.getText().trim());
        int adet = Integer.parseInt(txtAdet.getText().trim());
        double phDegeri = Double.parseDouble(txtPhDegeri.getText().trim());
        double alkolOrani = Double.parseDouble(txtAlkolMiktari.getText().trim());
        String tur = cmbTur.getValue();
        if (tur.equals("El Dezenfektan")) {
            MedikalKimyasal dezenfektan = new Dezenfektan(id, isim, fiyat, adet, tur,alkolOrani);
            medikalKimyasalList.add(dezenfektan);
            DosyaIslemleri.dosyayaYaz(medikalKimyasalList, "medikalkimyasal");
        } else if (tur.equals("Sabun")) {
            MedikalKimyasal sabun = new Sabun(id, isim, fiyat, adet, tur,phDegeri);
            medikalKimyasalList.add(sabun);
            DosyaIslemleri.dosyayaYaz(medikalKimyasalList, "medikalkimyasal");
        } else if (tur.equals("Ekg Jel")) {
            MedikalKimyasal ekgJel = new EkgJel(id, isim, fiyat, adet, tur);
            medikalKimyasalList.add(ekgJel);
            DosyaIslemleri.dosyayaYaz(medikalKimyasalList, "medikalkimyasal");
        } else {
            MedikalKimyasal medikalKimyasal = new MedikalKimyasal(id, isim, fiyat, adet, "Medikal Kimyasal");
            medikalKimyasalList.add(medikalKimyasal);
            DosyaIslemleri.dosyayaYaz(medikalKimyasalList, "medikalkimyasal");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Bilgilendirme");
        alert.setHeaderText(null);
        alert.setContentText("Ürün eklendi");

        alert.showAndWait();
        temizle();
        toplamKayıtGuncelle();

    }

    @FXML
    private void medikalKimyasalSil(ActionEvent event) {
        MedikalKimyasal seciliMedikalKimyasal = tblMedikalKimyasal.getSelectionModel().getSelectedItem();
        tblMedikalKimyasal.getItems().remove(seciliMedikalKimyasal);
        medikalKimyasalList.remove(seciliMedikalKimyasal);
        DosyaIslemleri.dosyayaYaz(medikalKimyasalList, "medikalkimyasal");
        toplamKayıtGuncelle();
    }

    @FXML
    private void medikalKimyasalDuzenle(ActionEvent event) throws IOException {
        if (tblMedikalKimyasal.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("medikalKimyasalDuzenle.fxml"));
        Parent parent = loader.load();
        MedikalKimyasalDuzenleController medikalKimyasalDuzenle = loader.<MedikalKimyasalDuzenleController>getController();
        MedikalKimyasal mk = tblMedikalKimyasal.getSelectionModel().getSelectedItem();
        loader.setController(medikalKimyasalDuzenle);
        medikalKimyasalDuzenle.duzenleyeDegerGonder(mk);
        Stage duzenleStage = new Stage();
        Scene scene = new Scene(parent);
        duzenleStage.setTitle("Medikal Kimyasal Düzenle");
        medikalKimyasalDuzenle.setMedikalKimyasalListesi(medikalKimyasalList);

        duzenleStage.initModality(Modality.APPLICATION_MODAL);
        duzenleStage.setScene(scene);
        duzenleStage.showAndWait();
    }

    @FXML
    private void menuyeDon(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("naviYeni.fxml"));
        panelMedikalKimyasal.getChildren().setAll(pane);
    }

    private void temizle() {
        txtId.clear();
        txtIsim.clear();
        txtAdet.clear();
        txtFiyat.clear();
        cmbTur.setValue("Seçiniz");
        
        txtAlkolMiktari.clear();
        txtPhDegeri.clear();
        lblAlkolMiktari.setVisible(false);
        lblPhDegeri.setVisible(false);
        txtAlkolMiktari.setVisible(false);
        txtPhDegeri.setVisible(false);
        
    }
    
    private void toplamKayıtGuncelle(){
        toplamDezenfektan.setText(String.valueOf(d.toplamKayit()));
        toplamEkgJel.setText(String.valueOf(ej.toplamKayit()));
        toplamSabun.setText(String.valueOf(sb.toplamKayit()));
        toplamKayit.setText(String.valueOf(mk.toplamKayit()));
        
    }

}
