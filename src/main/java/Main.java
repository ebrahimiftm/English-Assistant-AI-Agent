 public class Main {
    public static void main(String[] args) {
        String apiUrl = "http://localhost:11434/api/chat";

        LanguageModel llamaModel = new Llama32Model(apiUrl);

        EnglishAssistantAgent assistantAgent = new EnglishAssistantAgent(llamaModel);

        assistantAgent.startConversation();
    }
}



/*import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {
        String apiUrl = "http://localhost:11434/api/chat";

        LanguageModel llamaModel = new Llama32Model(apiUrl);

        EnglishAssistantAgent assistantAgent = new EnglishAssistantAgent(llamaModel);

        Thread consoleThread = new Thread(new Runnable() {
            @Override
            public void run() {
                assistantAgent.startConversation();
            }
        });
        consoleThread.start();

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new EnglishLearningAssistantBot(assistantAgent));
            System.out.println("Telegram Bot is running. Check it at t.me/EnglishLearningAssistantt_bot");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}


 */