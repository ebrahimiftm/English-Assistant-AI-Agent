public class Main {
    public static void main(String[] args) {
        String apiUrl = "http://localhost:11434/api/chat";

        LanguageModel llamaModel = new Llama32Model(apiUrl);

        EnglishAssistantAgent assistantAgent = new EnglishAssistantAgent(llamaModel);

        assistantAgent.startConversation();
    }
}
