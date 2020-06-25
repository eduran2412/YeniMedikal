
package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;


public class NaviController implements Initializable {

    @FXML
    private AnchorPane panel;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void naviMalzeme(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("naviYeni.fxml"));
        panel.getChildren().setAll(pane);
    }

    @FXML
    private void naviCihaz(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("isitmeCihazlari.fxml"));
        panel.getChildren().setAll(pane);
    }
    
}
