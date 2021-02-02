package home;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequestSender {

    private HttpClient client;

    public HttpRequestSender() {
        client = HttpClient.newHttpClient();
    }

    public String getResponseFromServer(String queryURL) {

        String responseStr = null;
        HttpResponse<String> responseHttp = null;

        try {
            var request = HttpRequest.newBuilder(
                    URI.create(queryURL))
                    .header("Authorization",
                            "eyJzY29wZSI6ImFsbCIsImlkIjoiMTIiLCJleHAiOiIyMDIxLTAxLTMxVDIzOjA4OjM5LjcwODA1NyswMTowMCJ9")
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

    public HttpResponse<String> getResponseFromServer(String queryURL, String body) {

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
}
