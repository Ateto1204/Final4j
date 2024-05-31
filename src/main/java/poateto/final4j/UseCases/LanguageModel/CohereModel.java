package poateto.final4j.UseCases.LanguageModel;

import com.cohere.api.Cohere;
import com.cohere.api.requests.ChatRequest;
import com.cohere.api.types.NonStreamedChatResponse;

import static poateto.final4j.UseCases.Components.ApiKeySet.*;

public class CohereModel implements LanguageModelUseCase {
    private Cohere cohere;

    public CohereModel() {
        cohere = Cohere.builder().token(COHERE_API_KEY).clientName("snippet").build();
    }

    private static CohereModel instance = new CohereModel();

    public static CohereModel getInstance() {
        return instance;
    }

    public String sendMessage(String prompt) {
        NonStreamedChatResponse response = cohere.chat(
                ChatRequest.builder()
                        .message(prompt)
                        .build()
        );
        return response.getText();
    }
}
