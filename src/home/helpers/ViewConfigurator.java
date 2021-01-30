package home.helpers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewConfigurator {

    public static Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>
    getStdCallbackForCVF(String emptyStrValue, int columnIndex) {

        return (TableColumn.CellDataFeatures<String[], String> p) -> {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > columnIndex ? x[columnIndex] : emptyStrValue);
        };
    }


    public static String[][] getColumnDataFromJsonArr(JSONArray jsonArr, String[] rawColumnNamesArr) {

        ArrayList<String> columnNamesArr = new ArrayList<>(Arrays.asList(rawColumnNamesArr));
        String[][] data = new String[jsonArr.size()][columnNamesArr.size()];

        for (int i = 0; i < jsonArr.size(); i++) {

            ArrayList<String> rowData = new ArrayList<>();
            for (String columnName : columnNamesArr) {
                rowData.add(((JSONObject) jsonArr.get(i)).get(columnName).toString());
            }

            String[] rawRowData = new String[rowData.size()];
            rawRowData = rowData.toArray(rawRowData);

            data[i] = rawRowData;
        }

        return data;
    }
}
