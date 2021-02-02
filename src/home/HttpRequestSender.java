package home;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class HttpRequestSender {

    private final HttpClient client;

    public HttpRequestSender() {
        client = HttpClient.newHttpClient();
    }

    public String getResponseFromServer(String queryURL) {

        String responseStr = null;
        HttpResponse<String> responseHttp;

        try {
            var request = HttpRequest.newBuilder(
                    URI.create(queryURL))
                    .header("Authorization", Main.token)
                    .build();

            responseHttp = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (responseHttp.statusCode() == HttpStatusCodes.OK.value) {
                responseStr = responseHttp.body();
            } else {
                throw new Exception("Bad response. Code: " + responseHttp.statusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseStr;
    }


    public String getObjectByKeyAndValue(String tableName, String key, String value) {

        JSONObject objJson = new JSONObject();
        objJson.put(key, value);

        String responseStr = null;
        HttpResponse<String> responseHttp;

        try {
            var request = HttpRequest.newBuilder(
                    URI.create("http://localhost:8083/medapp/get" + tableName))
                    .header("Authorization", Main.token)
                    .POST(HttpRequest.BodyPublishers.ofString(objJson.toString()))
                    .build();

            responseHttp = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (responseHttp.statusCode() == HttpStatusCodes.OK.value) {
                responseStr = responseHttp.body();
            } else {
                throw new Exception("Bad response. Code: " + responseHttp.statusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseStr;
    }


    public String insertNewItemInTable(
            String tableName, ArrayList<String> keys, ArrayList<String> values) throws Exception {
        JSONObject objJson = new JSONObject();

        for (int i = 0; i < keys.size(); i++) {
            objJson.put(keys.get(i), values.get(i));
        }

        objJson.put("operation", "create" + tableName);

        HttpResponse<String> responseHttp = null;
        var request = HttpRequest.newBuilder(
                URI.create("http://localhost:8083/medapp/registerChange"))
                .header("Authorization", Main.token)
                .POST(HttpRequest.BodyPublishers.ofString(objJson.toString()))
                .build();

        responseHttp = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (responseHttp.statusCode() != HttpStatusCodes.OK.value) {
            throw new Exception("Bad response. Code: " + responseHttp.statusCode());
        }

        return responseHttp.body() + "empty";
    }


    public HttpResponse<String> getResponseFromOpenAPI(String queryURL, String body) {

        String responseStr = null;
        HttpResponse<String> responseHttp = null;

        try {
            var request = HttpRequest.newBuilder(
                    URI.create(queryURL))
                    .header("Authorization",
                            "eyJzY29wZSI6ImFsbCIsImlkIjoiMTIiLCJleHAiOiIyMDIxLTAxLTMxVDIzOjA4OjM5LjcwODA1NyswMTowMCJ9")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseHttp;
    }


    public HttpResponse<String> setTokenFromLogin(String username, String password, String auth) throws IOException, InterruptedException {
        JSONObject objJson = new JSONObject();
        objJson.put("username", username);
        objJson.put("auth", auth);
        objJson.put("password", password);

        String responseStr = null;
        HttpResponse<String> responseHttp;

        var request = HttpRequest.newBuilder(
                URI.create("http://localhost:8083/medapp/login"))
                .header("Authorization", "")
                .POST(HttpRequest.BodyPublishers.ofString(objJson.toString()))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());

    }
}
