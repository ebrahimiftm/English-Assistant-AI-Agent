import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class Llama32Model implements LanguageModel {
    private String apiUrl;
    private JSONArray conversationHistory;

    public Llama32Model(String apiUrl) {
        this.apiUrl = apiUrl;
        conversationHistory = new JSONArray();
        JSONObject systemMessage = new JSONObject();
        systemMessage.put("role", "system");
        systemMessage.put("content", "Hello! My name is English Learning Assistant Bot. I am here to help you improve your English skills. At the beginning of our conversation, greet the user in a friendly manner, then ask them: 'What level are you at and how much English do you know right now?' Next, inquire about their learning goals and what specific aspect they want to focus on (for example, grammar, conversation, IELTS, etc.). Finally, based on the user's responses, start teaching an appropriate lesson. You must not answer in any language other than English!");
        conversationHistory.put(systemMessage);
    }

    @Override
    public String chat(String question) throws IOException {
        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");
        userMessage.put("content", question);
        conversationHistory.put(userMessage);

        JSONObject requestData = new JSONObject();
        requestData.put("model", "llama3.2");
        requestData.put("messages", conversationHistory);
        requestData.put("stream", false);

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
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                responseBuffer.append(inputLine);
            }
        }

        JSONObject responseJson = new JSONObject(responseBuffer.toString());
        JSONObject messageObj = responseJson.getJSONObject("message");
        String reply = messageObj.getString("content");

        JSONObject assistantMessage = new JSONObject();
        assistantMessage.put("role", "assistant");
        assistantMessage.put("content", reply);
        conversationHistory.put(assistantMessage);

        return reply;
    }
}




