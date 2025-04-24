package ci.miage.morpion.gnanguy.thio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class EditExamenFormController implements Initializable{
    @FXML
    private TextField editDoctor_date;

    @FXML
    private TextField editDoctor_diag;
    @FXML
    private TextField editDoctor_obs;

    @FXML
    private TextField editDoctor_examenID;

    @FXML
    private TextField editDoctor_libeleexamen;

    @FXML
    private TextField editDoctor_objexam;

    @FXML
    private TextField editDoctor_resultat;

    @FXML
    private TextField editDoctor_typeexamen;

    private Image image;


    AlertMessage alert =new AlertMessage();

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;



    public void updateBtn() {
        connect = Database.connectDB();

        if (editDoctor_examenID.getText().isEmpty()
                || editDoctor_libeleexamen.getText().isEmpty()
                || editDoctor_objexam.getText().isEmpty()
                || editDoctor_resultat.getText().isEmpty()
                || editDoctor_diag.getText().isEmpty()
                || editDoctor_typeexamen.getText().isEmpty()
                || editDoctor_date.getText().isEmpty()) {
            alert.errorMessage("Svp remplissez tous les champs");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());


                String updateData = "INSERT INTO `examen`(`id_examen`, `lib_examen`, `objectif_examen`, `resultats`, `diagnostic`, `date_examen`, `observation_examen`, `id_patient` , `type_examen`) VALUES ("+editDoctor_examenID.getText()+"" +
                        ",'"+editDoctor_libeleexamen.getText()+"','"+editDoctor_objexam.getText()+"','"+editDoctor_resultat.getText()+"','"+editDoctor_diag.getText()+"','"+String.valueOf(sqlDate)+"','"+editDoctor_obs.getText()+"',"+Donnee.temp_PatientID+",'"+editDoctor_typeexamen.getText()+"')";
                try {
                    if (alert.confirmationMessage("Etes vous sure de vouloir modifier L'Examen ID: " + editDoctor_examenID.getText() + "?")) {
                        prepare = connect.prepareStatement(updateData);
                        prepare.executeUpdate();
                        alert.MessageSucces("Enregistrement avec succes!");
                    } else {
                        alert.errorMessage("Cancelled.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

//        displayDoctorData();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //setField();


    }}
