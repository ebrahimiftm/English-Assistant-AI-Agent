import java.io.IOException;

public class EnglishAssistantAgent {
    private LanguageModel languageModel;

    public EnglishAssistantAgent(LanguageModel languageModel) {
        this.languageModel = languageModel;
    }

    public void startConversation() {
    }

    public String processQuery(String query) throws IOException {
        return languageModel.chat(query);
    }
}
