English Learning Assistant Bot

English Learning Assistant Bot is an interactive English learning application powered by the Llama 3.2 language model. The bot is designed to help users improve their English skills through engaging, conversational lessons and interactive exercises. The project provides two modes of interaction: a console-based interface and a Telegram bot, allowing users to learn English in various environments.

 Features

- Interactive Learning: Engage in real-time conversations with an AI assistant that offers structured lessons tailored to your proficiency level and learning goals.
- Multi-Interface Support: Use the bot both in a console environment and on Telegram for a seamless learning experience.
- Customizable Lessons: The assistant greets the user, asks about their current English proficiency and learning goals (e.g., grammar, conversation, IELTS, etc.), and then provides appropriate lessons.
- Extensible Architecture: Built in Java, the project is modular and can be extended with additional features such as chat history storage and advanced vector database integration.

Installation

1. Clone the Repository:

   ```bash
   git clone https://github.com/ebrahimiftm/english-assistant-ai-agent.git
   cd english-learning-assistant-bot
   ```

2. Dependencies:

   Ensure you have JDK 11 or later installed. The project requires the following external libraries:

   - [telegrambots-6.5.0.jar](https://repo1.maven.org/maven2/org/telegram/telegrambots/6.5.0/telegrambots-6.5.0.jar)
   - [telegrambots-meta-6.5.0.jar](https://repo1.maven.org/maven2/org/telegram/telegrambots-meta/6.5.0/telegrambots-meta-6.5.0.jar)
   - [Apache HttpClient 4.5.13](https://repo1.maven.org/maven2/org/apache/httpcomponents/httpclient/4.5.13/httpclient-4.5.13.jar)
   - [Apache HttpCore 4.4.14](https://repo1.maven.org/maven2/org/apache/httpcomponents/httpcore/4.4.14/httpcore-4.4.14.jar)
   - [Commons Logging 1.2](https://repo1.maven.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar)
   - [SLF4J API 1.7.36](https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar)
   - [SLF4J Simple 1.7.36](https://repo1.maven.org/maven2/org/slf4j/slf4j-simple/1.7.36/slf4j-simple-1.7.36.jar)
   - [Apache Commons Lang3 3.12.0](https://repo1.maven.org/maven2/org/apache/commons/commons-lang3/3.12.0/commons-lang3-3.12.0.jar)

   Alternatively, if you're using Maven or Gradle, add the corresponding dependencies to your configuration file.

3. Compile and Run:

   - To run the console interface, execute the `Main.java` class.
   - To run the Telegram bot, ensure no other instance is running (to avoid conflicts), then execute `Main.java` as it starts both interfaces concurrently.

Usage

- Console Mode:
  Run `Main.java` to start an interactive conversation in the console. Type your messages, and the assistant will respond in English.

- Telegram Bot Mode:
  The bot is available on Telegram at [t.me/EnglishLearningAssistantt_bot](https://t.me/EnglishLearningAssistantt_bot). Ensure only one instance of the bot is running to avoid update conflicts.

Customization

- ystem Prompt:
  You can modify the system prompt in `Llama32Model.java` to adjust the assistant’s behavior. The current prompt instructs the assistant to greet the user, ask about their English proficiency and learning goals, and then provide appropriate lessons.
- Extending Functionality:
  You can add features such as chat history persistence or integration with external vector databases for improved search capabilities.

Contributing

Contributions, suggestions, and bug reports are welcome! Feel free to fork the repository and open a pull request.
