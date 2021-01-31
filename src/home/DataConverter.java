package home;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class DataConverter {

    public static String[][] convertToRawData(ArrayList<ArrayList<String>> data) {
        String[][] rawData = new String[data.size()][];

        int index = 0;
        for (var dataPiece : data) {
            rawData[index] = new String[dataPiece.size()];
            rawData[index] = dataPiece.toArray(rawData[index]);
            index++;
        }

        return rawData;
    }


    public static ArrayList<String> convertJsonToStrArrByKey(JSONArray jsonArr, String key) {
        ArrayList<String> result = new ArrayList<>();

        for (Object obj : jsonArr) {
            JSONObject objJson = (JSONObject) obj;
            result.add(objJson.get(key).toString());
        }

        return result;
    }


    public static ArrayList<String> convertObjArrToStrArr(ArrayList<Object> objArr) {
        ArrayList<String> result = new ArrayList<>();
        for (Object obj : objArr) {
            result.add(obj.toString());
        }

        return result;
    }


    public static String getDateFromTimeStamp(String timeStamp) {
        return timeStamp.substring(0, timeStamp.indexOf("T"));
    }


    public static String getTimeFromTimeStamp(String timeStamp) {
        return timeStamp.substring(timeStamp.indexOf("T") + 1, timeStamp.indexOf("."));
    }
}
