package poateto.final4j.UseCases.LanguageModel;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

import static poateto.final4j.UseCases.Components.ApiKeySet.*;

public class GeminiModel implements LanguageModelUseCase {

    private ChatLanguageModel gemini;

    public  GeminiModel() {
         gemini = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI_AI_KEY"))
                .modelName("gemini-1.5-flash")
                .build();
    }

    private static LanguageModelUseCase instance = new GeminiModel();
    public static LanguageModelUseCase getInstance() {
        return instance;
    }

    public String sendMessage(String prompt) {
        String response = gemini.generate(prompt);
        return response;
    }
}
