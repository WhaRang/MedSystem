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

public class SignUpController implements Initializable {

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private TextField textFieldRepeatPassword;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldSurname;

    HttpRequestSender sender = new HttpRequestSender();

    @FXML
    void handleClicks(ActionEvent event) {
        if (event.getSource() == btnSignUp) {

            String password = textFieldPassword.getText();
            String passwordRepeat = textFieldRepeatPassword.getText();
            if (password.equals(passwordRepeat)) {

                //Create patient
                JSONObject patient = new JSONObject();
                patient.put("operation", "createPatient");
                patient.put("name", textFieldName.getText() + " " + textFieldSurname.getText());
                patient.put("insurance_id", "null");

                HttpResponse<String> patientResponse =
                        sender.getResponseFromOpenAPI(
                                "Http://localhost:8083/medapp/registerChange", patient.toJSONString());

                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Create user
                JSONObject user = new JSONObject();
                user.put("operation", "createUser");
                user.put("name", textFieldName.getText() + " " + textFieldSurname.getText());
                user.put("username", textFieldEmail.getText());
                user.put("auth", "patient");
                user.put("password", password);

                HttpResponse<String> response =
                        sender.getResponseFromOpenAPI(
                                "Http://localhost:8083/medapp/registerChange", user.toJSONString());

                if (response.statusCode() != 200 || patientResponse.statusCode() != 200) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Look, an Error Dialog");
                    alert.setContentText("Oops, something went wrong creating new user. Try choosing different username");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Information");
                    alert.setContentText("New user was successfully created!");
                    alert.showAndWait();
                    Main.loadLogin();
                    Main.signupStage.close();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("Passwords where different");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
