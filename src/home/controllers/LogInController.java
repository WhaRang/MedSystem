package home.controllers;


import home.DataHolder;
import home.HttpRequestSender;
import home.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
        if (event.getSource() == btnPatientLogin || event.getSource() == btnDoctorLogin) {
            HttpResponse<String> loginResponse;
            try {
                if(event.getSource() == btnDoctorLogin)
                    loginResponse = sender.setTokenFromLogin(textFieldEmail.getText(), textFieldPassword.getText(),"doctor");
                else
                    loginResponse = sender.setTokenFromLogin(textFieldEmail.getText(), textFieldPassword.getText(),"patient");

                if (loginResponse.statusCode() != 200) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Login Error");
                    alert.setContentText("Entered data was wrong, check username or password");
                    alert.showAndWait();
                } else {
                    if (event.getSource() == btnPatientLogin) {
                        JSONParser parser = new JSONParser();
                        JSONObject ob = (JSONObject)parser.parse(loginResponse.body());
                        Main.token = (String) ob.get("token");
                        int id = ((Long) ob.get("id")).intValue();
                        PatientDataController.setPatientId(id);
                        DataHolder.initializeData();
                        PatientLogin();

                    } else if (event.getSource() == btnDoctorLogin) {
                        JSONParser parser = new JSONParser();
                        JSONObject ob = (JSONObject)parser.parse(loginResponse.body());
                        Main.token = (String) ob.get("token");
                        int id = ((Long) ob.get("id")).intValue();
                        DataHolder.initializeData();
                        DoctorLogin();
                    }
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else if (event.getSource() == btnSignUp) {
            SignUp();
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
