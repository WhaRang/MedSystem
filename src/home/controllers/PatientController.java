package home.controllers;

import home.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    public static int patientID;

    @FXML
    private TableView<String[]> insuranceTableView;

    @FXML
    private TableColumn<String[], String> insuranceIdColumn;

    @FXML
    private TableColumn<String[], String> insuranceExpDateColumn;

    @FXML
    private TableColumn<String[], String> insuranceDescColumn;

    @FXML
    private TableView<String[]> appointmentTableView;

    @FXML
    private TableColumn<String[], String> appointmentIdColumn;

    @FXML
    private TableColumn<String[], String> appointmentTimeColumn;

    @FXML
    private TableColumn<String[], String> appointmentDescColumn;

    @FXML
    private TableColumn<String[], String> appointmentResultColumn;

    @FXML
    private TableView<String[]> researchesTableView;

    @FXML
    private TableColumn<String[], String> researchesIdColumn;

    @FXML
    private TableColumn<String[], String> researchesStartDateColumn;

    @FXML
    private TableColumn<String[], String> researchesEndDateColumn;

    @FXML
    private TableColumn<String[], String> researchesDescColumn;

    @FXML
    private TableView<String[]> recipesTableView;

    @FXML
    private TableColumn<String[], String> recipesIdColumn;

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

    JSONObject patientJSONobj;
    JSONArray insuranceJSONarr;
    JSONArray allergiesJSONarr;
    JSONArray patientDiseasesJSONarr;
    JSONArray diseasesJSONarr;
    JSONArray referralsJSONarr;

    //TODO
    JSONObject appointmentsJSONobj;

    JSONArray researchesJSONarr;
    JSONArray recipesJSONarr;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadInfo();
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
        printVisits();
        printResearches();
        printRecipes();
    }


    private void printRecipes() {
        try {
            recipesIdColumn.setCellValueFactory((p) -> {
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length > 0 ? x[0] : "<no id>");
            });

            recipesDateStartColumn.setCellValueFactory((p) -> {
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length > 0 ? x[0] : "<no start_date>");
            });

            recipesDateEndColumn.setCellValueFactory((p) -> {
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length > 1 ? x[1] : "<no end_date>");
            });

            String[][] data = new String[appointmentsJSONobj.size()][3];
            for (int i = 0; i < appointmentsJSONobj.size(); i++) {
                data[i] = new String[]{
                        ((JSONObject) recipesJSONarr.get(i)).get("id").toString(),
                        ((JSONObject) recipesJSONarr.get(i)).get("startDate").toString(),
                        ((JSONObject) recipesJSONarr.get(i)).get("endDate").toString()};
            }


            recipesTableView.getItems().addAll(Arrays.asList(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void printResearches() {

        try {
            researchesIdColumn.setCellValueFactory((p) -> {
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length > 0 ? x[0] : "<no id>");
            });

            researchesStartDateColumn.setCellValueFactory((p) -> {
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length > 0 ? x[0] : "<no start_date>");
            });

            researchesDescColumn.setCellValueFactory((p) -> {
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length > 1 ? x[1] : "<no desc>");
            });

            researchesEndDateColumn.setCellValueFactory((p) -> {
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length > 1 ? x[1] : "<no end_date>");
            });

            //TODO
            String[][] data = new String[appointmentsJSONobj.size()][4];
            for (int i = 0; i < 1; i++) {
                data[i] = new String[]{
                        appointmentsJSONobj.get("id").toString(),
                        appointmentsJSONobj.get("startDate").toString(),
                        appointmentsJSONobj.get("endDate").toString(),
                        appointmentsJSONobj.get("description").toString()};
            }

            researchesTableView.getItems().addAll(Arrays.asList(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void printVisits() {

        try {
            appointmentIdColumn.setCellValueFactory((p) -> {
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length > 0 ? x[0] : "<no id>");
            });

            appointmentTimeColumn.setCellValueFactory((p) -> {
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length > 0 ? x[0] : "<no time>");
            });

            appointmentDescColumn.setCellValueFactory((p) -> {
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length > 1 ? x[1] : "<no desc>");
            });

            appointmentResultColumn.setCellValueFactory((p) -> {
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length > 1 ? x[1] : "<no result>");
            });

            String[][] data = new String[appointmentsJSONobj.size()][5];
            for (int i = 0; i < appointmentsJSONobj.size(); i++) {
                data[i] = new String[]{
                        ((JSONObject) appointmentsJSONobj.get(i)).get("id").toString(),
                        ((JSONObject) appointmentsJSONobj.get(i)).get("timeStamp").toString(),
                        ((JSONObject) appointmentsJSONobj.get(i)).get("description").toString(),
                        ((JSONObject) appointmentsJSONobj.get(i)).get("result").toString()};
            }

            appointmentTableView.getItems().addAll(Arrays.asList(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadInfo() {
        
        var client = HttpClient.newHttpClient();

        HttpResponse<String> response = null;
        try {
            JSONParser parser = new JSONParser();

            patientJSONobj = new JSONObject();
            allergiesJSONarr = new JSONArray();
            patientDiseasesJSONarr = new JSONArray();
            recipesJSONarr = new JSONArray();
            appointmentsJSONobj = new JSONObject();
            researchesJSONarr = new JSONArray();
            insuranceJSONarr = new JSONArray();
            referralsJSONarr = new JSONArray();

            //PATIENT
            var request = HttpRequest.newBuilder(
                    URI.create("http://localhost:8083/medapp/getPatients&id="+patientID))
                    .header("accept", "application/json")
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assert response != null;
            patientJSONobj = (JSONObject) parser.parse(response.body());

            //VISITS
            request = HttpRequest.newBuilder(
                    URI.create("http://localhost:8083/medapp/getVisits"))
                    .header("accept", "application/json")
                    .build();

            //TODO create array, not object
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assert response != null;
            appointmentsJSONobj = (JSONObject) parser.parse(response.body());

            //ALLERGIES
            /*request = HttpRequest.newBuilder(
                    URI.create("http://localhost:8083/medapp/getHypersensitivities"))
                    .header("accept", "application/json")
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assert response != null;
            allergiesJSONarr = (JSONArray) parser.parse(response.body());
*/
            //PATIENT DISEASES
            request = HttpRequest.newBuilder(
                    URI.create("http://localhost:8083/medapp/getPatientDiseases"))
                    .header("accept", "application/json")
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assert response != null;
            patientDiseasesJSONarr = (JSONArray) parser.parse(response.body());

            //DISEASES
            request = HttpRequest.newBuilder(
                    URI.create("http://localhost:8083/medapp/getDiseases"))
                    .header("accept", "application/json")
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assert response != null;
            diseasesJSONarr = (JSONArray) parser.parse(response.body());

            //RECIPES
            request = HttpRequest.newBuilder(
                    URI.create("http://localhost:8083/medapp/getPrescription"))
                    .header("accept", "application/json")
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assert response != null;
            recipesJSONarr = (JSONArray) parser.parse(response.body());

            //INSURANCES
            request = HttpRequest.newBuilder(
                    URI.create("http://localhost:8083/medapp/getInsurances"))
                    .header("accept", "application/json")
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assert response != null;
            insuranceJSONarr = (JSONArray) parser.parse(response.body());

            //REFERRALS
            request = HttpRequest.newBuilder(
                    URI.create("http://localhost:8083/medapp/getReferrals"))
                    .header("accept", "application/json")
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assert response != null;
            referralsJSONarr = (JSONArray) parser.parse(response.body());

            //RESEARCHES
            request = HttpRequest.newBuilder(
                    URI.create("http://localhost:8083/medapp/getResearches"))
                    .header("accept", "application/json")
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            assert response != null;
            researchesJSONarr = (JSONArray) parser.parse(response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }

        assert response != null;
    }
}