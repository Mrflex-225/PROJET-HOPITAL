package ci.miage.morpion.gnanguy.thio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.util.Date;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RecordControleur implements Initializable {


    public String style1 ="-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);\n"
            + "    -fx-cursor: hand;\n"
            + "    -fx-text-fill: #fff;\n"
            + "    -fx-font-size: 14px;\n"
            + "    -fx-font-family: Arial;";
    public String style2 = "-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);\n"
            + "    -fx-cursor: hand;\n"
            + "    -fx-text-fill: #fff;\n"
            + "    -fx-font-size: 14px;\n"
            + "    -fx-font-family: Arial;";

    @FXML
    private TableColumn<patientData, String> record_nom;

    @FXML
    private TableColumn<patientData, String> record_patientaction;

    @FXML
    private TableColumn<patientData, String> record_patientadresse;

    @FXML
    private TableColumn<patientData, String> record_patientdatecrea;

    @FXML
    private TableColumn<patientData, String> record_patientdatemodif;

    @FXML
    private TableColumn<patientData, String> record_patientdatesupp;

    @FXML
    private TableColumn<patientData, String> record_patientgenre;

    @FXML
    private TableColumn<patientData, String> record_patientid;

    @FXML
    private TableColumn<patientData, String> record_patientnumero;

    @FXML
    private AnchorPane record_recherche;

    @FXML
    private TableView<patientData> record_tableview;

    private final AlertMessage alert = new AlertMessage();

    //DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private ObservableList<patientData> patientRecordData;

    public ObservableList<patientData> getPatientRecordData() {
        ObservableList<patientData> listData = FXCollections.observableArrayList();

        String selectData = "select * from patient where date_supp IS NULL AND docteur = '"
                + Donnee.docteur_id + "'";
        connect = Database.connectDB();

        patientData pData;
//        patientData(Integer id, Integer patientID, String fullName,
        //              Long mobileNumber, String address, Date date
        //            , Date dateModify, Date dateDelete)

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();
            while (result.next()) {
                pData = new patientData(result.getInt("id"), result.getInt("id_patient"),
                        result.getString("nom_complet"), result.getString("genre"), result.getLong("numero"),
                        result.getString("adresse"), result.getString("status"), result.getDate("date"),
                        result.getDate("date_modif"), result.getDate("date_supp"));
                listData.add(pData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public void displaypatientData() {
        patientRecordData = getPatientRecordData();

        record_patientid.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        record_nom.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        record_patientgenre.setCellValueFactory(new PropertyValueFactory<>("gender"));
        record_patientnumero.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        record_patientadresse.setCellValueFactory(new PropertyValueFactory<>("address"));
        record_patientdatecrea.setCellValueFactory(new PropertyValueFactory<>("date"));
        record_patientdatemodif.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        record_patientdatesupp.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));

        record_tableview.setItems(patientRecordData);

    }

    public void actionButtons() {

        connect = Database.connectDB();
        patientRecordData = getPatientRecordData();

        Callback<TableColumn<patientData, String>, TableCell<patientData, String>> cellFactory = (TableColumn<patientData, String> param) -> {
            return new TableCell<patientData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Editer");
                        Button removeButton = new Button("Supprimer");

                        editButton.setStyle(style1);

                        removeButton.setStyle(style2);

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                patientData pData = record_tableview.getSelectionModel().getSelectedItem();
                                int num = record_tableview  .getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Svp selectionnez un element !");
                                    return;
                                }

                                Donnee.temp_PatientID = pData.getPatientID();
                                Donnee.temp_nom = pData.getFullName();
                                Donnee.temp_genre = pData.getGender();
                                Donnee.temp_number = pData.getMobileNumber();
                                Donnee.temp_addresse = pData.getAddress();
                                Donnee.temp_statut = pData.getStatus();
                                Parent root = FXMLLoader.load(getClass().getResource("EditPatientForm.fxml"));
                                Stage stage = new Stage();

                                stage.setScene(new Scene(root));
                                stage.show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            patientData pData = record_tableview.getSelectionModel().getSelectedItem();
                            int num = record_tableview.getSelectionModel().getSelectedIndex();

                            if ((num - 1) < -1) {
                                alert.errorMessage("Svp selectionnez un element !");
                                return;
                            }

                            String deleteData = "UPDATE patient SET date_supp = ? WHERE id_patient = "
                                    + pData.getPatientID();

                            try {
                                if (alert.confirmationMessage("Etes vous sur de vouloir supprimer le patient ID: " + pData.getPatientID() + "?")) {
                                    prepare = connect.prepareStatement(deleteData);
                                    Date date = new Date();
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                                    prepare.setString(1, String.valueOf(sqlDate));
                                    prepare.executeUpdate();

                                    alert.MessageSucces("Supprimé avec succes!");

                                    displaypatientData();
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
        };

        record_patientaction.setCellFactory(cellFactory);
        record_tableview.setItems(patientRecordData);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displaypatientData();
        actionButtons();
    }
}
