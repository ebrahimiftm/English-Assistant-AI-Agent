import java.io.IOException;
import java.util.Scanner;

public class EnglishAssistantAgent {
    private LanguageModel languageModel;

    public EnglishAssistantAgent(LanguageModel languageModel) {
        this.languageModel = languageModel;
    }

    public void startConversation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Conversation started. Type 'exit' to quit.");
        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Conversation ended.");
                break;
            }
            try {
                String response = languageModel.chat(userInput);
                System.out.println("Assistant: " + response);
            } catch (IOException e) {
                System.err.println("Error communicating with the API: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
