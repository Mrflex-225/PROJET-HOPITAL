package ci.miage.morpion.gnanguy.thio;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableList;

public class ControleurPatient implements Initializable {
    @FXML
    private TextField login_PatientID;

    @FXML
    private CheckBox login_checkBox;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Button login_loginBtn;

    @FXML
    private PasswordField login_mdp;

    @FXML
    private TextField login_showmdp;

    @FXML
    private ComboBox<?> login_user;

    @FXML
    private AnchorPane main_form;

    private Connection connexion;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private AlertMessage alert = new AlertMessage();

    @FXML
    void ConnexionCompte(ActionEvent event) {

        if (login_PatientID.getText().isEmpty()
                || login_mdp.getText().isEmpty()) {
            alert.errorMessage("Incorrect Patient ID/Password");
        } else {

            String sql = "SELECT * FROM patient  WHERE id_patient = ? AND password = ? AND date_supp IS NULL";
            connexion = Database.connectDB();

            try {

                if (!login_showmdp.isVisible()) {
                    if (!login_showmdp.getText().equals(login_mdp.getText())) {
                        login_showmdp.setText(login_mdp.getText());
                    }
                } else {
                    if (!login_showmdp.getText().equals(login_mdp.getText())) {
                        login_mdp.setText(login_showmdp.getText());
                    }
                }

                // CHECK IF THE STATUS OF THE DOCTOR IS CONFIRM
                String checkStatus = "SELECT status FROM patient WHERE id_patient = '"
                        + login_PatientID.getText() + "' AND password = '"
                        + login_mdp.getText() + "' AND status = 'Confirm'";

                preparedStatement = connexion.prepareStatement(checkStatus);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    if (login_mdp.isVisible()) {
                        if (!login_mdp.getText().equals(login_mdp.getText())) {
                            login_showmdp.setText(login_mdp.getText());
                        }
                    } else {
                        if (!login_showmdp.getText().equals(login_mdp.getText())) {
                            login_mdp.setText(login_showmdp.getText());
                        }
                    }

                    alert.errorMessage("Need the confimation of the Admin!");
                } else {
                    preparedStatement = connexion.prepareStatement(sql);
                    preparedStatement.setString(1, login_PatientID.getText());
                    preparedStatement.setString(2, login_mdp.getText());

                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        alert.MessageSucces("Login Successfully!");
                        Donnee.patient_id=Integer.parseInt(login_PatientID.getText());
                        // LINK YOUR PATIENT MAIN FORM
                        Parent root = FXMLLoader.load(getClass().getResource("PatientMainForm.fxml"));
                        Stage stage = new Stage();

                        stage.setScene(new Scene(root));
                        stage.show();

                        // TO HIDE YOUR LOGIN FORM
                        login_loginBtn.getScene().getWindow().hide();
                    } else {
                        alert.errorMessage("Incorrect Patient ID/Password");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void changerPage(ActionEvent event) {
        if(login_user.getSelectionModel().getSelectedItem() == "Admin Portail"){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("vue - Copie.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Systeme de management d'Hopital");
                stage.setMinWidth(350);
                stage.setMinHeight(580);
                stage.setMaxHeight(580);
                stage.setMaxWidth(350);
                stage.setScene(new Scene(root));
                stage.show();
            }catch (Exception e){
                e.printStackTrace();
            }

        }else if(login_user.getSelectionModel().getSelectedItem() == "Docteur Portail"){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("vue2.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Systeme de management d'Hopital");
                stage.setMinWidth(350);
                stage.setMinHeight(580);
                stage.setMaxHeight(580);
                stage.setMaxWidth(350);
                stage.setScene(new Scene(root));
                stage.show();

            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(login_user.getSelectionModel().getSelectedItem() == "Patient Portail"){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("vuepatient.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Systeme de management d'Hopital");
                stage.setMinWidth(350);
                stage.setMinHeight(580);
                stage.setMaxHeight(580);
                stage.setMaxWidth(350);
                stage.setScene(new Scene(root));
                stage.show();

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        login_user.getScene().getWindow().hide();

    }

    @FXML
    void loginshowmdp(ActionEvent event) {
        if (login_checkBox.isSelected()) {
            login_showmdp.setText(login_mdp.getText());
            login_showmdp.setVisible(true);
            login_mdp.setVisible(false);
        } else {
            login_mdp.setText(login_showmdp.getText());
            login_showmdp.setVisible(false);
            login_mdp.setVisible(true);
        }
    }

    public void ListeUtil() {
        List<String> listU = new ArrayList<>();

        for (String data : Utilisateur.user) {
            listU.add(data);
        }

        ObservableList listedonnees = observableList(listU);
        login_user.setItems(listedonnees);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListeUtil();
    }
}
