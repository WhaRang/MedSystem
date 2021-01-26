package home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage loginStage;
    private static Stage patientStage;
    private static Stage signupStage;
    private static Stage choosePatientStage;
    private static Stage doctorStage;


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent loginRoot = FXMLLoader.load(getClass().getResource("fxml/LogIn.fxml"));
        Parent patientRoot = FXMLLoader.load(getClass().getResource("fxml/Patient.fxml"));
        Parent signupRoot = FXMLLoader.load(getClass().getResource("fxml/SignUp.fxml"));
        Parent choosePatientRoot = FXMLLoader.load(getClass().getResource("fxml/ChoosePatient.fxml"));
        Parent doctorRoot = FXMLLoader.load(getClass().getResource("fxml/Doctor.fxml"));

        doctorStage = new Stage();
        doctorStage.setResizable(false);
        doctorStage.setTitle("Doctor");
        doctorStage.setScene(new Scene(doctorRoot));

        choosePatientStage = new Stage();
        choosePatientStage.setResizable(false);
        choosePatientStage.setTitle("Choose Patient");
        choosePatientStage.setScene(new Scene(choosePatientRoot));

        signupStage = new Stage();
        signupStage.setResizable(false);
        signupStage.setTitle("Sign Up");
        signupStage.setScene(new Scene(signupRoot));

        patientStage = new Stage();
        patientStage.setResizable(false);
        patientStage.setTitle("Patient");
        patientStage.setScene(new Scene(patientRoot));

        loginStage = new Stage();
        loginStage.setResizable(false);
        loginStage.setTitle("Med System");
        loginStage.setScene(new Scene(loginRoot));

        loginStage.show();
    }


    public static void patientLogIn() {
        patientStage.show();
        loginStage.close();
    }


    public static void patientLogOut() {
        loginStage.show();
        patientStage.close();
    }


    public static void afterSignUpLogIn() {
        loginStage.show();
        signupStage.close();
    }


    public static void signUp() {
        signupStage.show();
        loginStage.close();
    }


    public static void doctorLogIn() {
        choosePatientStage.show();
        loginStage.close();
    }


    public static void doctorLogOut() {
        loginStage.show();
        doctorStage.close();
    }


    public static void afterPatientChoose() {
        doctorStage.show();
        choosePatientStage.close();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
