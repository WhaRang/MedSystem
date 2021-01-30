package home;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

public class JSONHandler {

    JSONParser parser;


    public JSONHandler() {
        parser = new JSONParser();
    }


    public JSONArray getJSONArrayFrom(String jsonStr) {

        JSONArray resultJSON = null;

        try {

            Object resultObj = parser.parse(jsonStr);
            if (resultObj instanceof JSONArray) {
                resultJSON = (JSONArray) resultObj;
            } else if (resultObj instanceof JSONObject) {
                resultJSON.add(resultObj);
            } else {
                throw new Exception("Unacceptable type: " + resultObj.getClass());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultJSON;
    }


    public JSONArray filterItemsByValue(JSONArray source, Object key, Object value) {

        JSONArray result = new JSONArray();

        for (Object item : source) {
            JSONObject jsonItem = (JSONObject) item;
            if (jsonItem.get(key).equals(value)) {
                result.add(item);
            }
        }

        return result;
    }


    public ArrayList<Object> getValuesArray(JSONArray source, Object key) {

        var result = new ArrayList<>();

        for (Object item : source) {
            JSONObject jsonItem = (JSONObject) item;

            if (jsonItem.containsKey(key)) {
                result.add(jsonItem.get(key));
            }
        }

        return result;
    }

}
