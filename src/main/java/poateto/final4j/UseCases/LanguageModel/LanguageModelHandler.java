package poateto.final4j.UseCases.LanguageModel;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import poateto.final4j.UseCases.Components.LanguageModelType;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static poateto.final4j.UseCases.Components.LanguageModelType.*;

public class LanguageModelHandler {
    private LanguageModelUseCase model;
    private OkHttpClient client;

    public LanguageModelHandler() {
        client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        IOException exception = null;
                        for (int i = 0; i < 3; i++) {
                            try {
                                return chain.proceed(request);
                            } catch (IOException e) {
                                exception = e;
                            }
                        }
                        throw exception;
                    }
                })
                .build();
    }

    public String sendMessage(LanguageModelType modelType, String prompt) {
        String response = "Error occur, please try again";
        try {
            if (modelType == OPENAI) {
                model = OpenAiModel.getInstance();
            } else if (modelType == COHERE) {
                model = CohereModel.getInstance();
            } else if (modelType == GEMINI) {
                model = GeminiModel.getInstance();
            }
            response = model.sendMessage(prompt);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return response;
    }
}
