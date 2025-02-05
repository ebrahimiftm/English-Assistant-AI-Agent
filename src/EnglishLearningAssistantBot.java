 import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class EnglishLearningAssistantBot extends TelegramLongPollingBot {

    private EnglishAssistantAgent agent;

    public EnglishLearningAssistantBot(EnglishAssistantAgent agent) {
        this.agent = agent;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String userMessage = update.getMessage().getText();

            String response;
            try {
                response = agent.processQuery(userMessage);
            } catch(Exception e) {
                response = "Error processing your query: " + e.getMessage();
            }

            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(response);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "EnglishLearningAssistantt_bot";
    }

    @Override
    public String getBotToken() {
        return "7503841969:AAGKtCVb0vckyLFWTiTYAF0jXRc1VYkngLE";
    }
}


/*import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class EnglishLearningAssistantBot extends TelegramLongPollingBot {

    private EnglishAssistantAgent agent;

    public EnglishLearningAssistantBot(EnglishAssistantAgent agent) {
        this.agent = agent;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String userMessage = update.getMessage().getText();
            String response;
            try {
                response = agent.processQuery(userMessage);
            } catch (Exception e) {
                response = "Error processing your query: " + e.getMessage();
            }
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(response);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "EnglishLearningAssistantt_bot";
    }

    @Override
    public String getBotToken() {
        return "7503841969:AAGKtCVb0vckyLFWTiTYAF0jXRc1VYkngLE";
    }
}


 */