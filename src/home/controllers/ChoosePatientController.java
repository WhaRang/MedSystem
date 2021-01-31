package home.controllers;

import home.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ChoosePatientController implements Initializable {

    @FXML
    private Button btnChoose;

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
        if (event.getSource() == btnChoose) {
            Main.afterPatientChoose();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JSONArray jsonArr = DataHolder.patients;

        try {
            idColumn.setCellValueFactory(
                    ViewConfigurator.getStdCallbackForCVF("<no id>", 0));
            nameColumn.setCellValueFactory(
                    ViewConfigurator.getStdCallbackForCVF("<no name>", 1));

            var data =
                    ViewConfigurator.getColumnDataFromJsonArr(jsonArr, new ArrayList<>(Arrays.asList("id", "name")));

            var rawData = DataConverter.convertToRawData(data);

            patientTable.getItems().addAll(Arrays.asList(rawData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
