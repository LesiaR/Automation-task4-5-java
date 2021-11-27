package appiumproject.api;

import appiumproject.api.models.TripRequest;
import appiumproject.api.models.TripResponse;
import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class HttpClient {
    private static final String BASIC_URL="https://trips-service-legacy.qa.ryanair.com";
    private static final String TRIP_ENDPOINT = "/trip";
    static TripResponse tripResponse;

    private static TripResponse sendRequest(TripRequest tripRequest) {
        Gson gson = new Gson();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(BASIC_URL+TRIP_ENDPOINT);
        httpPost.setHeader("Content-type", "application/json");
        try {
            httpPost.setEntity(new StringEntity(createJson(tripRequest)));
            CloseableHttpResponse response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Response status code is "+statusCode);

            String jsonResponse = EntityUtils.toString(response.getEntity());
            tripResponse = gson.fromJson(jsonResponse, TripResponse.class);
            client.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return tripResponse;
    }

    private static String createJson(TripRequest tripRequest){
        Gson gson = new Gson();
        String json = gson.toJson(tripRequest);
        return json;
    }

    public static TripResponse getTrip(TripRequest tripRequest){
        tripResponse = sendRequest(tripRequest);
        return tripResponse;
    }
}

