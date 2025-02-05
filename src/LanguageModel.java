import java.io.IOException;

public interface LanguageModel {
    String chat(String question) throws IOException;
}
