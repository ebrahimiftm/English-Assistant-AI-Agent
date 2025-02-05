import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBotMain {
    public static void main(String[] args) {
        String chatApiUrl = "http://localhost:11434/api/chat";
        LanguageModel llamaModel = new Llama32Model(chatApiUrl);

        EnglishAssistantAgent agent = new EnglishAssistantAgent(llamaModel);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new EnglishLearningAssistantBot(agent));
            System.out.println("Telegram Bot is running.");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
