package ci.miage.morpion.gnanguy.thio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("vue - Copie.fxml"));
        Scene scene = new Scene(root);

        stage.setMinWidth(350);
        stage.setMinHeight(580);
        stage.setMaxHeight(580);
        stage.setMaxWidth(350);
        stage.setTitle("Systeme de management hopital");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }
}