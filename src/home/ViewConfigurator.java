package home;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class ViewConfigurator {

    public static Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>
    getStdCallbackForCVF(String emptyStrValue, int columnIndex) {

        return (TableColumn.CellDataFeatures<String[], String> p) -> {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > columnIndex ? x[columnIndex] : emptyStrValue);
        };
    }


    public static ArrayList<ArrayList<String>> getColumnDataFromJsonArr(JSONArray jsonArr, ArrayList<String> columnNamesArr) {

        ArrayList<ArrayList<String>> data = new ArrayList<>();

        for (int i = 0; i < jsonArr.size(); i++) {

            ArrayList<String> rowData = new ArrayList<>();
            for (String columnName : columnNamesArr) {
                rowData.add(((JSONObject) jsonArr.get(i)).get(columnName).toString());
            }

            data.add(rowData);
        }

        return data;
    }
}
