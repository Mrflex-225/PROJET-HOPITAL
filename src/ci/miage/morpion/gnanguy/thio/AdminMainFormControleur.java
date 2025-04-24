package ci.miage.morpion.gnanguy.thio;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import java.sql.*;
import java.util.Date;

import static javafx.collections.FXCollections.observableList;




public class AdminMainFormControleur implements Initializable {
    @FXML
    private Button btn_dash;

    @FXML
    private Button btn_doc;

    @FXML
    private Button btn_patient;

    @FXML
    private Button btn_deco;

    @FXML
    private Button btn_pay;

    @FXML
    private Button btn_profil;
    @FXML
    private Button btn_rdv;

    @FXML
    private Label dash_ad;

    @FXML
    private TableView<DoctorData> dash_tv;

    @FXML
    private AreaChart<DoctorData,String> dash_chart_DM;

    @FXML
    private AreaChart<DoctorData,String> dash_chart_DP;

    @FXML
    private TableColumn<DoctorData, String> dash_col_IDD;

    @FXML
    private TableColumn<DoctorData, String> dash_col_name;

    @FXML
    private TableColumn<DoctorData, String> dash_col_special;

    @FXML
    private TableColumn<DoctorData, String> dash_col_statut;

    @FXML
    private Label dash_pa;

    @FXML
    private Label dash_tp;

    @FXML
    private Label dash_trdv;



    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TableView<DoctorData> dashboard_tableview;

    @FXML
    private Label date;

    @FXML
    private TableColumn<DoctorData, String> docteur_col_action;

    @FXML
    private TableColumn<DoctorData, String> docteur_col_adresse;

    @FXML
    private TableColumn<DoctorData, String> docteur_col_contact;

    @FXML
    private TableColumn<DoctorData, String> docteur_col_docID;

    @FXML
    private TableColumn<DoctorData, String> docteur_col_email;

    @FXML
    private TableColumn<DoctorData, String> docteur_col_genre;

    @FXML
    private TableColumn<DoctorData, String> docteur_col_nom;

    @FXML
    private TableColumn<DoctorData, String> docteur_col_special;

    @FXML
    private TableColumn<DoctorData, String> docteur_col_statut;

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
    private TableView<patientData> patient_tableview;

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
    private TableColumn<AppointmentData, String> rdv_col_idrdv;

    @FXML
    private TableColumn<AppointmentData, String> rdv_col_nom;

    @FXML
    private TableColumn<AppointmentData, String> rdv_col_statut;

    @FXML
    private AnchorPane rdv_form;

    @FXML
    private TableView<AppointmentData> rdv_tableview;

    @FXML
    private Label top_nomutili;

    @FXML
    private Circle top_profil;


    @FXML
    private Label profile_adminid;

    @FXML
    private Label profile_adresseemail;

    @FXML
    private Circle profile_cercleimage;

    @FXML
    private Label profile_datecreation;

    @FXML
    private TextField profile_email;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private TextField profile_idadmin;

    @FXML
    private Button profile_import;

    @FXML
    private Label profile_label_nom;

    @FXML
    private TextField profile_nomupdate;

    @FXML
    private ComboBox<String> profile_statut;

    @FXML
    private Button profile_updateBtn;


    @FXML
    private Label paiement_IDD;

    @FXML
    private Label paiement_IDP;

    @FXML
    private Circle paiement_circle;

    @FXML
    private TableColumn<patientData,String> paiement_col_IDP;
    @FXML
    private TableColumn<patientData,String> paiement_col_date;

    @FXML
    private TableColumn<patientData,String> paiement_col_diagnostic;

    @FXML
    private TableColumn<patientData,String> paiement_col_doc;

    @FXML
    private TableColumn<patientData,String> paiement_col_genre;

    @FXML
    private TableColumn<patientData,String> paiement_col_name;

    @FXML
    private Label paiement_date;

    @FXML
    private AnchorPane paiement_form;

    @FXML
    private Label paiement_patientNom;

    @FXML
    private TableView<patientData> paiement_tableview;

    @FXML
    private Button btn_cree;



    private Connection connexion;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    AlertMessage alertMessage = new AlertMessage();

    Image image;

    public void dashboardAD() {

        String sql = "SELECT COUNT(id) FROM docteur WHERE status = 'Active' AND date_supp IS NULL";

        connexion = Database.connectDB();

        int tempAD = 0;
        try {

            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tempAD = resultSet.getInt("COUNT(id)");
            }
            dash_ad.setText(String.valueOf(tempAD));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void decoBtn(){
        try{
            if(alertMessage.confirmationMessage("Etes vous sure de quitter ?")){
                Parent root = FXMLLoader.load(getClass().getResource("vue - Copie.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                btn_deco.getScene().getWindow().hide();



            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void creeUt(){
        try{
                Parent root = FXMLLoader.load(getClass().getResource("vueadmin.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void dashboardTP() {

        String sql = "SELECT COUNT(id) FROM patient WHERE date_supp IS NULL";

        connexion = Database.connectDB();

        int tempTP = 0;
        try {

            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tempTP = resultSet.getInt("COUNT(id)");
            }
            dash_tp.setText(String.valueOf(tempTP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardAP() {

        String sql = "SELECT COUNT(id) FROM patient WHERE date_supp IS NULL AND status = 'Active'";

        connexion= Database.connectDB();

        int tempAP = 0;
        try {

            preparedStatement= connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tempAP = resultSet.getInt("COUNT(id)");
            }
            dash_pa.setText(String.valueOf(tempAP));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTA() {

        String sql = "SELECT COUNT(id) FROM rdv WHERE date_supp IS NULL";

        connexion = Database.connectDB();

        int tempTA = 0;
        try {

            preparedStatement = connexion.prepareStatement(sql);
            resultSet= preparedStatement.executeQuery();

            if (resultSet.next()) {
                tempTA = resultSet.getInt("COUNT(id)");
            }
            dash_trdv.setText(String.valueOf(tempTA));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<DoctorData> dashboardGetDoctorData() {

        ObservableList<DoctorData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM docteur WHERE date_supp IS NULL";

        connexion = Database.connectDB();

        try {

            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            DoctorData dData;

            while (resultSet.next()) {
//                DoctorData(String doctorID, String fullName, String specialized, String status)
                dData = new DoctorData(resultSet.getString("id_docteur"),
                        resultSet.getString("nom_complet"), resultSet.getString("specialite"),
                        resultSet.getString("status"));

                listData.add(dData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<DoctorData> dashboardGetDoctorListData;

    public void dashboardGetDoctorDisplayData() {
        dashboardGetDoctorListData = dashboardGetDoctorData();

        dash_col_IDD.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        dash_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        dash_col_special.setCellValueFactory(new PropertyValueFactory<>("specialized"));
        dash_col_statut.setCellValueFactory(new PropertyValueFactory<>("status"));

        dash_tv .setItems(dashboardGetDoctorListData);

    }

    public void dashboardPatientDataChart() {
        dash_chart_DP.getData().clear();

        String selectData = "SELECT date, COUNT(id) FROM patient WHERE date_supp IS NULL GROUP BY TIMESTAMP(datE)";

        connexion = Database.connectDB();
        XYChart.Series chart = new XYChart.Series<>();

        try {
            preparedStatement = connexion.prepareStatement(selectData);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                chart.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getInt(2)));
            }

            dash_chart_DP.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardDoctorDataChart() {
        dash_chart_DM.getData().clear();

        String selectData = "SELECT date, COUNT(id) FROM docteur WHERE date_supp IS NULL GROUP BY TIMESTAMP(date)";

        connexion = Database.connectDB();
        XYChart.Series chart = new XYChart.Series<>();

        try {
            preparedStatement = connexion.prepareStatement(selectData);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                chart.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getInt(2)));
            }

            dash_chart_DM.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<DoctorData> doctorGetData() {
        ObservableList<DoctorData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM docteur";

        connexion = Database.connectDB();

        try {
            preparedStatement= connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            DoctorData dData;
            while (resultSet.next()) {
//                DoctorData(Integer id, String doctorID, String password, String fullName,
//            String email, String gender, Long mobileNumber, String specialized, String address,
//            String image, Date date, Date dateModify, Date dateDelete, String status)
                dData = new DoctorData(resultSet.getInt("id"), resultSet.getString("id_docteur"),
                        resultSet.getString("mdp"), resultSet.getString("nom_complet"),
                        resultSet.getString("email"), resultSet.getString("genre"),
                        resultSet.getLong("numero"), resultSet.getString("specialite"),
                        resultSet.getString("adresse"), resultSet.getString("image"),
                        resultSet.getDate("date"), resultSet.getDate("date_modif"),
                        resultSet.getDate("date_supp"), resultSet.getString("status"));

                listData.add(dData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<DoctorData> doctorListData;

    public void doctorDisplayData() {
        doctorListData = doctorGetData();

        docteur_col_docID.setCellValueFactory(new PropertyValueFactory<>("doctorID"));
        docteur_col_nom.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        docteur_col_genre.setCellValueFactory(new PropertyValueFactory<>("gender"));
        docteur_col_contact.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        docteur_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        docteur_col_special.setCellValueFactory(new PropertyValueFactory<>("specialized"));
        docteur_col_adresse.setCellValueFactory(new PropertyValueFactory<>("address"));
        docteur_col_statut.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboard_tableview.setItems(doctorListData);

    }

    public void doctorActionButton() {

        connexion = Database.connectDB();
        doctorListData = doctorGetData();

        Callback<TableColumn<DoctorData, String>, TableCell<DoctorData, String>> cellFactory = (TableColumn<DoctorData, String> param) -> {
            final TableCell<DoctorData, String> cell = new TableCell<DoctorData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Editer");
                        Button removeButton = new Button("Supprimer");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                DoctorData pData = dashboard_tableview.getSelectionModel().getSelectedItem();
                                int num = dashboard_tableview.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alertMessage.errorMessage("S'il vous selectionnez un element");
                                    return;
                                }

                                Donnee.temp_docteurID = pData.getDoctorID();
                                Donnee.temp_docteurName = pData.getFullName();
                                Donnee.temp_docteurEmail = pData.getEmail();
                                Donnee.temp_docteurPassword = pData.getPassword();
                                Donnee.temp_docteurSpecialized = pData.getSpecialized();
                                Donnee.temp_docteurGender = pData.getGender();
                                Donnee.temp_docteurMobileNumber = String.valueOf(pData.getMobileNumber());
                                Donnee.temp_docteurAddress = pData.getAddress();
                                Donnee.temp_docteurStatus = pData.getStatus();
                                Donnee.temp_docteurImagePath = pData.getImage();

                                // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("EditDoctorForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            DoctorData pData = dashboard_tableview.getSelectionModel().getSelectedItem();
                            int num = dashboard_tableview.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alertMessage.errorMessage("S'il vous selectionnez un element");
                                return;
                            }

                            String deleteData = "UPDATE doctor SET date_supp = ? WHERE id_docteur = '"
                                    + pData.getDoctorID() + "'";

                            try {
                                if (alertMessage.confirmationMessage("Etes vous Sure de vouloir supprimer le ID Docteur: " + pData.getDoctorID() + "?")) {
                                    preparedStatement = connexion.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    preparedStatement.setString(1, String.valueOf(sqlDate));
                                    preparedStatement.executeUpdate();

                                    doctorGetData();
                                    alertMessage.MessageSucces("Suppression avec success!");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };

        docteur_col_action.setCellFactory(cellFactory);
        dashboard_tableview.setItems(doctorListData);

    }

    public void patientActionButton() {

        connexion = Database.connectDB();
        patientListData = patientGetData();

        Callback<TableColumn<patientData, String>, TableCell<patientData, String>> cellFactory = (TableColumn<patientData, String> param) -> {
            final TableCell<patientData, String> cell = new TableCell<patientData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Editer");
                        Button removeButton = new Button("Supprimer");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                patientData pData = patient_tableview.getSelectionModel().getSelectedItem();
                                int num = patient_tableview.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alertMessage.errorMessage("Svp veuillez remplir selectionner un element");
                                    return;
                                }

                                Donnee.temp_PatientID = pData.getPatientID();
                                Donnee.temp_addresse = pData.getAddress();
                                Donnee.temp_nom = pData.getFullName();
                                Donnee.temp_genre = pData.getGender();
                                Donnee.temp_number = pData.getMobileNumber();
                                Donnee.temp_statut = pData.getStatus();

                                // NOW LETS CREATE FXML FOR EDIT PATIENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("EditPatientForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            patientData pData = patient_tableview.getSelectionModel().getSelectedItem();
                            int num = patient_tableview.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alertMessage.errorMessage("Svp veuillez selectionner un element");
                                return;
                            }

                            String deleteData = "UPDATE patient SET date_supp = ? WHERE id_patient = '"
                                    + pData.getPatientID() + "'";

                            try {
                                if (alertMessage.confirmationMessage("Etes vous sure de bien vouloir supprimer l' Patient ID: " + pData.getPatientID() + "?")) {
                                    preparedStatement = connexion.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    preparedStatement.setString(1, String.valueOf(sqlDate));
                                    preparedStatement.executeUpdate();

                                    doctorGetData();
                                    alertMessage.MessageSucces("Suppression avec Succees !");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
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


    public ObservableList<AppointmentData> appointmentGetData() {

        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM rdv";

        connexion = Database.connectDB();

        try {
            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            AppointmentData aData;
            while (resultSet.next()) {
//            AppointmentData(Integer id, Integer appointmentID, String name, String gender,
//            Long mobileNumber, String description, String diagnosis, String treatment, String address,
//            Date date, Date dateModify, Date dateDelete, String status, Date schedule)
                aData = new AppointmentData(resultSet.getInt("id"), resultSet.getInt("rdv_id"),
                        resultSet.getString("nom"), resultSet.getString("genre"), resultSet.getLong("numero"),
                        resultSet.getString("description"), resultSet.getString("diagnostique"),
                        resultSet.getString("traitement"), resultSet.getString("adresse"),
                        resultSet.getString("docteur"), resultSet.getString("specialite"),
                        resultSet.getDate("date"), resultSet.getDate("date_modif"),
                        resultSet.getDate("date_supp"), resultSet.getString("statut"),
                        resultSet.getDate("Horaire"));
                listData.add(aData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<AppointmentData> appointmentListData;

    public void appointmentDisplayData() {
        appointmentListData = appointmentGetData();

        rdv_col_idrdv.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        rdv_col_nom.setCellValueFactory(new PropertyValueFactory<>("name"));
        rdv_col_genre.setCellValueFactory(new PropertyValueFactory<>("gender"));
        rdv_col_contact.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        rdv_col_descrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        rdv_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        rdv_col_datemodif.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        rdv_col_datesupp.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        rdv_col_statut.setCellValueFactory(new PropertyValueFactory<>("status"));

        rdv_tableview.setItems(appointmentListData);

    }

    public void appointmentActionButton() {

        connexion = Database.connectDB();
        appointmentListData = appointmentGetData();

        Callback<TableColumn<AppointmentData, String>, TableCell<AppointmentData, String>> cellFactory = (TableColumn<AppointmentData, String> param) -> {
            final TableCell<AppointmentData, String> cell = new TableCell<AppointmentData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Editer");
                        Button removeButton = new Button("Supprimer");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #188ba7, #306090);\n"
                                + "    -fx-cursor: hand;\n"
                                + "    -fx-text-fill: #fff;\n"
                                + "    -fx-font-size: 14px;\n"
                                + "    -fx-font-family: Arial;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                AppointmentData aData = rdv_tableview.getSelectionModel().getSelectedItem();
                                int num = rdv_tableview.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alertMessage.errorMessage("Svp selectionnnez un element d'abord");
                                    return;
                                }

                                Donnee.temp_appID = String.valueOf(aData.getAppointmentID());
                                Donnee.temp_appName = aData.getName();
                                Donnee.temp_appGender = aData.getGender();
                                Donnee.temp_appAddress = aData.getAddress();
                                Donnee.temp_appDescription = aData.getDescription();
                                Donnee.temp_appDiagnosis = aData.getDiagnosis();
                                Donnee.temp_appTreatment = aData.getTreatment();
                                Donnee.temp_appMobileNumber = String.valueOf(aData.getMobileNumber());
                                Donnee.temp_appdocteur = aData.getDoctorID();
                                Donnee.temp_appSpecialized = aData.getSpecialized();
                                Donnee.temp_appStatus = aData.getStatus();

                                // NOW LETS CREATE FXML FOR EDIT APPOINTMENT FORM
                                Parent root = FXMLLoader.load(getClass().getResource("EditAppointmentForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            AppointmentData aData = rdv_tableview.getSelectionModel().getSelectedItem();
                            int num = rdv_tableview.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alertMessage.errorMessage("Svp selectionnez un element d abord");
                                return;
                            }

                            String deleteData = "UPDATE rdv SET date_supp = ? WHERE id_rdv = '"
                                    + aData.getAppointmentID() + "'";

                            try {
                                if (alertMessage.confirmationMessage("Etes vous sure de vouloir supprimer le RDV ID: " + aData.getAppointmentID() + "?")) {
                                    preparedStatement = connexion.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    preparedStatement.setString(1, String.valueOf(sqlDate));
                                    preparedStatement.executeUpdate();

                                    doctorGetData();
                                    alertMessage.MessageSucces("Suppression Avec Succes");

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        HBox manageBtn = new HBox(editButton, removeButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);
                    }
                }
            };
            doctorDisplayData();
            return cell;
        };

        rdv_col_action.setCellFactory(cellFactory);
        rdv_tableview.setItems(appointmentListData);

    }

    public void profileUpdateBtn() {
        connexion = Database.connectDB();
        if (profile_idadmin.getText().isEmpty()
                || profile_nomupdate.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_statut.getSelectionModel().getSelectedItem() == null) {
            alertMessage.errorMessage("Svp remplissez tous les champs");
        } else {
            if (Donnee.path == null || "".equals(Donnee.path)) {
                String updateData = "UPDATE admin SET nom = ?, email = ?, gender = ? "
                        + "WHERE admin_id = " + Donnee.admin_id;

                try {
                    preparedStatement = connexion.prepareStatement(updateData);
                    preparedStatement.setString(1, profile_nomupdate.getText());
                    preparedStatement.setString(2, profile_email.getText());
                    preparedStatement.setString(3, profile_statut.getSelectionModel().getSelectedItem());

                    preparedStatement.executeUpdate();

                    profileDisplayInfo();

                    alertMessage.MessageSucces("Mise a jour avec succes");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                String updateData = "UPDATE admin SET nomutilisateur = ?, email = ?, image = ?, genre = ? "
                        + "WHERE admin_id = '"+  Donnee.admin_id +"'";
                try {
                    preparedStatement = connexion.prepareStatement(updateData);
                    preparedStatement.setString(1, profile_nomupdate.getText());
                    preparedStatement.setString(2, profile_email.getText());

                    String path = Donnee.path;
                    path = path.replace("\\", "\\\\");
                    Path transfer = Paths.get(path);

                    Path copy = Paths.get("C:\\Users\\MR_FLEX\\IdeaProjects\\PROJET HOPITAL\\src\\Image\\Admin\\"
                            + Donnee.admin_id + ".jpg");

                    Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    preparedStatement.setString(3, copy.toAbsolutePath().toString());
                    preparedStatement.setString(4, profile_statut.getSelectionModel().getSelectedItem());

                    preparedStatement.executeUpdate();
                    profileDisplayInfo();
                   profileDisplayImages();
                    alertMessage.MessageSucces("Mise a jour avec succes!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void profileDisplayImages() {

        String selectData = "SELECT * FROM admin WHERE admin_id = '" + Donnee.admin_id +"'";
        connexion = Database.connectDB();

        String tempPath1 = "";
        String tempPath2 = "";
        try {
            preparedStatement = connexion.prepareStatement(selectData);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tempPath1 = "File:" + resultSet.getString("image");
                tempPath2 = "File:" + resultSet.getString("image");

                if (resultSet.getString("image") != null) {
                    image = new Image(tempPath1, 1012, 22, false, true);
                    top_profil.setFill(new ImagePattern(image));

                    image = new Image(tempPath2, 137, 95, false, true);
                    profile_cercleimage.setFill(new ImagePattern(image));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void profileInsertImage() {

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image", "*jpg", "*jpeg", "*png"));

        File file = open.showOpenDialog(profile_import.getScene().getWindow());

        if (file != null) {
            Donnee.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 137, 95, false, true);
            profile_cercleimage.setFill(new ImagePattern(image));
        }

    }

    public void profileDisplayInfo() {

        String sql = "SELECT * FROM admin WHERE admin_id = '" + Donnee.admin_id+"'";
        System.out.println(Donnee.admin_id);
        connexion = Database.connectDB();

        try {
            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                profile_adminid.setText(resultSet.getString("admin_id"));
                profile_nomupdate.setText(resultSet.getString("nomutilisateur"));
                profile_email.setText(resultSet.getString("email"));
                profile_statut.getSelectionModel().select(resultSet.getString("genre"));

                profile_idadmin.setText(resultSet.getString("admin_id"));
                profile_label_nom.setText(resultSet.getString("nomutilisateur"));
                profile_adresseemail.setText(resultSet.getString("email"));
                profile_datecreation.setText(resultSet.getString("date"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public ObservableList<patientData> paymentGetData() {

        ObservableList<patientData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM patient WHERE date_supp IS NULL AND paiement_statut IS NULL";
        connexion = Database.connectDB();

        try {
            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            patientData pData;
            while (resultSet.next()) {
//                (Integer id, Integer patientID, String fullName, String gender
//            , String description, String diagnosis, String treatment
//            , String doctor, String image, Date date)
                pData = new patientData(resultSet.getInt("id"),
                        resultSet.getInt("id_patient"), resultSet.getString("nom_complet"),
                        resultSet.getString("genre"), resultSet.getString("description"),
                        resultSet.getString("diagnostique"), resultSet.getString("traitement"),
                        resultSet.getString("docteur"), resultSet.getString("image"), resultSet.getDate("date"));

                listData.add(pData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public ObservableList<patientData> paymentListData;

    public void paymentDisplayData() {
        paymentListData = paymentGetData();

        paiement_col_IDP.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        paiement_col_name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        paiement_col_genre.setCellValueFactory(new PropertyValueFactory<>("gender"));
        paiement_col_diagnostic.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        paiement_col_doc.setCellValueFactory(new PropertyValueFactory<>("doctor"));
        paiement_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        paiement_tableview.setItems(paymentListData);

    }

    public void paymentSelectItems() {

         patientData pData = patient_tableview.getSelectionModel().getSelectedItem();
        int num = paiement_tableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        if (pData.getImage() != null) {
            String path = "File:" + pData.getImage();
            image = new Image(path, 144, 105, false, true);
            paiement_circle.setFill(new ImagePattern(image));

            Donnee.temp_path = pData.getImage();
        }

        Donnee.temp_PatientID = pData.getPatientID();
        Donnee.temp_nom = pData.getFullName();
        Donnee.temp_date = String.valueOf(pData.getDate());

        paiement_IDP.setText(String.valueOf(pData.getPatientID()));
        paiement_patientNom.setText(pData.getFullName());
        paiement_IDD.setText(pData.getDoctor());
        paiement_date.setText(String.valueOf(pData.getDate()));

    }

    public void paymentCheckOutBtn() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("CheckOutPatient.fxml"));
            Stage stage = new Stage();

            stage.setTitle("Hospital Management System | Check-Out");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void profileStatusList() {
        List<String> listS = new ArrayList<>();

        for (String data : Donnee.genre) {
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_statut.setItems(listData);
    }



    public void changerPage(ActionEvent event){
        if (event.getSource() == btn_dash) {
            dashboard_form.setVisible(true);
            docteur_form.setVisible(false);
            patient_form.setVisible(false);
            rdv_form.setVisible(false);
            profile_form.setVisible(false);
            paiement_form.setVisible(false);

            dashboardAD();
            dashboardTP();
            dashboardAP();
            dashboardTA();
            dashboardGetDoctorDisplayData();
            form_courant.setText("Formulaire DashBoard");

        } else if (event.getSource() == btn_doc) {
            dashboard_form.setVisible(false);
            docteur_form.setVisible(true);
            patient_form.setVisible(false);
            rdv_form.setVisible(false);
            profile_form.setVisible(false);
            paiement_form.setVisible(false);


            doctorDisplayData();
            doctorActionButton();

            form_courant.setText("Formulaire Docteur");
        } else if (event.getSource() == btn_patient) {
            dashboard_form.setVisible(false);
            docteur_form.setVisible(false);
            patient_form.setVisible(true);
            rdv_form.setVisible(false);
            patientDisplayData();
            patientActionButton();
            profile_form.setVisible(false);
            paiement_form.setVisible(false);

            form_courant.setText("Formulaire Patient");
        } else if (event.getSource() == btn_rdv) {
            dashboard_form.setVisible(false);
            docteur_form.setVisible(false);
            patient_form.setVisible(false);
            rdv_form.setVisible(true);
            profile_form.setVisible(false);
            paiement_form.setVisible(false);

            appointmentDisplayData();
            appointmentActionButton();
            form_courant.setText("Formulaire RDV");
        }else if (event.getSource() == btn_profil) {
            dashboard_form.setVisible(false);
            docteur_form.setVisible(false);
            patient_form.setVisible(false);
            rdv_form.setVisible(false);
            profile_form.setVisible(true);
            paiement_form.setVisible(false);

            profileStatusList();
            profileDisplayInfo();
            profileDisplayImages();

            form_courant.setText("Formulaire Profil");
        }
        else if (event.getSource() == btn_pay) {
            dashboard_form.setVisible(false);
            docteur_form.setVisible(false);
            patient_form.setVisible(false);
            rdv_form.setVisible(false);
            profile_form.setVisible(false);
            paiement_form.setVisible(true);
            paymentDisplayData();
            form_courant.setText("Formulaire Paiement");
        }
    }

    public void affichageAdminID(){
        String sql = "SELECT * FROM admin WHERE nomutilisateur = '"
                + Donnee.admin_nomutil + "'";

        connexion = Database.connectDB();

        try {

            preparedStatement = connexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nav_IDadmin.setText(resultSet.getString("admin_id"));
                String tempUsername = resultSet.getString("nomutilisateur");
                tempUsername = tempUsername.substring(0, 1).toUpperCase() + tempUsername.substring(1); // TO SET THE FIRST LETTER TO UPPER CASE
                nav_nomutili.setText(tempUsername);
                top_nomutili.setText(tempUsername);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runTime();
        affichageAdminID();


        dashboardAD();
        dashboardTP();
        dashboardAP();
        dashboardTA();
        dashboardGetDoctorDisplayData();
        dashboardDoctorDataChart();
        dashboardPatientDataChart();

        doctorDisplayData();
        doctorActionButton();

        patientDisplayData();
        patientActionButton();

        appointmentDisplayData();
        appointmentActionButton();


        paymentDisplayData();
        paymentSelectItems();

        profileStatusList();
        profileDisplayInfo();
        profileDisplayImages();


    }
}
