package home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    private static Stage loginStage;
    private static Stage patientDataStage;
    private static Stage signupStage;
    private static Stage choosePatientStage;

    public static boolean shouldInitLoginStage = true;
    public static boolean shouldInitPatientDataStage = true;
    public static boolean shouldInitSignupStage = true;
    public static boolean shouldInitChoosePatientStage = true;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        (new HttpRequestSender()).setTokenFromLogin("admin", "admin.123");

        DataHolder.initializeData();
        loadLogin();
    }


    public static void patientLogIn() {
        try {
            if (shouldInitPatientDataStage) {
                shouldInitPatientDataStage = false;
                Parent patientRoot = FXMLLoader.load(Main.class.getResource("fxml/PatientData.fxml"));
                patientDataStage = loadStage(false, "Patient", patientRoot);
            }

            patientDataStage.show();
            loginStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void loadLogin() {
        try {
            if (shouldInitLoginStage) {
                shouldInitLoginStage = false;
                Parent loginRoot = FXMLLoader.load(Main.class.getResource("fxml/LogIn.fxml"));
                loginStage = loadStage(false, "Log In", loginRoot);
            }

            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void patientLogOut() {
        loadLogin();
        patientDataStage.close();
    }


    public static void afterSignUpLogIn() {
        loadLogin();
        signupStage.close();
    }


    public static void signUp() {
        try {
            if (shouldInitSignupStage) {
                shouldInitSignupStage = false;
                Parent signupRoot = FXMLLoader.load(Main.class.getResource("fxml/SignUp.fxml"));
                signupStage = loadStage(false, "Sign Up", signupRoot);
            }

            signupStage.show();
            loginStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void doctorLogIn() {
        try {
            if (shouldInitChoosePatientStage) {
                shouldInitChoosePatientStage = false;
                Parent choosePatientRoot = FXMLLoader.load(Main.class.getResource("fxml/ChoosePatient.fxml"));
                choosePatientStage = loadStage(false, "Choose Patient", choosePatientRoot);
            }

            choosePatientStage.show();
            loginStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void afterPatientChoose() {
        try {
            if (shouldInitPatientDataStage) {
                shouldInitPatientDataStage = false;
                Parent doctorRoot = FXMLLoader.load(Main.class.getResource("fxml/PatientData.fxml"));
                patientDataStage = loadStage(false, "Doctor", doctorRoot);
            }

            patientDataStage.show();
            choosePatientStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static Stage loadStage(boolean resizable, String title, Parent root) {
        Stage stage = new Stage();
        stage.setResizable(resizable);
        stage.setTitle(title);
        stage.setScene(new Scene(root));

        return stage;
    }

}