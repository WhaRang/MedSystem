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


    public JSONArray filterItemsByMultipleValues(JSONArray source, Object key, ArrayList<String> values) {

        JSONArray result = new JSONArray();

        for (Object item : source) {

            JSONObject jsonItem = (JSONObject) item;
            String itemStr = String.valueOf(jsonItem.get(key));

            for (String value : values) {
                if (value.equals(itemStr)) {
                    result.add(item);
                }
            }
        }

        return result;
    }


    public JSONArray filterItemsByValue(JSONArray source, Object key, String value) {

        JSONArray result = new JSONArray();

        for (Object item : source) {

            JSONObject jsonItem = (JSONObject) item;
            String itemStr = String.valueOf(jsonItem.get(key));

            if (value.equals(itemStr)) {
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
