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

    private static Parent loginRoot;
    private static Parent patientRoot;
    private static Parent signupRoot;
    private static Parent choosePatientRoot;
    private static Parent doctorRoot;

    public static boolean shouldInitLoginStage = true;
    public static boolean shouldInitPatientStage = true;
    public static boolean shouldInitSignupStage = true;
    public static boolean shouldInitChoosePatientStage = true;
    public static boolean shouldInitDoctorStage = true;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        DataHolder.initializeData();

        patientRoot = FXMLLoader.load(getClass().getResource("fxml/Patient.fxml"));
        doctorRoot = FXMLLoader.load(getClass().getResource("fxml/Doctor.fxml"));

        doctorStage = loadStage(false, "Doctor", doctorRoot);

        loadLogin();
    }


    public static void patientLogIn() {
        try {
            if (shouldInitPatientStage) {
                shouldInitPatientStage = false;
                patientRoot = FXMLLoader.load(Main.class.getResource("fxml/Patient.fxml"));
                patientStage = loadStage(false, "Patient", patientRoot);
            }

            patientStage.show();
            loginStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void loadLogin() {
        try {
            if (shouldInitLoginStage) {
                shouldInitLoginStage = false;
                loginRoot = FXMLLoader.load(Main.class.getResource("fxml/LogIn.fxml"));
                loginStage = loadStage(false, "Log In", loginRoot);
            }

            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void patientLogOut() {
        loadLogin();
        patientStage.close();
    }


    public static void afterSignUpLogIn() {
        loadLogin();
        signupStage.close();
    }


    public static void signUp() {
        try {
            if (shouldInitSignupStage) {
                shouldInitSignupStage = false;
                signupRoot = FXMLLoader.load(Main.class.getResource("fxml/SignUp.fxml"));
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
                choosePatientRoot = FXMLLoader.load(Main.class.getResource("fxml/ChoosePatient.fxml"));
                choosePatientStage = loadStage(false, "Choose Patient", choosePatientRoot);
            }

            choosePatientStage.show();
            loginStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void doctorLogOut() {
        loadLogin();
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