package home.helpers;

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
                    .header("accept", "application/json")
                    .header("Authorization","Bearer Kappa123")
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
}
