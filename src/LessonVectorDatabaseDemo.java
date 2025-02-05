import java.util.List;

public class LessonVectorDatabaseDemo {
    public static void main(String[] args) {
        String lessonText = "Lesson 1: Foundations of English Language\n\n" +
                "1. Introduction\n" +
                "English is a global language essential for communication in education, business, and daily life. " +
                "This lesson introduces you to the basic elements of English, aiming to build a strong foundation for further learning.\n\n" +
                "2. Vocabulary Building\n\n" +
                "2.1. Everyday Vocabulary\n" +
                "Greetings and Introductions:\n" +
                "  - Hello, Hi, Good morning, Good evening\n" +
                "  - My name is…; I am…\n" +
                "Common Expressions:\n" +
                "  - How are you?\n" +
                "  - Thank you, Please, Excuse me\n\n" +
                "2.2. Strategies for Learning Vocabulary\n" +
                "Flashcards: Create flashcards with words and their meanings.\n" +
                "Contextual Learning: Read simple texts or watch videos in English.\n" +
                "Daily Practice: Learn 5–10 new words every day and review regularly.\n\n" +
                "3. Basic Grammar Concepts\n\n" +
                "3.1. Parts of Speech\n" +
                "Nouns: Words that name people, places, or things (for example, book, teacher).\n" +
                "Verbs: Words that express actions or states (for example, run, is).\n" +
                "Adjectives: Words that describe nouns (for example, happy, blue).\n" +
                "Adverbs: Words that modify verbs, adjectives, or other adverbs (for example, quickly, very).\n\n" +
                "3.2. Simple Sentence Structure\n" +
                "Basic Pattern:\n" +
                "  - Subject + Verb + Object\n" +
                "    Example: \"She reads books.\"\n" +
                "Questions and Negatives:\n" +
                "  - To form questions: “Do you like coffee?”\n" +
                "  - To form negatives: “He does not like tea.”\n\n" +
                "3.3. Tenses Overview\n" +
                "Simple Present:\n" +
                "  - Used for habits and facts.\n" +
                "    Example: \"I work every day.\"\n" +
                "Simple Past:\n" +
                "  - Describes completed actions.\n" +
                "    Example: \"They visited the park yesterday.\"\n" +
                "Simple Future:\n" +
                "  - For actions that will happen.\n" +
                "    Example: \"We will travel next month.\"\n\n" +
                "4. Developing Listening and Speaking Skills\n\n" +
                "4.1. Listening Techniques\n" +
                "Active Listening: Focus on key words and try to understand the main idea.\n" +
                "Multimedia Exposure: Listen to English podcasts, songs, and watch videos with subtitles initially.\n" +
                "Note-Taking: Write down new words or phrases you hear and look them up later.\n\n" +
                "4.2. Speaking Practice\n" +
                "Repetition and Drills: Practice repeating sentences and phrases to improve pronunciation.\n" +
                "Language Exchange: Find a partner or join conversation clubs where you can practice speaking.\n" +
                "Record Yourself: Listening to your own recordings helps identify areas for improvement.\n\n" +
                "5. Enhancing Reading and Writing Abilities\n\n" +
                "5.1. Reading Skills\n" +
                "Choose Simple Texts: Start with short articles, children’s books, or graded readers.\n" +
                "Skimming and Scanning: Practice reading for the main idea (skimming) and looking for specific information (scanning).\n" +
                "Vocabulary in Context: When encountering new words, try to deduce their meaning from the context.\n\n" +
                "5.2. Writing Practice\n" +
                "Journal Writing: Write a short daily journal entry in English about your day.\n" +
                "Structured Writing: Practice writing emails or short paragraphs with a clear introduction, body, and conclusion.\n" +
                "Feedback: Share your writing with teachers or peers to receive constructive feedback.\n\n" +
                "6. Tips for Effective Learning\n\n" +
                "Regular Practice: Even short daily sessions make a significant difference.\n" +
                "Set Achievable Goals: Define clear, small goals (for example, learn 5 new words a day) to track progress.\n" +
                "Engage with the Language: Surround yourself with English through music, films, and conversations.\n" +
                "Don’t Fear Mistakes: Mistakes are a natural part of the learning process. Learn from them and keep practicing.\n\n" +
                "7. Recommended Resources\n\n" +
                "Books:\n" +
                "  - English Grammar in Use by Raymond Murphy\n" +
                "  - Vocabulary in Use series\n" +
                "Online Platforms and Apps:\n" +
                "  - Duolingo, BBC Learning English, and Memrise for interactive exercises.\n" +
                "Multimedia:\n" +
                "  - YouTube channels like \"English Addict\" and \"Speak English With Vanessa\" for lessons on pronunciation and usage.\n" +
                "Podcasts:\n" +
                "  - \"The English We Speak\" and \"6 Minute English\" for short, engaging lessons.\n\n" +
                "8. Conclusion\n\n" +
                "This lesson provides a starting point for mastering English. By focusing on essential vocabulary, basic grammar, and regularly practicing listening, speaking, reading, and writing, you can build a strong foundation in English. Use this material as a reference for your daily studies and gradually expand your learning with more advanced topics and resources.";


        String[] chunks = lessonText.split("\\n\\n");

        EmbeddingService embeddingService = new EmbeddingService("http://localhost:11434/api/embeddings");
        VectorIndex vectorIndex = new VectorIndex();

        int idCounter = 1;
        for (String chunk : chunks) {
            String trimmed = chunk.trim();
            if (trimmed.isEmpty()) continue;
            try {
                double[] vector = embeddingService.getEmbedding(trimmed);
                String metadata = trimmed.length() > 100 ? trimmed.substring(0, 100) + "..." : trimmed;
                VectorRecord record = new VectorRecord("Lesson1_" + idCounter, vector, metadata);
                vectorIndex.addRecord(record);
                idCounter++;
            } catch (Exception e) {
                System.err.println("Error processing chunk: " + e.getMessage());
            }
        }


    }
}
