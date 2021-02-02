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


    HttpRequestSender sender = new HttpRequestSender();

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private TextField textFieldRepeatPassword;

    @FXML
    private  TextField textFieldName;

    @FXML
    private  TextField textFieldSurname;



    @FXML
    void handleClicks(ActionEvent event) {
        if (event.getSource() == btnSignUp) {

        String password=  textFieldPassword.getText();
        String passwordRepeat = textFieldRepeatPassword.getText();
        if(password.equals(passwordRepeat)){
            //Create user
            JSONObject user = new JSONObject();
            user.put("operation","createUser");
            user.put("username",textFieldEmail.getText());
            user.put("password",password);

            HttpResponse<String> response = sender.getResponseFromServer("Http://localhost:8083/medapp/registerChange",user.toJSONString());

            //Create patient
            JSONObject patient = new JSONObject();
            patient.put("operation","createPatient");
            patient.put("name",textFieldName.getText() + " " + textFieldSurname.getText());
            patient.put("insurance_id","null");

            HttpResponse<String> patientResponse = sender.getResponseFromServer("Http://localhost:8083/medapp/registerChange",patient.toJSONString());

            if(response.statusCode()!=200 && patientResponse.statusCode()!=200){
                  Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("Oops, something went wrong creating new user. Try choosing different username");
            alert.showAndWait();
            }
            else{
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Information");
                    alert.setContentText("New user was successfully created!");
                    alert.showAndWait();
                    Main.loadLogin();
                    //TODO close previous view before returing to login

            }

        }
        else{
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
        System.out.println("Signup init");
    }
}
