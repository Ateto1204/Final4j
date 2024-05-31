package poateto.final4j.UseCases.LanguageModel;

import dev.langchain4j.model.openai.OpenAiChatModel;

import static poateto.final4j.UseCases.Components.ApiKeySet.*;

public class OpenAiModel implements LanguageModelUseCase {
    private OpenAiChatModel openai;

    public OpenAiModel() {
        openai = OpenAiChatModel.withApiKey(OPENAI_API_KEY);
    }

    private static OpenAiModel instance = new OpenAiModel();

    public static OpenAiModel getInstance() {
        return instance;
    }

    public String sendMessage(String prompt) {
        String response = openai.generate(prompt);
        return response;
    }
}
