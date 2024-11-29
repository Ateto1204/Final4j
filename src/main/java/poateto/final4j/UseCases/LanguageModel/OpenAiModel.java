package poateto.final4j.UseCases.LanguageModel;

import dev.langchain4j.model.openai.OpenAiChatModel;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;
import static poateto.final4j.UseCases.Components.ApiKeySet.*;

public class OpenAiModel implements LanguageModelUseCase {
    private OpenAiChatModel openai;

    public OpenAiModel() {
        openai = OpenAiChatModel.builder()
                .apiKey(OPENAI_API_KEY)
                .modelName(GPT_4_O_MINI) // temporary usage for "OPENAI_API_KEY=demo"
                .build();
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
