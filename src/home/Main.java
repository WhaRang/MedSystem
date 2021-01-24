package home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage loginStage;
    public static Stage patientStage;
    public static Stage signupStage;


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent loginRoot = FXMLLoader.load(getClass().getResource("fxml/LogIn.fxml"));
        Parent patientRoot = FXMLLoader.load(getClass().getResource("fxml/Patient.fxml"));
        Parent signupRoot = FXMLLoader.load(getClass().getResource("fxml/SignUp.fxml"));

        signupStage = new Stage();
        signupStage.setResizable(false);
        signupStage.setTitle("MedSystem");
        signupStage.setScene(new Scene(signupRoot));

        patientStage = new Stage();
        patientStage.setResizable(false);
        patientStage.setTitle("MedSystem");
        patientStage.setScene(new Scene(patientRoot));

        loginStage = new Stage();
        loginStage.setResizable(false);
        loginStage.setTitle("MedSystem");
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


    public static void main(String[] args) {
        launch(args);
    }
}
