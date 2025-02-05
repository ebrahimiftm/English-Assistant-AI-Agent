import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class EnglishLearningAssistantBot extends TelegramLongPollingBot {

    private EnglishAssistantAgent agent;

    // سازنده که ایجنت شما را دریافت می‌کند
    public EnglishLearningAssistantBot(EnglishAssistantAgent agent) {
        this.agent = agent;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // بررسی می‌کنیم که پیام وجود داشته باشد و دارای متن است
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String userMessage = update.getMessage().getText();

            String response;
            try {
                // پردازش پرسش توسط ایجنت
                response = agent.processQuery(userMessage);
            } catch(Exception e) {
                response = "Error processing your query: " + e.getMessage();
            }

            // ارسال پیام پاسخ به کاربر
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
        // نام ربات که در تلگرام نمایش داده می‌شود
        return "EnglishLearningAssistantt_bot";
    }

    @Override
    public String getBotToken() {
        // توکن ربات که از تلگرام دریافت کرده‌اید (لطفاً توکن را به صورت ایمن نگهداری کنید)
        return "7503841969:AAGKtCVb0vckyLFWTiTYAF0jXRc1VYkngLE";
    }
}
