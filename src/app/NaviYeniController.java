/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author erend
 */
public class NaviYeniController implements Initializable {

    @FXML
    private AnchorPane panel2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void naviYeniOrtopedik(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("ortopedik.fxml"));
        panel2.getChildren().setAll(pane);
    }

    @FXML
    private void naviYeniMedikalKimyasal(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("medikalKimyasal.fxml"));
        panel2.getChildren().setAll(pane);
    }

    @FXML
    private void naviYeniMedikalTekstil(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("medikalTekstil.fxml"));
        panel2.getChildren().setAll(pane);
    }

    @FXML
    private void anaMenuyeDon(ActionEvent event) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("navi.fxml"));
        panel2.getChildren().setAll(pane);
    }
    
}
