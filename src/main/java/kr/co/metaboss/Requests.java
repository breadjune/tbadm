package kr.co.metaboss;

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

    public JSONArray post() {
        JSONArray result = null;
        HttpURLConnection conn = null;
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
            result = new JSONArray(response.toString());
        } catch (IOException e) {
            log.error(e.getStackTrace());
        }
        return result;
    }

}
