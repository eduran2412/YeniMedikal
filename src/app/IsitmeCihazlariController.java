
package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class IsitmeCihazlariController implements Initializable {

    @FXML
    private TableColumn<?, ?> tblId;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtIsim;
    @FXML
    private TextField txtAdet;
    @FXML
    private TextField txtFiyat;
    @FXML
    private ComboBox<?> cmbTur;
    @FXML
    private TableView<?> tblIsitmeCihazlari;
    @FXML
    private TableColumn<?, ?> tblIsim;
    @FXML
    private TableColumn<?, ?> tblAdet;
    @FXML
    private TableColumn<?, ?> tblFiyat;
    @FXML
    private TableColumn<?, ?> tblTur;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void isitmeCihazlariEkle(ActionEvent event) {
    }

    @FXML
    private void isitmeCihazlariSil(ActionEvent event) {
    }

    @FXML
    private void isitmeCihazlariDuzenle(ActionEvent event) {
    }

    @FXML
    private void menuyeDon(ActionEvent event) {
    }
    
}
