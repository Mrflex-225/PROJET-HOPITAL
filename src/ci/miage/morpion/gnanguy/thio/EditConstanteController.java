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

public class EditConstanteController implements Initializable{
    @FXML
    private TextArea editDoctor_address;

    @FXML
    private Button editDoctor_cancelBtn;

    @FXML
    private TextField editDoctor_doctorID;

    @FXML
    private TextField editDoctor_email;

    @FXML
    private TextField editDoctor_fullName;

    @FXML
    private ComboBox<String> editDoctor_garde;

    @FXML
    private ComboBox<String> editDoctor_gender;

    @FXML
    private ImageView editDoctor_imageView;

    @FXML
    private Button editDoctor_importBtn;

    @FXML
    private TextField editDoctor_mobileNumber;

    @FXML
    private TextField editDoctor_password;

    @FXML
    private ComboBox<String> editDoctor_specialized;

    @FXML
    private ComboBox<String> editDoctor_status;

    @FXML
    private Button editDoctor_updateBtn;

    private Image image;


    AlertMessage alert =new AlertMessage();

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;


    public void cancelBtn() {
      displayDoctorData();
    }

    public void updateBtn() {
        connect = Database.connectDB();

        if (editDoctor_doctorID.getText().isEmpty()
                || editDoctor_fullName.getText().isEmpty()
                || editDoctor_email.getText().isEmpty()
                || editDoctor_password.getText().isEmpty()) {
            alert.errorMessage("Svp remplissez tous les champs");
        } else {
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            if (Donnee.path == null || "".equals(Donnee.path)) {
                String updateData = "INSERT INTO `consultatio`(`id_patient`, `poids`, `tension`, `taille`) " +
                        "VALUES ("+
                        editDoctor_doctorID.getText()+",'"+editDoctor_fullName.getText()+"','"+editDoctor_email.getText()+"','"+editDoctor_password.getText()+"')";
                try {
                    if (alert.confirmationMessage("Etes vous sure de vouloir modifier le constante ID: " + editDoctor_doctorID.getText() + "?")) {
                        prepare = connect.prepareStatement(updateData);
                        prepare.executeUpdate();
                    } else {
                        alert.errorMessage("Cancelled.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        displayDoctorData();
    }

    public void setField() {
        editDoctor_doctorID.setText(String.valueOf(Donnee.temp_PatientID));
    }


    public void genderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : Donnee.genre) {
            genderL.add(data);
        }

        ObservableList listData = FXCollections.observableList(genderL);
        editDoctor_gender.setItems(listData);
    }

    public void specializationList() {
        List<String> specializationL = new ArrayList<>();

        for (String data : Donnee.specialite) {
            specializationL.add(data);
        }

        ObservableList listData = FXCollections.observableList(specializationL);
        editDoctor_specialized.setItems(listData);
    }

    public void statusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : Donnee.statut) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableList(statusL);
        editDoctor_status.setItems(listData);
    }


    public void displayDoctorData() {

        String sql = "SELECT * FROM docteur WHERE id_docteur = '"
                + editDoctor_doctorID.getText() + "'";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                editDoctor_fullName.setText(result.getString("full_name"));
                editDoctor_email.setText(result.getString("email"));
                editDoctor_password.setText(result.getString("password"));
                editDoctor_specialized.getSelectionModel().select(result.getString("specialite"));
                editDoctor_gender.getSelectionModel().select(result.getString("gender"));
                editDoctor_mobileNumber.setText(result.getString("mobile_number"));
                editDoctor_address.setText(result.getString("address"));
                editDoctor_status.getSelectionModel().select(result.getString("status"));

                image = new Image("File:" + result.getString("image"), 112, 121, false, true);
                editDoctor_imageView.setImage(image);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setField();
        //statusList();
        //genderList();
        //specializationList();

    }}
