package poateto.final4j.UseCases.LanguageModel;

import okhttp3.OkHttpClient;
import poateto.final4j.UseCases.Components.LanguageModelType;

import java.util.concurrent.TimeUnit;

import static poateto.final4j.UseCases.Components.LanguageModelType.*;

public class LanguageModelHandler {
    private CohereModel cohere = CohereModel.getInstance();
    private OpenAiModel openai = OpenAiModel.getInstance();
    private GeminiModel gemini = GeminiModel.getInstance();
    private OkHttpClient client;

    public LanguageModelHandler() {
        client = new OkHttpClient.Builder()
                .connectTimeout(40, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)
                .build();
    }

    public String sendMessage(LanguageModelType model, String prompt) {
        String response = "error";
        if (model == OPENAI) {
            response = openai.sendMessage(prompt);
        } else if (model == COHERE) {
            response = cohere.sendMessage(prompt);
        } else if (model == GEMINI) {
            response = gemini.sendMessage(prompt);
        }
        return response;
    }
}
