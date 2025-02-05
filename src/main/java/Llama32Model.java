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
        systemMessage.put("content", "You are an English language learning assistant. At the beginning of the conversation, you must greet the user warmly. Then, ask the user for their current English proficiency level, followed by inquiring about their goal for learning English. Based on the provided level and learning goal, start teaching the appropriate lesson to the user.\n Your task is to help Persian-speaking users learn English. Even if a user writes in Persian, you must respond exclusively in simple English that is easy for lower-level learners to understand. Begin by asking the user for their current English proficiency level. Then ask which area (e.g., grammar, conversation, etc.) they want to focus on and what their learning goal is. Once the goal is determined, start teaching an English lesson interactively. Provide an initial lesson, ask if they understood it, and if they have, offer some related exercises before moving on to the next lesson. If they did not understand, explain the lesson again in simpler terms, ask again if they understand, and then provide exercises. Continue this interactive process for subsequent lessons.");
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
        String assistantReply = messageObj.getString("content");

        JSONObject assistantMessage = new JSONObject();
        assistantMessage.put("role", "assistant");
        assistantMessage.put("content", assistantReply);
        conversationHistory.put(assistantMessage);

        return assistantReply;
    }
}
