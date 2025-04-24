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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableList;

public class Controleur2 implements Initializable {

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
    private ComboBox<String> login_user;

    @FXML
    private TextField login_utilisateur;

    @FXML
    private AnchorPane main_form;

    @FXML
    private CheckBox register_checkBox;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private Hyperlink register_loginhere;

    @FXML
    private PasswordField register_mdp;

    @FXML
    private TextField register_show_mdp;

    @FXML
    private Button register_signUp;

    @FXML
    private TextField register_utilisateur;

    private AlertMessage alert = new AlertMessage();
    //OUtils database

    public void SupprimerChamp(){
        register_mdp.clear();
        register_utilisateur.clear();
        register_email.clear();
        register_show_mdp.clear();
    }


    public void ConnexionCompte(){
        if(login_utilisateur.getText().isEmpty() || login_mdp.getText().isEmpty()){
            alert.errorMessage("Mot de passe / Nom utilsiateur vide");
        }else {
            String sql = "Select * from admin Where nomutilisateur = ? and mdp = ?";
            connect = Database.connectDB();
            try {
                if(login_mdp.isVisible()){
                    if(!login_mdp.getText().equals(login_mdp.getText())){
                        login_showmdp.setText(login_mdp.getText());
                    }
                }else {
                    if(!login_showmdp.getText().equals(login_mdp.getText())){
                        login_mdp.setText(login_showmdp.getText());
                    }
                }
                preparedStatement = connect.prepareStatement(sql);
                preparedStatement.setString(1,login_utilisateur.getText());
                preparedStatement.setString(2,login_mdp.getText());
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    Donnee.admin_nomutil = login_utilisateur.getText();
                    Donnee.admin_id = resultSet.getString("admin_id");

                    //Donnee.admin_id
                    alert.MessageSucces("Connexion reussie");
                    Parent root = FXMLLoader.load(getClass().getResource("adminform.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setTitle("Systeme de Gestion D'Hopital | Portail Admin");
                    stage.setScene(scene);
                    stage.show();
                    login_loginBtn.getScene().getWindow().hide();

                }else {
                    alert.errorMessage("Mot de passe / Nom utilsiateur incorrect");
                }

                resultSet = preparedStatement.executeQuery();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    public void EnregistrementCompte() throws SQLException {
        if(register_email.getText().isEmpty() || register_utilisateur.getText().isEmpty() || register_mdp.getText().isEmpty()){
            alert.errorMessage("Veuillez remplir tous les champs !!");
        }else {
            String verfiNomutilisateur = "Select * from admin WHERE nomutilisateur = '"
                    + register_utilisateur.getText() +"'";


            Database connection = new Database();
            connect = connection.getConnection();
            try {
                if(register_show_mdp.isVisible()){
                    if(!register_show_mdp.getText().equals(register_mdp.getText())){
                        register_show_mdp.setText(register_mdp.getText());
                    }
                }else {
                    if(!register_show_mdp.getText().equals(register_mdp.getText())){
                        register_mdp.setText(register_show_mdp.getText());
                    }
                }
                preparedStatement = connect.prepareStatement(verfiNomutilisateur);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    alert.errorMessage(register_utilisateur.getText() + " existe deja !");
                }else if (register_mdp.getText().length() < 8) { // CHECK IF THE CHARACTERS OF THE PASSWORD IS LESS THAN TO 8
                    alert.errorMessage("Mot de passe Invalide , Caractere inferieur a 8 ");
                } else {
                    String insertData = "INSERT INTO admin (email, nomutilisateur, mdp, date,admin_id) VALUES(?,?,?,?,?)";

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    preparedStatement = connect.prepareStatement(insertData);
                    preparedStatement.setString(1, register_email.getText());
                    preparedStatement.setString(2, register_utilisateur.getText());
                    preparedStatement.setString(3, register_mdp.getText());
                    preparedStatement.setString(4, String.valueOf(sqlDate));
                    preparedStatement.setString(5, generatemdp.generationmdpadmin(register_utilisateur.getText()));

                    preparedStatement.executeUpdate();

                    alert.MessageSucces("Enregistrez avec succes !");

                    SupprimerChamp();
                    // TO SWITCH THE FORM INTO LOGIN FORM
                    login_form.setVisible(true);
                    register_form.setVisible(false);



                }
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
    }

    public void loginShowmdp(){
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
    private Connection connect ;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

//    connexion connection = new connexion();




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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ListeUtil();
    }
}








