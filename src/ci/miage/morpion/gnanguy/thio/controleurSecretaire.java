package ci.miage.morpion.gnanguy.thio;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import java.nio.file.Paths;

public class controleurSecretaire implements Initializable {


    @FXML
    private Button btn_confirm;

    @FXML
    private Button btn_dash;

    @FXML
    private Button btn_patient;

    @FXML
    private Button btn_patient1;

    @FXML
    private AnchorPane patient_liste;
    @FXML
    private Button btn_profile;

    @FXML
    private Button decobtn;

    @FXML
    private Button btn_rdv;

    @FXML
    private AreaChart<?, ?> dash_chart_DM;

    @FXML
    private AreaChart<?, ?> dash_chart_DP;

    @FXML
    private TableColumn<AppointmentData, String> dash_col_RDVID;

    @FXML
    private TableColumn<AppointmentData, String> dash_col_descrip;

    @FXML
    private TableColumn<AppointmentData, String> dash_col_name;

    @FXML
    private TableColumn<AppointmentData, String> dash_col_rdvdate;

    @FXML
    private TableColumn<AppointmentData, String> dash_col_statut;


    @FXML
    private Label dash_pa;

    @FXML
    private Label dash_pi;

    @FXML
    private Label dash_tp;

    @FXML
    private Label dash_trdv;

    @FXML
    private TableView<AppointmentData> dash_tv;


    @FXML
    private TableView<AppointmentData> dash_rdv;


    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TableView<AppointmentData> dashboard_tableview;

    @FXML
    private Label date;

    @FXML
    private TableColumn<?, ?> docteur_col_action;

    @FXML
    private TableColumn<?, ?> docteur_col_adresse;

    @FXML
    private TableColumn<?, ?> docteur_col_contact;

    @FXML
    private TableColumn<?, ?> docteur_col_docID;

    @FXML
    private TableColumn<?, ?> docteur_col_email;

    @FXML
    private TableColumn<?, ?> docteur_col_genre;

    @FXML
    private TableColumn<?, ?> docteur_col_nom;

    @FXML
    private TableColumn<?, ?> docteur_col_special;

    @FXML
    private TableColumn<?, ?> docteur_col_statut;

    @FXML
    private AnchorPane docteur_form;

    @FXML
    private Label form_courant;

    @FXML
    private BorderPane main_form;

    @FXML
    private Label nav_IDadmin;

    @FXML
    private Label nav_nomutili;



    @FXML
    private AnchorPane patient_form1;

    @FXML
    private AnchorPane patient_form11;

    @FXML
    private Label patients_CP_adresse;

    @FXML
    private Label patients_CP_datecree;

    @FXML
    private Label patients_CP_idpatient;

    @FXML
    private Label patients_CP_mdp;

    @FXML
    private Button patients_IP_addbtn;

    @FXML
    private Label patients_IP_genre;

    @FXML
    private Label patients_IP_nompatient;

    @FXML
    private Label patients_IP_numero;

    @FXML
    private Button patients_IP_savebtn;

    @FXML
    private TextArea patients_adresse;

    @FXML
    private ComboBox<String> patients_genre;

    @FXML
    private TextField patients_idpatient;

    @FXML
    private TextField patients_mdp;

    @FXML
    private TextField patients_nom;

    @FXML
    private TextField patients_numero;

    @FXML
    private TextArea rdv_adresse;

    @FXML
    private Button rdv_btn_inserer;
    @FXML
    private Button rdv_btn_effacer;

    @FXML
    private Button rdv_btn_maj;

    @FXML
    private Button rdv_btn_supprimer;


    @FXML
    private TableColumn<AppointmentData, String> rdv_col_action;

    @FXML
    private TableColumn<AppointmentData, String> rdv_col_contact;

    @FXML
    private TableColumn<AppointmentData, String> rdv_col_date;

    @FXML
    private TableColumn<AppointmentData, String> rdv_col_datemodif;

    @FXML
    private TableColumn<AppointmentData, String> rdv_col_datesupp;

    @FXML
    private TableColumn<AppointmentData, String> rdv_col_descrip;

    @FXML
    private TableColumn<AppointmentData, String> rdv_col_genre;

    @FXML
    private TableColumn<AppointmentData, Integer> rdv_col_idrdv;

    @FXML
    private TableColumn<AppointmentData, String> rdv_col_nom;

    @FXML
    private TableColumn<AppointmentData, String> rdv_col_statut;

    @FXML
    private TextField rdv_description;

    @FXML
    private TextField rdv_diagnostique;

    @FXML
    private AnchorPane rdv_form;

    @FXML
    private ComboBox<String> rdv_genre;

    @FXML
    private TextField rdv_nom;

    @FXML
    private TextField rdv_numero;

    @FXML
    private TextField rdv_rdvID;

    @FXML
    private ComboBox<?> rdv_statut;

    @FXML
    private TableView<AppointmentData> rdv_tableview;

    @FXML
    private TextField rdv_traitement;

    @FXML
    private Label top_nomutili;

    @FXML
    private Circle top_profil;

    public Connection connexion;
    public PreparedStatement preparedStatement;
    public ResultSet resultSet;

    @FXML
    private DatePicker rdv_horaire;

    private final AlertMessage alert = new AlertMessage();

    @FXML
    private AnchorPane profile_form;
    @FXML
    private TextArea profile_adresse;

    @FXML
    private Label profile_adresseemail;

    @FXML
    private Circle profile_cercleimage;

    @FXML
    private Label profile_datecreation;

    @FXML
    private Label profile_docid;

    @FXML
    private TextField profile_email;

    @FXML
    private ComboBox<String> profile_genre;

    @FXML
    private ComboBox<String> profile_grade;

    @FXML
    private TextField profile_iddocteur;

    @FXML
    private Button profile_importimage;

    @FXML
    private Label profile_label_nom;

    @FXML
    private TextField profile_nomupdate;

    @FXML
    private TextField profile_numero;

    @FXML
    private ComboBox<String> profile_specialite;

    @FXML
    private ComboBox<String> profile_statut;
    @FXML
    private Button profile_updateBtn;

    private Image image;


    @FXML
    private TableColumn<patientData,String> patient_col_action;

    @FXML
    private TableColumn<patientData,String> patient_col_contact;

    @FXML
    private TableColumn<patientData,String> patient_col_date;

    @FXML
    private TableColumn<patientData,String> patient_col_datemodif;

    @FXML
    private TableColumn<patientData,String> patient_col_datesupp;

    @FXML
    private TableColumn<patientData,String> patient_col_description;

    @FXML
    private TableColumn<patientData,String> patient_col_genre;

    @FXML
    private TableColumn<patientData,String> patient_col_idpat;

    @FXML
    private TableColumn<patientData,String> patient_col_nom;

    @FXML
    private TableColumn<patientData,String> patient_col_statut;

    @FXML
    private AnchorPane patient_form;


    @FXML
    private AnchorPane patient_form2;


    @FXML
    private TableView<patientData> patient_tableview;


//    private String[] specialite = {"Allergist", "Dermatologist","Generaliste", "Ophthalmologist", "Gynecologist", "Cardiologist"};

    public void dashbboardDisplayIP() {
        String sql = "SELECT COUNT(id) FROM patient WHERE status = 'Inactive' AND docteur = '"
                + Donnee.docteur_id + "'";
        connexion = Database.connectDB();
        int getIP = 0;
        try {
            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                getIP = resultSet.getInt("COUNT(id)");
            }
            dash_pi.setText(String.valueOf(getIP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayTP() {
        String sql = "SELECT COUNT(id) FROM patient WHERE docteur = '"
                + Donnee.docteur_id + "'";
        connexion = Database.connectDB();
        int getTP = 0;
        try {
            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                getTP = resultSet.getInt("COUNT(id)");
            }
            dash_tp.setText(String.valueOf(getTP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashbboardDisplayAP() {
        String sql = "SELECT COUNT(id) FROM patient WHERE status = 'Active' AND docteur = '"
                + Donnee.docteur_id + "'";
        connexion = Database.connectDB();
        int getAP = 0;
        try {
            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                getAP = resultSet.getInt("COUNT(id)");
            }
            dash_pa.setText(String.valueOf(getAP));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void dashbboardDisplayRDV() {
        String sql = "SELECT COUNT(id) FROM rdv WHERE statut = 'Active' AND docteur = '"
                + Donnee.docteur_id + "'";
        connexion = Database.connectDB();
        int getRDV = 0;
        try {
            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                getRDV = resultSet.getInt("COUNT(id)");
            }
            dash_trdv.setText(String.valueOf(getRDV));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileChangeProfile() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image", "*png", "*jpg", "*jpeg"));

        File file = open.showOpenDialog(profile_importimage.getScene().getWindow());

        if (file != null) {
            Donnee.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 128, 103, false, true);
            profile_cercleimage.setFill(new ImagePattern(image));
        }

    }
    ////
    public void profileUpdateBtn() {

        connexion = Database.connectDB();

        if (profile_iddocteur.getText().isEmpty()
                || profile_nomupdate.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_genre.getSelectionModel().getSelectedItem() == null
                || profile_numero.getText().isEmpty()
                || profile_adresse.getText().isEmpty()
                || profile_specialite.getSelectionModel().getSelectedItem() == null
                || profile_grade.getSelectionModel().getSelectedItem() == null
                || profile_statut.getSelectionModel().getSelectedItem() == null) {
            alert.errorMessage("Svp remplissez tous les champs");
        } else {
            // CHECK IF THE PATH IS NULL
            if (Donnee.path == null || "".equals(Donnee.path)) {
                String updateData = "UPDATE docteur SET nom_complet = ?, email = ?"
                        + ", genre = ?, numero = ?, adresse = ?, specialite = ?, status = ?, date_modif = ?"
                        + " WHERE id_docteur = '"
                        + Donnee.docteur_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    preparedStatement = connexion.prepareStatement(updateData);
                    preparedStatement.setString(1, profile_nomupdate.getText());
                    preparedStatement.setString(2, profile_email.getText());
                    preparedStatement.setString(3, profile_genre.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(4, profile_numero.getText());
                    preparedStatement.setString(5, profile_adresse.getText());
                    preparedStatement.setString(6, profile_specialite.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(7, profile_statut.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(8, String.valueOf(sqlDate));

                    preparedStatement.executeUpdate();

                    alert.MessageSucces("Mise a jour avec succes!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                String updateData = "UPDATE docteur SET nom_complet = ?, email = ?"
                        + ", genre = ?, numero = ?, adresse = ?, image = ?, specialite = ?, status = ?,date_modif = ?"
                        + " WHERE id_docteur = '"
                        + Donnee.docteur_id + "'";
                try {
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    preparedStatement = connexion.prepareStatement(updateData);
                    preparedStatement.setString(1, profile_nomupdate.getText());
                    preparedStatement.setString(2, profile_email.getText());
                    preparedStatement.setString(3, profile_genre.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(4, profile_numero.getText());
                    preparedStatement.setString(5, profile_adresse.getText());
                    String path = Donnee.path;
                    path = path.replace("\\", "\\\\");


                    Path transfer = Paths.get(path);



                    Path copy = Paths.get("C:\\Users\\MR_FLEX\\IdeaProjects\\PROJET HOPITAL\\src\\Image\\"+Donnee.docteur_id+".jpg");

                    try {
                        Files.copy(transfer , copy , StandardCopyOption.REPLACE_EXISTING);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    preparedStatement.setString(6, copy.toAbsolutePath().toString());

                    preparedStatement.setString(7, profile_specialite.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(8, profile_statut.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(9, String.valueOf(sqlDate));

                    preparedStatement.executeUpdate();

                    alert.MessageSucces("Mise a jour avec succes!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void profileDisplayImages() {

        String selectData = "SELECT * FROM docteur WHERE id_docteur = '"
                + Donnee.docteur_id + "'";
        String temp_path1 = "";
        String temp_path2 = "";
        connexion = Database.connectDB();

        try {
            preparedStatement = connexion.prepareStatement(selectData);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                temp_path1 = "File:" + resultSet.getString("image");
                temp_path2 = "File:" + resultSet.getString("image");

                if (resultSet.getString("image") != null) {
                    image = new Image(temp_path1, 1012, 22, false, true);
                    top_profil.setFill(new ImagePattern(image));

                    image = new Image(temp_path2, 128, 103, false, true);
                    profile_cercleimage.setFill(new ImagePattern(image));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ////
    public void profileLabels() {
        String selectData = "SELECT * FROM docteur WHERE id_docteur = '"
                + Donnee.docteur_id + "'";
        connexion = Database.connectDB();

        try {
            preparedStatement = connexion.prepareStatement(selectData);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                profile_docid.setText(resultSet.getString("id_docteur"));
                profile_label_nom.setText(resultSet.getString("nom_complet"));
                profile_adresseemail.setText(resultSet.getString("email"));
                profile_datecreation.setText(resultSet.getString("date"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileFields() {
        String selectData = "SELECT * FROM docteur WHERE id_docteur = '"
                + Donnee.docteur_id + "'";

        connexion = Database.connectDB();
        try {
            preparedStatement = connexion.prepareStatement(selectData);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                profile_iddocteur.setText(resultSet.getString("id_docteur"));
                profile_nomupdate.setText(resultSet.getString("nom_complet"));
                profile_email.setText(resultSet.getString("email"));
                profile_genre.getSelectionModel().select(resultSet.getString("genre"));
                profile_numero.setText(resultSet.getString("numero"));
                profile_adresse.setText(resultSet.getString("adresse"));
                profile_specialite.getSelectionModel().select(resultSet.getString("specialite"));
                profile_statut.getSelectionModel().select(resultSet.getString("status"));
                profile_grade.getSelectionModel().select(resultSet.getString("grade"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void profileStatusList() {

        List<String> listSt = new ArrayList<>();

        for (String data : Donnee.statut) {
            listSt.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listSt);
        profile_statut.setItems(listData);
    }
    public void profileGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Donnee.genre) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        profile_genre.setItems(listData);

    }

    public void profileGradeList() {

        List<String> listG = new ArrayList<>();

        for (String data : Donnee.grade) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        profile_grade.setItems(listData);

    }
   // public void profileSpecializedList() {

     //   List<String> listS = new ArrayList<>();

     //   for (String data : specialite) {
        //    listS.add(data);
      //  }

      //  ObservableList listData = FXCollections.observableArrayList(listS);
        //profile_specialite.setItems(listData);
    //}
    private void patientGenderList() {

        List<String> listG = new ArrayList<>();

        for (String data : Donnee.genre) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableList(listG);

        patients_genre.setItems(listData);

    }


    public void rdvInsererBtn(){
//        CHECK IF THE FIELD(S) ARE EMPTY
        if (rdv_rdvID.getText().isEmpty()
                || rdv_nom.getText().isEmpty()
                || rdv_genre.getSelectionModel().getSelectedItem() == null
                || rdv_numero.getText().isEmpty()
                || rdv_description.getText().isEmpty()
                || rdv_adresse.getText().isEmpty()
                || rdv_statut.getSelectionModel().getSelectedItem() == null
                || rdv_horaire.getValue() == null) {
            alert.errorMessage("Please fill the blank fields");
        } else {
            String checkAppointmentID = "SELECT * FROM rdv WHERE rdv_id = "
                    + rdv_rdvID.getText();
            connexion = Database.connectDB();
            try {
                preparedStatement = connexion.prepareStatement(checkAppointmentID);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    alert.errorMessage(rdv_rdvID.getText() + " was already taken");
                } else {
                    String getSpecialized = "";
                    String getDoctorData = "SELECT * FROM docteur WHERE id_docteur = '"
                            + Donnee.docteur_id + "'";

                    preparedStatement = connexion.prepareStatement(getDoctorData);
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        getSpecialized = resultSet.getString("specialite");
                    }

                    String insertData = "INSERT INTO rdv (rdv_id, nom, genre"
                            + ", description, diagnostique, traitement, numero"
                            + ", adresse, date, statut, docteur, specialite, Horaire) "
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    preparedStatement = connexion.prepareStatement(insertData);

                    preparedStatement.setString(1, rdv_rdvID.getText());
                    preparedStatement.setString(2, rdv_nom.getText());
                    preparedStatement.setString(3, (String) rdv_genre.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(4, rdv_description.getText());
                    preparedStatement.setString(5, rdv_diagnostique.getText());
                    preparedStatement.setString(6, rdv_traitement.getText());
                    preparedStatement.setString(7, rdv_numero.getText());
                    preparedStatement.setString(8, rdv_adresse.getText());

                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                    preparedStatement.setString(9, "" + sqlDate);
                    preparedStatement.setString(10, (String) rdv_statut.getSelectionModel().getSelectedItem());
                    preparedStatement.setString(11, Donnee.docteur_id);
                    preparedStatement.setString(12, getSpecialized);
                    preparedStatement.setString(13, "" +rdv_horaire.getValue());

                    preparedStatement.executeUpdate();

                    appointmentShowData();
                    rdvrdvid();
                    appointmentClearBtn();
                    alert.MessageSucces("Ajout avec Succes!");


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public ObservableList<AppointmentData> dashboardAppointmentTableView() {

        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM rdv WHERE docteur = '"
                + Donnee.docteur_id + "'";

        connexion = Database.connectDB();

        try {

            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            AppointmentData aData;
            while (resultSet.next()) {
                aData = new AppointmentData(resultSet.getInt("rdv_id"),
                        resultSet.getString("nom"), resultSet.getString("description"),
                        resultSet.getDate("date"), resultSet.getString("statut"));

                listData.add(aData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }


    private ObservableList<AppointmentData> dashboardGetData;

    public void dashboardDisplayData() {
        dashboardGetData = dashboardAppointmentTableView();

        dash_col_RDVID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        dash_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dash_col_descrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        dash_col_rdvdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        dash_col_statut.setCellValueFactory(new PropertyValueFactory<>("status"));

        dash_rdv.setItems(dashboardGetData);
    }

    public void dashboardNOP() {

        dash_chart_DP.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM patient WHERE docteur = '"
                + Donnee.docteur_id + "' GROUP BY TIMESTAMP(date)";
        connexion = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                chart.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getInt(2)));
            }

            dash_chart_DP.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void dashboardNOA() {

        dash_chart_DM.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM rdv WHERE docteur = '"
                + Donnee.docteur_id + "' GROUP BY TIMESTAMP(date)";
        connexion = Database.connectDB();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                chart.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getInt(2)));
            }

            dash_chart_DM.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void patientConfirmBtn() {

        // CHECK IF SOME OR ALL FIELDS ARE EMPTY
        if (patients_idpatient.getText().isEmpty()
                || patients_nom.getText().isEmpty()
                || patients_genre.getSelectionModel().getSelectedItem() == null
                || patients_numero.getText().isEmpty()
                || patients_mdp.getText().isEmpty()
                || patients_adresse.getText().isEmpty()) {
            alert.errorMessage("SVP remplissez les champs vides");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            // TO DISPLAY THE DATA FROM PERSONAL ACCOUNT
            patients_CP_idpatient.setText(patients_idpatient.getText());
            patients_CP_mdp.setText(patients_mdp.getText());
            patients_CP_datecree.setText(String.valueOf(sqlDate));

            // TO DISPLAY THE DATA FROM PERSONAL INFORMATION
            patients_IP_nompatient.setText(patients_nom.getText());
            patients_IP_genre.setText(patients_genre.getSelectionModel().getSelectedItem());
            patients_IP_numero.setText(patients_numero.getText());
            patients_CP_adresse.setText(patients_adresse.getText());
        }

    }

    public void patientRecordBtn() {
        try {
            // LINK THE NAME OF YOUR FXML FOR RECORD PAGE
            Parent root = FXMLLoader.load(getClass().getResource("FormRecord.fxml"));
            Stage stage = new Stage();

            stage.setTitle("Systeme | Enregistrement des patients");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void patientAddBtn() {

        if (patients_CP_idpatient.getText().isEmpty()
                || patients_mdp.getText().isEmpty()
                || patients_CP_datecree.getText().isEmpty()
                || patients_nom.getText().isEmpty()
                || patients_IP_genre.getText().isEmpty()
                || patients_numero.getText().isEmpty()
                || patients_adresse.getText().isEmpty()) {
            alert.errorMessage("Quelque Chose ne va pas");
        } else {

            connexion = Database.connectDB();
            try {
                String doctorName = "";
                String doctorSpecialized = "";

                 // CHECK IF THE PATIENT ID THAT THE DOCTORS WANT TO INSERT/ADD IS EXISTING ALREADY
                String checkPatientID = "SELECT * FROM patient WHERE id_patient = '"
                        + patients_idpatient.getText() + "'";
                preparedStatement = connexion.prepareStatement(checkPatientID);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    alert.errorMessage(patients_idpatient.getText() + " existe deja");
                } else {
                    String insertData = "INSERT INTO patient (id_patient, password, nom_complet, numero, "
                            + "adresse, date, "
                            + "status,genre) "
                            + "VALUES(?,?,?,?,?,?,?,?)";
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    preparedStatement = connexion.prepareStatement(insertData);
                    preparedStatement.setString(1, patients_CP_idpatient.getText());
                    preparedStatement.setString(2, patients_CP_mdp.getText());
                    preparedStatement.setString(3, patients_IP_nompatient.getText());
                    preparedStatement.setString(4, patients_IP_numero.getText());
                    preparedStatement.setString(5, patients_CP_adresse.getText());
                    preparedStatement.setString(6, "" + sqlDate);
                    preparedStatement.setString(7, "Confirm");
                    preparedStatement.setString(8, patients_IP_genre.getText());

                    preparedStatement.executeUpdate();

                    alert.MessageSucces("Ajout avec succes!");
                    // TO CLEAR ALL FIELDS AND SOME LABELS
                    patientClearFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
// NOW, LETS TRY
        }
    }

    public void patientClearFields() {
        patients_idpatient.clear();
        patients_nom.clear();
        patients_genre.getSelectionModel().clearSelection();
        patients_numero.clear();
        patients_mdp.clear();
        patients_adresse.clear();

        patients_CP_idpatient.setText("");
        patients_CP_mdp.setText("");
        patients_CP_datecree.setText("");

        patients_IP_numero.setText("");
        patients_IP_genre.setText("");
        patients_IP_nompatient.setText("");
        patients_CP_adresse.setText("");
    }
    public void appointmentDeleteBtn() {

        if (rdv_rdvID.getText().isEmpty()) {
            alert.errorMessage("Please select the item first");
        } else {

            String updateData = "UPDATE rdv SET date_supp = ? WHERE rdv_id = '"
                    + rdv_rdvID.getText() + "'";

            connexion = Database.connectDB();

            try {
                java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                if (alert.confirmationMessage("Etes vous sur de vouloir supprimer le rdv ID: "
                        + rdv_rdvID.getText() + "?")) {
                    preparedStatement = connexion.prepareStatement(updateData);

                    preparedStatement.setString(1, String.valueOf(sqlDate));
                    preparedStatement.executeUpdate();

                    appointmentShowData();
                    rdvrdvid();
                    appointmentClearBtn();

                    alert.MessageSucces("Successully Updated!");
                } else {
                    alert.errorMessage("Cancelled.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    public void appointmentUpdateBtn() {

        if (rdv_rdvID.getText().isEmpty()
                || rdv_nom.getText().isEmpty()
                || rdv_genre.getSelectionModel().getSelectedItem() == null
                || rdv_numero.getText().isEmpty()
                || rdv_description.getText().isEmpty()
                || rdv_adresse.getText().isEmpty()
                || rdv_statut.getSelectionModel().getSelectedItem() == null
                || rdv_horaire.getValue() == null) {
            alert.errorMessage("Please fill the blank fields");
        } else {
            // TO GET THE DATE TODAY
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

            String updateData = "UPDATE rdv SET nom = '"
                    + rdv_nom.getText() + "', genre = '"
                    + rdv_genre.getSelectionModel().getSelectedItem() + "', numero = '"
                    + rdv_numero.getText() + "', description = '"
                    + rdv_description.getText() + "', adresse = '"
                    + rdv_adresse.getText() + "', statut = '"
                    + rdv_statut.getSelectionModel().getSelectedItem() + "', horaire = '"
                    + rdv_horaire.getValue() + "', date_modif = '"
                    + sqlDate + "' WHERE rdv_id = '"
                    + rdv_rdvID.getText() + "'";

            connexion= Database.connectDB();

            try {
                if (alert.confirmationMessage("Etes Vous sure de vouloir mettre a jour Le RDV ID: "
                        + rdv_rdvID.getText() + "?")) {
                    preparedStatement = connexion.prepareStatement(updateData);
                    preparedStatement.executeUpdate();

                    appointmentShowData();
                    rdvrdvid();
                    appointmentClearBtn();
                    alert.MessageSucces("Mise a Jour avec Succes");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void appointmentSelect() {

        AppointmentData appData = rdv_tableview.getSelectionModel().getSelectedItem();
        int num = rdv_tableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        rdv_rdvID.setText("" + appData.getAppointmentID());
        rdv_nom.setText(appData.getName());
        rdv_genre.getSelectionModel().select(appData.getGender());
        rdv_numero.setText("" + appData.getMobileNumber());
        rdv_description.setText(appData.getDescription());
        rdv_diagnostique.setText(appData.getDiagnosis());
        rdv_traitement.setText(appData.getTreatment());
        rdv_adresse.setText(appData.getAddress());
        rdv_statut.getSelectionModel().select(appData.getNStatus());

    }


    public void appointmentClearBtn() {
        rdv_rdvID.clear();
        rdv_nom.clear();
        rdv_genre.getSelectionModel().clearSelection();
        rdv_numero.clear();
        rdv_description.clear();
        rdv_traitement.clear();
        rdv_diagnostique.clear();
        rdv_adresse.clear();
        rdv_statut.getSelectionModel().clearSelection();
        rdv_horaire.setValue(null);
    }

    public void rdvrdvid(){
        rdvrdvgetid();
        rdv_rdvID.setText("" +rdvid);
        rdv_rdvID.setDisable(true);

    }
    private Integer rdvid;
    public void rdvrdvgetid(){

        int tempappID = 0;
        String sql = "select MAX(rdv_id) from rdv ";
        connexion = Database.connectDB();
        try {
            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                tempappID = resultSet.getInt("MAX(rdv_id)");
            }

            if(tempappID==0){
                tempappID+=1;
            }else{
                tempappID+=1;
            }

            rdvid = tempappID;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<AppointmentData> appointmentGetData() {

        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM rdv WHERE date_supp IS NULL ";

        connexion = Database.connectDB();

        try {

            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            AppointmentData appData;

            while (resultSet.next()) {
//            Integer appointmentID, String name, String gender,
//            Long mobileNumber, String description, String diagnosis, String treatment, String address,
//            Date date, Date dateModify, Date dateDelete, String status, Date schedule

                appData = new AppointmentData(resultSet.getInt("rdv_id"),
                        resultSet.getString("nom"), resultSet.getString("genre"),
                        resultSet.getLong("numero"), resultSet.getString("description"),
                        resultSet.getString("diagnostique"), resultSet.getString("traitement"),
                        resultSet.getString("adresse"), resultSet.getDate("date"),
                        resultSet.getDate("date_modif"), resultSet.getDate("date_supp"),
                        resultSet.getString("statut"), resultSet.getDate("Horaire"));
                // STORE ALL DATA
                listData.add(appData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<AppointmentData> appoinmentListData;

    public void appointmentShowData() {
        appoinmentListData = appointmentGetData();

        rdv_col_idrdv.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        rdv_col_nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        rdv_col_genre.setCellValueFactory(new PropertyValueFactory<>("gender"));
        rdv_col_contact.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        rdv_col_descrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        rdv_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        rdv_col_datemodif.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        rdv_col_datesupp.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        rdv_col_statut.setCellValueFactory(new PropertyValueFactory<>("status"));

        rdv_tableview.setItems(appoinmentListData);
    }


    public void appointmentGenderList() {
        List<String> listG = new ArrayList<>();

        for (String data : Donnee.genre) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        rdv_genre.setItems(listData);

    }

    public void appointmentStatusList() {
        List<String> listS = new ArrayList<>();

        for (String data : Donnee.statut) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        rdv_statut.setItems(listData);

    }

    public void logoutBtn() {

        try {
            if (alert.confirmationMessage("Etes vous sure de vouloir vous deconnecter ?")) {
                Donnee.docteur_id = "";
                Donnee.docteur_name = "";
                Parent root = FXMLLoader.load(getClass().getResource("vue2.fxml"));
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();

                // TO HIDE YOUR MAIN FORM
                decobtn.getScene().getWindow().hide();

                Donnee.docteur_id = "";
                Donnee.docteur_name = "";
                Donnee.temp_PatientID = 0;
                Donnee.temp_nom = "";
                Donnee.temp_genre = "";
                Donnee.temp_number = Long.parseLong("0");
                Donnee.temp_addresse = "";
                Donnee.temp_statut = "";
                Donnee.temp_date = "";
                Donnee.temp_path = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void runTime(){
        new Thread(){
            public void run(){
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true){

                    try {
                        Thread.sleep(1000);


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Platform.runLater(()->{
                        date.setText(String.valueOf(format.format(new Date())));
                    });

                }
            }
        }.start();
    }

    public void AffichageAdminName(){
        String name = Donnee.docteur_name;
        name = name.substring(0,1).toUpperCase()+name.substring(1);
        nav_nomutili.setText(name);
        nav_IDadmin.setText(Donnee.secre_id);
        top_nomutili.setText(name);
    }

    public void changerPage(ActionEvent event) {
        if (event.getSource() == btn_dash) {
            dashboard_form.setVisible(true);
            patient_form1.setVisible(false);
            rdv_form.setVisible(false);
            profile_form.setVisible(false);

        } else if (event.getSource() == btn_patient) {
            dashboard_form.setVisible(false);
            patient_form1.setVisible(true);
            rdv_form.setVisible(false);
            profile_form.setVisible(false);
            patient_liste.setVisible(false);

        } else if (event.getSource() == btn_patient1) {
            dashboard_form.setVisible(false);
            patient_form1.setVisible(false);
            rdv_form.setVisible(false);
            profile_form.setVisible(false);
            patient_liste.setVisible(true);

        } else if (event.getSource() == btn_rdv) {
            dashboard_form.setVisible(false);
            patient_form1.setVisible(false);
            rdv_form.setVisible(true);
            profile_form.setVisible(false);

        } else if (event.getSource() == btn_profile) {
            dashboard_form.setVisible(false);
            patient_form1.setVisible(false);
            rdv_form.setVisible(false);
            profile_form.setVisible(true);

        }
    }

    public void patientActionButton() {

        connexion = Database.connectDB();
        patientListData = patientGetData();

        Callback<TableColumn<patientData, String>, TableCell<patientData, String>> cellFactory = (TableColumn<patientData, String> param) -> {
            return new TableCell<patientData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Constante");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                patientData pData = patient_tableview.getSelectionModel().getSelectedItem();
                                int num = patient_tableview.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Svp veuillez remplir selectionner un element");
                                    return;
                                }

                                Donnee.temp_PatientID = pData.getPatientID();
                                Donnee.temp_addresse = pData.getAddress();
                                Donnee.temp_nom = pData.getFullName();
                                Donnee.temp_genre = pData.getGender();
                                Donnee.temp_number = pData.getMobileNumber();
                                Donnee.temp_statut = pData.getStatus();

                                // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("EditConstante.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
        };

        patient_col_action.setCellFactory(cellFactory);
        patient_tableview.setItems(patientListData);

    }




    public ObservableList<patientData> patientGetData() {

        ObservableList<patientData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM patient";

        connexion = Database.connectDB();

        try {
            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            patientData pData;

            while (resultSet.next()) {
//                patientData(Integer id, Integer patientID, String password, String fullName, Long mobileNumber
//            , String address, String image, String description, String diagnosis, String treatment
//            , String doctor, String specialized, Date date, Date dateModify
//            , Date dateDelete, String status)
                pData = new patientData(resultSet.getInt("id"), resultSet.getInt("id_patient"),
                        resultSet.getString("password"), resultSet.getString("nom_complet"),
                        resultSet.getLong("numero"), resultSet.getString("genre"),
                        resultSet.getString("adresse"),
                        resultSet.getString("image"), resultSet.getString("description"),
                        resultSet.getString("diagnostique"),
                        resultSet.getString("traitement"), resultSet.getString("docteur"),
                        resultSet.getString("specialite"), resultSet.getDate("date"),
                        resultSet.getDate("date_modif"), resultSet.getDate("date_supp"),
                        resultSet.getString("status"));

                listData.add(pData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<patientData> patientListData;

    public void patientDisplayData() {
        patientListData = patientGetData();

        patient_col_idpat.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        patient_col_nom.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        patient_col_genre.setCellValueFactory(new PropertyValueFactory<>("gender"));
        patient_col_contact.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        patient_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        patient_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        patient_col_datemodif.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        patient_col_datesupp.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        patient_col_statut.setCellValueFactory(new PropertyValueFactory<>("status"));

        patient_tableview.setItems(patientListData);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //AffichageAdminName();
        runTime();

        patientGenderList();
        profileStatusList();
        patientActionButton();
        patientDisplayData();
    }


}
