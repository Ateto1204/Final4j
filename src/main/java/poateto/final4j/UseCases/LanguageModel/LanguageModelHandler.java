package poateto.final4j.UseCases.LanguageModel;

import okhttp3.OkHttpClient;
import poateto.final4j.UseCases.Components.LanguageModelType;

import java.util.concurrent.TimeUnit;

import static poateto.final4j.UseCases.Components.LanguageModelType.*;

public class LanguageModelHandler {
    CohereModel cohere = CohereModel.getInstance();
    OpenAiModel openai = OpenAiModel.getInstance();
    OkHttpClient client;

    public LanguageModelHandler() {
        client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    public String sendMessage(LanguageModelType model, String prompt) {
        String response = "error";
        if (model == OPENAI) {
            response = openai.sendMessage(prompt);
        } else if (model == COHERE) {
            response = cohere.sendMessage(prompt);
        }
        return response;
    }
}
