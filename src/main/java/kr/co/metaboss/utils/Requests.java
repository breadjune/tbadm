package kr.co.metaboss.utils;

import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Log4j2
public class Requests {

    private final String requestUrl;
    private final JSONObject data;

    public Requests(String requestUrl, JSONObject data) {
        this.requestUrl = requestUrl;
        this.data = data;
    }

    public JSONArray postAndArrayResponse() {
        String response = this.post();
        log.info("postAndArrayResponse : " + response);
        if (!response.isEmpty()) return new JSONArray(response);
        else return null;
    }

    public JSONObject postAndObjectResponse() {
        String response = this.post();
        log.info("postAndObjectResponse : " + response);
        if (!response.isEmpty()) {
            return new JSONObject(response);
        } else return null;
    }

    public String post() {
        log.info("data : " + this.data.toString());
        HttpURLConnection conn;
        String result = "";
        try {
            URL url = new URL(requestUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            writer.write(data.toString());
            writer.close();

            int responseCode = conn.getResponseCode();
            StringBuilder response = new StringBuilder();
            String line;
            BufferedReader reader;

            if (responseCode == HttpURLConnection.HTTP_OK) reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            else reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            result = response.toString();
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
        return result;
    }
}
