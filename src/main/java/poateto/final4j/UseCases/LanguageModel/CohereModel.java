package poateto.final4j.UseCases.LanguageModel;

import dev.langchain4j.model.cohere.CohereEmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.data.embedding.Embedding;
import java.time.Duration;

import static poateto.final4j.UseCases.Components.ApiKeySet.*;

public class CohereModel implements LanguageModelUseCase {

    private CohereEmbeddingModel cohere;

    public CohereModel() {
        cohere = CohereEmbeddingModel.builder()
                .baseUrl("https://api.cohere.ai/v1/")
                .apiKey(COHERE_API_KEY)
                .modelName("command")
                .timeout(Duration.ofSeconds(60))
                .logRequests(true)
                .logResponses(true)
                .build();
    }

    private static CohereModel instance = new CohereModel();

    public static CohereModel getInstance() {
        return instance;
    }

    @Override
    public String sendMessage(String prompt) {
        Response<Embedding> response = cohere.embed(prompt);
        return String.format("Embedding created with dimension: %d", response.content().dimension());
    }
}