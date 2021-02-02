package home.controllers;


import home.HttpRequestSender;
import home.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;

import java.net.URL;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    HttpRequestSender sender = new HttpRequestSender();

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
        //TEST
        HttpRequestSender sender = new HttpRequestSender();
    }


    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == btnPatientLogin || event.getSource() == btnDoctorLogin || event.getSource() == btnSignUp) {
            JSONObject login = new JSONObject();
            login.put("username", textFieldPassword.getText());
            login.put("password", textFieldEmail.getText());

            HttpResponse<String> loginResponse = sender.getResponseFromServer("Http://localhost:8083/medapp/login", login.toJSONString());
            if (loginResponse.statusCode() != 200) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Login Error");
                alert.setContentText("Entered data was wrong, check username or password");
                alert.showAndWait();
            } else {
                if (event.getSource() == btnPatientLogin) {
                    Main.token = loginResponse.body();
                    PatientLogin();
                } else if (event.getSource() == btnDoctorLogin) {
                    Main.token = loginResponse.body();
                    DoctorLogin();
                } else if (event.getSource() == btnSignUp) {
                    SignUp();
                }
            }
        }

    }


    private void PatientLogin() {
        PatientDataController.setDoctorPermission(false);
        Main.patientLogIn();
    }


    private void DoctorLogin() {
        Main.doctorLogIn();
    }


    private void SignUp() {
        Main.signUp();
    }
}
