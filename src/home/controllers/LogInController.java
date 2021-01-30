package home.controllers;


import home.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private Button btnSignUp;

    @FXML
    private Button btnPatientLogin;

    @FXML
    private Button btnDoctorLogin;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldPassword;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == btnPatientLogin) {
            /*Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Ooops, there was an error!");
            alert.showAndWait();*/
            PatientLogin();
        } else if (event.getSource() == btnDoctorLogin) {
            DoctorLogin();
        } else if (event.getSource() == btnSignUp) {
            SignUp();
        }
    }


    private void PatientLogin() {
        Main.patientLogIn();
    }


    private void DoctorLogin() {
        Main.doctorLogIn();
    }


    private void SignUp() {
        Main.signUp();
    }
}
