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


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        //TODO wait until everything is implemented
        //DataHolder.initializeTables();

        Parent loginRoot = FXMLLoader.load(getClass().getResource("fxml/LogIn.fxml"));
        Parent patientRoot = FXMLLoader.load(getClass().getResource("fxml/Patient.fxml"));
        Parent signupRoot = FXMLLoader.load(getClass().getResource("fxml/SignUp.fxml"));
        Parent choosePatientRoot = FXMLLoader.load(getClass().getResource("fxml/ChoosePatient.fxml"));
        Parent doctorRoot = FXMLLoader.load(getClass().getResource("fxml/Doctor.fxml"));

        doctorStage = loadStage(false, "Doctor", doctorRoot);
        patientStage = loadStage(false, "Patient", patientRoot);
        signupStage = loadStage(false, "Sign Up", signupRoot);
        choosePatientStage = loadStage(false, "Choose Patient", choosePatientRoot);
        loginStage = loadStage(false, "Log In", loginRoot);

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


    private static Stage loadStage(boolean resizable, String title, Parent root) {
        Stage stage = new Stage();
        stage.setResizable(resizable);
        stage.setTitle(title);
        stage.setScene(new Scene(root));

        return stage;
    }

}
