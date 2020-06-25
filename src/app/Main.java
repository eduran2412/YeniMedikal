package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        /*
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root, 765, 532);
            primaryStage.setScene(scene);
            primaryStage.show();
        
        
        
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("KitapEkle.fxml"));
        panel.getChildren().setAll(pane);
        
        
         AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Main.fxml"));
        panel.getChildren().setAll(pane);
        */
        
        
         AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("navi.fxml"));
            Scene scene = new Scene(root,860,600);
            primaryStage.setScene(scene);
            primaryStage.show();
        
       /* Parent root = FXMLLoader.load(getClass().getResource("medikalKimyasal.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }

}
