package home.controllers;

import home.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import org.json.simple.JSONArray;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    public static int patientID = 1;

    @FXML
    private TableView<String[]> insuranceTable;

    @FXML
    private TableColumn<String[], String> insuranceExpDateColumn;

    @FXML
    private TableColumn<String[], String> insuranceDescColumn;

    @FXML
    private TableView<String[]> referralsTable;

    @FXML
    private TableColumn<String[], String> referralsDoctorColumn;

    @FXML
    private TableColumn<String[], String> referralsDateStartColumn;

    @FXML
    private TableColumn<String[], String> referralsDateEndColumn;

    @FXML
    private TableColumn<String[], String> referralsSpecializationColumn;

    @FXML
    private TableView<String[]> diseasesTable;

    @FXML
    private TableColumn<String[], String> diseasesNameColumn;

    @FXML
    private TableColumn<String[], String> diseasesRegistDateColumn;

    @FXML
    private TableColumn<String[], String> diseasesDescColumn;

    @FXML
    private TableView<String[]> visitsTable;

    @FXML
    private TableColumn<String[], String> visitsTimeColumn;

    @FXML
    private TableColumn<String[], String> visitsDateColumn;

    @FXML
    private TableColumn<String[], String> visitsDescColumn;

    @FXML
    private TableColumn<String[], String> visitsResultColumn;

    @FXML
    private TableView<String[]> hypersensTable;

    @FXML
    private TableColumn<String[], String> hypersensDescColumn;

    @FXML
    private TableView<String[]> surveysTable;

    @FXML
    private TableColumn<String[], String> surveysDoctorColumn;

    @FXML
    private TableColumn<String[], String> surveysDateStartColumn;

    @FXML
    private TableColumn<String[], String> surveysDateEndColumn;

    @FXML
    private TableColumn<String[], String> surveysDescColumn;

    @FXML
    private TableView<String[]> prescriptionsTable;

    @FXML
    private TableColumn<String[], String> prescriptionsMedicineColumn;

    @FXML
    private TableColumn<String[], String> prescriptionsDateStartColumn;

    @FXML
    private TableColumn<String[], String> prescriptionsDateEndColumn;

    @FXML
    private TableColumn<String[], String> prescriptionsUsingColumn;

    @FXML
    private TableColumn<String[], String> prescriptionsDescColumn;

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
        initColumns();
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


    private void initColumns() {
        initInsurancesColumns();
        initReferralsColumns();
        initDiseasesColumns();
        initVisits();
        initHypersens();
        initSurveys();
        initRecipes();
    }


    private void initRecipes() {
        prescriptionsMedicineColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no medicine>", 0));
        prescriptionsDateStartColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no issue date>", 1));
        prescriptionsDateEndColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no expiry date>", 2));
        prescriptionsUsingColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no using>", 3));
        prescriptionsDescColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no description>", 4));
    }


    private void initSurveys() {
        surveysDoctorColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no doctor>", 0));
        surveysDateStartColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no start date>", 1));
        surveysDateEndColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no end date>", 2));
        surveysDescColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no desc>", 3));
    }


    private void initHypersens() {
        hypersensDescColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no desc>", 0));
    }


    private void initVisits() {
        visitsTimeColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no time>", 0));
        visitsDateColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no date>", 1));
        visitsDescColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no desc>", 2));
        visitsResultColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no result>", 3));
    }


    private void initDiseasesColumns() {
        diseasesNameColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no name>", 0));
        diseasesRegistDateColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no date>", 1));
        diseasesDescColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no description>", 2));
    }


    private void initReferralsColumns() {
        referralsDoctorColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no doctor>", 0));
        referralsDateStartColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no date of issue>", 1));
        referralsDateEndColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no expiry date>", 2));
        referralsSpecializationColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no specialization>", 3));
    }


    private void initInsurancesColumns() {
        insuranceExpDateColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no date>", 0));
        insuranceDescColumn.setCellValueFactory(
                ViewConfigurator.getStdCallbackForCVF("<no desc>", 1));
    }


    private void printInfo() {
        printInsurances();
        printAllergies();
        printDiseases();
        printReferrals();
        printVisits();
        printSurveys();
        printPrescriptions();
    }


    private void printInsurances() {

        JSONHandler handler = new JSONHandler();
        JSONArray jsonArr = handler.filterItemsByValue(
                DataHolder.insurances, "patientId", String.valueOf(patientID));

        var data = ViewConfigurator.getColumnDataFromJsonArr(
                jsonArr, new ArrayList<>(Arrays.asList("expirationDate", "description")));

        var rawData = DataConverter.convertToRawData(data);

        insuranceTable.getItems().addAll(Arrays.asList(rawData));
    }


    private void printReferrals() {

        JSONHandler handler = new JSONHandler();
        JSONArray referralsJsonArr = handler.filterItemsByValue(
                DataHolder.referrals, "patientId", String.valueOf(patientID));

        ArrayList<Object> doctorsIdObj = handler.getValuesArray(referralsJsonArr, "fromDoctorId");
        ArrayList<String> doctorsIdStr = DataConverter.convertObjArrToStrArr(doctorsIdObj);

        JSONArray filteredDoctorsJsonArr =
                handler.filterItemsByMultipleValues(DataHolder.doctors, "id", doctorsIdStr);
        ArrayList<String> doctorsNamesData =
                DataConverter.convertJsonToStrArrByKey(filteredDoctorsJsonArr, "name");

        ArrayList<String> doctorSpecsIds =
                DataConverter.convertJsonToStrArrByKey(filteredDoctorsJsonArr, "specializationId");
        JSONArray filteredSpecsJsonArr =
                handler.filterItemsByMultipleValues(DataHolder.specializations, "id", doctorSpecsIds);
        ArrayList<String> doctorSpecsData =
                DataConverter.convertJsonToStrArrByKey(filteredSpecsJsonArr, "description");

        var datesData = ViewConfigurator.getColumnDataFromJsonArr(
                referralsJsonArr, new ArrayList<>(Arrays.asList("issueDate", "expirationDate")));

        ArrayList<ArrayList<String>> data = new ArrayList<>(datesData);
        for (int i = 0; i < data.size(); i++) {
            data.get(i).add(0, doctorsNamesData.get(i));
            data.get(i).add(doctorSpecsData.get(i));
        }

        var rawData = DataConverter.convertToRawData(data);
        referralsTable.getItems().addAll(Arrays.asList(rawData));
    }


    private void printDiseases() {

        JSONHandler handler = new JSONHandler();
        JSONArray patDiseasesJsonArr = handler.filterItemsByValue(
                DataHolder.patientDiseases, "patientId", String.valueOf(patientID));

        ArrayList<Object> diseasesIdObj = handler.getValuesArray(patDiseasesJsonArr, "diseaseId");
        ArrayList<String> diseasesIdStr = DataConverter.convertObjArrToStrArr(diseasesIdObj);
        JSONArray diseasesJsonArr = handler.filterItemsByMultipleValues(
                DataHolder.diseases, "id", diseasesIdStr);

        ArrayList<String> diseasesNames =
                DataConverter.convertJsonToStrArrByKey(diseasesJsonArr, "name");
        ArrayList<String> diseasesDescriptions =
                DataConverter.convertJsonToStrArrByKey(diseasesJsonArr, "description");

        ArrayList<String> diseaseRegistrDates =
                DataConverter.convertJsonToStrArrByKey(patDiseasesJsonArr, "detectionDate");

        int index = 0;
        for (String date : diseaseRegistrDates) {
            diseaseRegistrDates.set(index, DataConverter.getDateFromTimeStamp(date));
            index++;
        }

        ArrayList<ArrayList<String>> data = new ArrayList<>(diseasesNames.size());
        for (int i = 0; i < diseasesNames.size(); i++) {
            ArrayList<String> dataPiece = new ArrayList<>();
            dataPiece.add(diseasesNames.get(i));
            dataPiece.add(diseaseRegistrDates.get(i));
            dataPiece.add(diseasesDescriptions.get(i));

            data.add(dataPiece);
        }

        var rawData = DataConverter.convertToRawData(data);
        diseasesTable.getItems().addAll(Arrays.asList(rawData));
    }


    private void printVisits() {

        JSONHandler handler = new JSONHandler();
        JSONArray referralsJsonArr = handler.filterItemsByValue(
                DataHolder.referrals, "patientId", String.valueOf(patientID));

        ArrayList<Object> referralsIdObj = handler.getValuesArray(referralsJsonArr, "id");
        ArrayList<String> referralsIdStr = DataConverter.convertObjArrToStrArr(referralsIdObj);
        JSONArray visitsJsonArray =
                handler.filterItemsByMultipleValues(DataHolder.visits, "referralId", referralsIdStr);

        ArrayList<String> dateData = DataConverter.convertJsonToStrArrByKey(visitsJsonArray, "timestamp");
        ArrayList<ArrayList<String>> data = ViewConfigurator.getColumnDataFromJsonArr(
                visitsJsonArray, new ArrayList<>(Arrays.asList("description", "result")));

        for (int i = 0; i < data.size(); i++) {
            String time = dateData.get(i).substring(0, dateData.get(i).indexOf(" "));
            String date = dateData.get(i).substring(dateData.get(i).indexOf(" ") + 1);

            data.get(i).add(0, date);
            data.get(i).add(0, time);
        }

        var rawData = DataConverter.convertToRawData(data);
        visitsTable.getItems().addAll(Arrays.asList(rawData));
    }


    private void printAllergies() {
        //Implement on server
    }


    private void printPrescriptions() {

        JSONHandler handler = new JSONHandler();
        JSONArray prescriptionsJsonArr = handler.filterItemsByValue(
                DataHolder.prescriptions, "patientId", String.valueOf(patientID));

        ArrayList<Object> medicinesIdObj = handler.getValuesArray(prescriptionsJsonArr, "medicineId");
        ArrayList<String> medicinesIdStr = DataConverter.convertObjArrToStrArr(medicinesIdObj);
        JSONArray medicinesJsonArr =
                handler.filterItemsByMultipleValues(DataHolder.medicines, "id", medicinesIdStr);

        ArrayList<ArrayList<String>> dateData = ViewConfigurator.getColumnDataFromJsonArr(
                prescriptionsJsonArr, new ArrayList<>(Arrays.asList("issueDate", "expirationDate")));
        ArrayList<ArrayList<String>> medicineData = ViewConfigurator.getColumnDataFromJsonArr(
                medicinesJsonArr, new ArrayList<>(Arrays.asList("name", "usage", "description")));

        ArrayList<ArrayList<String>> data = new ArrayList<>(medicineData);
        for (int i = 0; i < data.size(); i++) {
            data.get(i).add(1, dateData.get(i).get(1));
            data.get(i).add(1, dateData.get(i).get(0));
        }

        var rawData = DataConverter.convertToRawData(data);
        prescriptionsTable.getItems().addAll(Arrays.asList(rawData));
    }


    private void printSurveys() {

        JSONHandler handler = new JSONHandler();
        JSONArray surveysJsonArr = handler.filterItemsByValue(
                DataHolder.surveys, "patientId", String.valueOf(patientID));

        ArrayList<Object> doctorsIdObj = handler.getValuesArray(surveysJsonArr, "doctorId");
        ArrayList<String> doctorsIdStr = DataConverter.convertObjArrToStrArr(doctorsIdObj);

        JSONArray filteredDoctorsJsonArr =
                handler.filterItemsByMultipleValues(DataHolder.doctors, "id", doctorsIdStr);
        ArrayList<String> doctorsNamesData =
                DataConverter.convertJsonToStrArrByKey(filteredDoctorsJsonArr, "name");

        ArrayList<ArrayList<String>> data = ViewConfigurator.getColumnDataFromJsonArr(
                surveysJsonArr, new ArrayList<>(Arrays.asList("startDate", "endDate", "description")));
        int index = 0;
        for (var dataPiece : data) {
            dataPiece.add(0, doctorsNamesData.get(index));
            index++;
        }

        var rawData = DataConverter.convertToRawData(data);
        surveysTable.getItems().addAll(Arrays.asList(rawData));
    }
}