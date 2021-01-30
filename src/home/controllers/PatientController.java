package home.controllers;

import home.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    public static int patientID;

    @FXML
    private TableView<String[]> insuranceTableView;

    @FXML
    private TableColumn<String[], String> insuranceExpDateColumn;

    @FXML
    private TableColumn<String[], String> insuranceDescColumn;

    @FXML
    private TableView<String[]> appointmentTableView;

    @FXML
    private TableColumn<String[], String> appointmentTimeColumn;

    @FXML
    private TableColumn<String[], String> appointmentDescColumn;

    @FXML
    private TableColumn<String[], String> appointmentResultColumn;

    @FXML
    private TableView<String[]> researchesTableView;

    @FXML
    private TableColumn<String[], String> researchesStartDateColumn;

    @FXML
    private TableColumn<String[], String> researchesEndDateColumn;

    @FXML
    private TableColumn<String[], String> researchesDescColumn;

    @FXML
    private TableView<String[]> recipesTableView;

    @FXML
    private TableColumn<String[], String> recipesDateStartColumn;

    @FXML
    private TableColumn<String[], String> recipesDateEndColumn;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button bntInsurance;

    @FXML
    private Button btnAppointments;

    @FXML
    private Button btnAllergy;

    @FXML
    private Button btnDiseases;

    @FXML
    private Button btnReferrals;

    @FXML
    private Button btnResearches;

    @FXML
    private Button btnRecipes;

    @FXML
    private GridPane panelInsurances;

    @FXML
    private GridPane panelDiseases;

    @FXML
    private GridPane panelAppointments;

    @FXML
    private GridPane panelResearches;

    @FXML
    private GridPane panelRecipes;

    @FXML
    private GridPane panelAllergies;

    @FXML
    private GridPane panelReferrals;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        printInfo();
    }


    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == bntInsurance) {
            setCurrentPanelActive(PanelType.INSURANCES);
        } else if (event.getSource() == btnAllergy) {
            setCurrentPanelActive(PanelType.ALLERGIES);
        } else if (event.getSource() == btnDiseases) {
            setCurrentPanelActive(PanelType.DISEASES);
        } else if (event.getSource() == btnReferrals) {
            setCurrentPanelActive(PanelType.REFERRALS);
        } else if (event.getSource() == btnAppointments) {
            setCurrentPanelActive(PanelType.APPOINTMENTS);
        } else if (event.getSource() == btnResearches) {
            setCurrentPanelActive(PanelType.RESEARCHES);
        } else if (event.getSource() == btnRecipes) {
            setCurrentPanelActive(PanelType.RECIPES);
        } else if (event.getSource() == btnLogOut) {
            logOut();
        }
    }


    private void logOut() {
        Main.patientLogOut();
    }


    private void setCurrentPanelActive(PanelType panel) {
        disableAllPanels();

        switch (panel) {
            case ALLERGIES: {
                panelAllergies.setVisible(true);
                break;
            }
            case APPOINTMENTS: {
                panelAppointments.setVisible(true);
                break;
            }

            case RECIPES: {
                panelRecipes.setVisible(true);
                break;
            }

            case RESEARCHES: {
                panelResearches.setVisible(true);
                break;
            }

            case DISEASES: {
                panelDiseases.setVisible(true);
                break;
            }

            case REFERRALS: {
                panelReferrals.setVisible(true);
                break;
            }

            case INSURANCES: {
                panelInsurances.setVisible(true);
                break;
            }
        }
    }


    private void disableAllPanels() {
        panelInsurances.setVisible(false);
        panelAllergies.setVisible(false);
        panelAppointments.setVisible(false);
        panelDiseases.setVisible(false);
        panelReferrals.setVisible(false);
        panelResearches.setVisible(false);
        panelRecipes.setVisible(false);
    }


    private void printInfo() {
        printInsurances();
        printAllergies();
        printDiseases();
        printReferrals();
        printVisits();
        printResearches();
        printRecipes();
    }


    private void printReferrals() {

    }


    private void printDiseases() {

    }


    private void printAllergies() {

    }


    private void printInsurances() {

    }


    private void printRecipes() {

    }


    private void printResearches() {

    }


    private void printVisits() {

    }
}