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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableList;

public class ControleurPageDocteur2 implements Initializable {

    private final AlertMessage alert = new AlertMessage();
    @FXML
    private TextField login_DoctorID;
    @FXML
    private CheckBox login_checkBox;
    @FXML
    private AnchorPane login_form;
    @FXML
    private Button login_loginBtn;
    @FXML
    private PasswordField login_mdp;
    @FXML
    private Hyperlink login_register;
    @FXML
    private TextField login_showmdp;
    @FXML
    private ComboBox<?> login_user;
    @FXML
    private AnchorPane main_form;
    @FXML
    private CheckBox register_checkBox;
    @FXML
    private TextField register_doctorID;
    @FXML
    private TextField register_email;
    @FXML
    private AnchorPane register_form;
    @FXML
    private Hyperlink register_loginhere;
    private Connection connexion;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @FXML
    private PasswordField register_mdp;

    @FXML
    private TextField register_nomcomplet;

    @FXML
    private TextField register_show_mdp;

    @FXML
    private Button register_signUp;



    @FXML
    void ConnexionCompte() {

        if (login_DoctorID.getText().isEmpty()
                || login_mdp.getText().isEmpty()) {
            alert.errorMessage("Incorrect Doctor ID/Password");
        } else {

            String sql = "SELECT * FROM docteur WHERE id_docteur = ? AND mdp = ? AND date_supp IS NULL";
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
                String checkStatus = "SELECT status FROM docteur WHERE id_docteur = '"
                        + login_DoctorID.getText() + "' AND mdp = '"
                        + login_mdp.getText() + "' AND status = 'Confirm'";

                preparedStatement = connexion.prepareStatement(checkStatus);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    if(login_mdp.isVisible()){
                        if(!login_mdp.getText().equals(login_mdp.getText())){
                            login_showmdp.setText(login_mdp.getText());
                        }
                    }else {
                        if(!login_showmdp.getText().equals(login_mdp.getText())){
                            login_mdp.setText(login_showmdp.getText());
                        }
                    }

                    alert.errorMessage("Need the confimation of the Admin!");
                } else {
                    preparedStatement = connexion.prepareStatement(sql);
                    preparedStatement.setString(1, login_DoctorID.getText());
                    preparedStatement.setString(2, login_mdp.getText());

                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        Donnee.docteur_name = resultSet.getString("nom_complet");
                        Donnee.docteur_id = resultSet.getString("id_docteur");
                        alert.MessageSucces("Login Successfully!");
                        Parent root = FXMLLoader.load(getClass().getResource("docmainform.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("Systeme gestion Hopital | Doctor Main Form");
                        stage.setScene(new Scene(root));
                        stage.show();
                        login_loginBtn.getScene().getWindow().hide();
                    } else {
                        alert.errorMessage("Incorrect Doctor ID/Password");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    @FXML
    void EnregistrementCompte(ActionEvent event) {
        if (register_nomcomplet.getText().isEmpty()
                || register_email.getText().isEmpty()
                || register_doctorID.getText().isEmpty()
                || register_mdp.getText().isEmpty()) {
            alert.errorMessage("Please fill all blank fields");
        } else {

            String checkDoctorID = "SELECT * FROM docteur WHERE id_docteur = '"
                    + register_doctorID.getText() + "'"; // LETS CREATE OUR TABLE FOR DOCTOR FIRST

            connexion = Database.connectDB();

            try {

                if (!register_show_mdp.isVisible()) {
                    if (!register_show_mdp.getText().equals(register_mdp.getText())) {
                        register_show_mdp.setText(register_mdp.getText());
                    }
                } else {
                    if (!register_show_mdp.getText().equals(register_mdp.getText())) {
                        register_mdp.setText(register_show_mdp.getText());
                    }
                }

                preparedStatement = connexion.prepareStatement(checkDoctorID);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    alert.errorMessage(register_doctorID.getText() + " is already taken");
                } else if (register_mdp.getText().length() < 8) {
                    alert.errorMessage("Invalid password, at least 8 characters needed");
                } else {

                    String insertData = "INSERT INTO docteur (nom_complet, email,id_docteur, mdp, date, status) "
                            + "VALUES(?,?,?,?,?,?)";

                    preparedStatement = connexion.prepareStatement(insertData);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    preparedStatement.setString(1, register_nomcomplet.getText());
                    preparedStatement.setString(2, register_email.getText());
                    preparedStatement.setString(3, register_doctorID.getText());
                    preparedStatement.setString(4, register_mdp.getText());
                    preparedStatement.setString(5, String.valueOf(sqlDate));
                    preparedStatement.setString(6, "Confirm");

                    preparedStatement.executeUpdate();

                    alert.MessageSucces("Registered Succesfully!");

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void ChangerFormulaire(ActionEvent event){
        if(event.getSource() == register_loginhere){
            //FORM D ENREGISTREMENT SERA VISIBLE
            register_form.setVisible(false);
            login_form.setVisible(true);
        } else if (event.getSource() == login_register) {
            //FORM CONNEXION SERA VISIBLE
            register_form.setVisible(true);
            login_form.setVisible(false);
        }
    }

    @FXML
    void loginshowmdp() {

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

    public void showmdp(){
        if (register_checkBox.isSelected()) {
            register_show_mdp.setText(register_mdp.getText());
            register_show_mdp.setVisible(true);
            register_mdp.setVisible(false);
        } else {
            register_mdp.setText(register_show_mdp.getText());
            register_show_mdp.setVisible(false);
            register_mdp.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListeUtil();
    }

    public void ListeUtil(){
        List<String> listU = new ArrayList<>();

        for(String data : Utilisateur.user){
            listU.add(data);
        }

        ObservableList listedonnees = observableList(listU);
        login_user.setItems(listedonnees);
    }

    public void changerPage(){
        if(login_user.getSelectionModel().getSelectedItem() == "Admin Portail"){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("vueadmin.fxml"));
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
                Parent root = FXMLLoader.load(getClass().getResource("vuedocteur.fxml"));
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
                Parent root = FXMLLoader.load(getClass().getResource("vuedespatients.fxml"));
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



}
