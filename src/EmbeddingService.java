import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class EmbeddingService {
    private String apiUrl;

    public EmbeddingService(String apiUrl) {
        this.apiUrl = apiUrl;
    }


    public double[] getEmbedding(String text) throws Exception {
        JSONObject requestData = new JSONObject();
        requestData.put("model", "nomic-embed-text");
        requestData.put("prompt", text);


        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(requestData.toString().getBytes("UTF-8"));
        }

        StringBuilder responseBuffer = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                responseBuffer.append(line);
            }
        }

        JSONObject responseJson = new JSONObject(responseBuffer.toString());
        JSONArray embeddingArray = responseJson.getJSONArray("embedding");
        double[] embedding = new double[embeddingArray.length()];
        for (int i = 0; i < embeddingArray.length(); i++) {
            embedding[i] = embeddingArray.getDouble(i);
        }
        return embedding;
    }
}
