
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

public class OrtopedikController implements Initializable {

    @FXML
    private AnchorPane tblId;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtIsim;
    @FXML
    private TextField txtAdet;
    @FXML
    private TextField txtFiyat;
    @FXML
    private TableView<?> tblOrtopedik;
    @FXML
    private TableColumn<?, ?> tblIsim;
    @FXML
    private TableColumn<?, ?> tblAdet;
    @FXML
    private TableColumn<?, ?> tblFiyat;
    @FXML
    private TableColumn<?, ?> tblTur;
    @FXML
    private ComboBox<?> cmbTur;

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ortopedikEkle(ActionEvent event) {
    }

    @FXML
    private void ortopedikSil(ActionEvent event) {
    }

    @FXML
    private void ortopedikDuzenle(ActionEvent event) {
    }

    @FXML
    private void menuyeDon(ActionEvent event) {
    }
    
}
