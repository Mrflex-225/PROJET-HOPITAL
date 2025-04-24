package ci.miage.morpion.gnanguy.thio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class EditPatientControleur implements Initializable {@FXML
private TextArea edit_address;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    private TextField edit_contactNumber;

    @FXML
    private ComboBox<String> edit_gender;

    @FXML
    private TextField edit_name;

    @FXML
    private TextField edit_patientID;

    @FXML
    private ComboBox<String> edit_status;

    @FXML
    private Button edit_updateBtn;
    private AlertMessage alert = new AlertMessage();


    @FXML
    private AnchorPane main_form;
    // CLOSE THE EDITPATIENTFORM FXML FILE AND OPEN IT AGAIN

    public void setField() {
        edit_patientID.setText(String.valueOf(Donnee.temp_PatientID));
        edit_name.setText(Donnee.temp_nom);
        edit_gender.getSelectionModel().select(Donnee.temp_genre);
        edit_contactNumber.setText(String.valueOf(Donnee.temp_number));
        edit_address.setText(Donnee.temp_addresse);
        edit_status.getSelectionModel().select(Donnee.temp_statut);
    }

    public void updateBtn() {

        if (edit_patientID.getText().isEmpty() || edit_name.getText().isEmpty()
                || edit_gender.getSelectionModel().getSelectedItem() == null
                || edit_contactNumber.getText().isEmpty()
                || edit_address.getText().isEmpty()
                || edit_status.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Please fill all blank fields");
        } else {
            String updateData = "UPDATE patient SET nom_complet = ?, genre = ?"
                    + ", numero = ?, adresse = ?, status = ?, date_modif = ? "
                    + "WHERE id_patient = '"
                    + edit_patientID.getText() + "'";
            connect = Database.connectDB();
            try {
                if (alert.confirmationMessage("Etes vous sure de mettre a jour le Patient ID: " + edit_patientID.getText()
                        + "?")) {
                    prepare = connect.prepareStatement(updateData);
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(1, edit_name.getText());
                    prepare.setString(2, edit_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(3, edit_contactNumber.getText());
                    prepare.setString(4, edit_address.getText());
                    prepare.setString(5, edit_status.getSelectionModel().getSelectedItem());
                    prepare.setString(6, String.valueOf(sqlDate));
                    prepare.executeUpdate();
                    alert.MessageSucces("Mise a jour avec succes!");
                } else {
                    alert.errorMessage("Cancelled.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void genderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : Donnee.genre) {
            genderL.add(data);
        }

        ObservableList listData = FXCollections.observableList(genderL);
        edit_gender.setItems(listData);
    }

    public void statusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : Donnee.statut) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableList(statusL);
        edit_status.setItems(listData);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
setField();
statusList();
genderList();
    }
}
