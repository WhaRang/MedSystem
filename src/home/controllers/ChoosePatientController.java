package home.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ChoosePatientController implements Initializable {

    @FXML
    private Button bntChoose;

    @FXML
    private TextField textFieldNameSurname;

    @FXML
    private TableView<String[]> patientTable;

    @FXML
    private TableColumn<String[], String> idColumn;

    @FXML
    private TableColumn<String[], String> nameColumn;

    @FXML
    private TextField textFieldID;

    @FXML
    private Button btnSearch;

    @FXML
    void handleClicks(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                URI.create("http://localhost:8083/medapp/getPatients"))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        JSONArray jsonArr = new JSONArray();
        try {
            assert response != null;
            jsonArr = (JSONArray) parser.parse(response.body());

            idColumn.setCellValueFactory((p)->{
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length>0 ? x[0] : "<no id>");
            });

            nameColumn.setCellValueFactory((p)->{
                String[] x = p.getValue();
                return new SimpleStringProperty(x != null && x.length>1 ? x[1] : "<no name>");
            });

            String[][] data = new String[jsonArr.size()][2];
            for (int i = 0; i < jsonArr.size(); i++) {
                data[i] = new String[]{"" + i,((JSONObject) jsonArr.get(i)).get("birthDay").toString() };
            }

            patientTable.getItems().addAll(Arrays.asList(data));
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert response != null;
    }
}
