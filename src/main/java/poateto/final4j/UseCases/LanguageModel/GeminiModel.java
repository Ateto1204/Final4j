package poateto.final4j.UseCases.LanguageModel;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.preview.GenerativeModel;
import com.google.cloud.vertexai.generativeai.preview.ResponseHandler;

import static poateto.final4j.UseCases.Components.ApiKeySet.*;

import java.io.IOException;

public class GeminiModel implements LanguageModelUseCase {
    private final String projectId = GEMINI_projectId;
    private final String location = GEMINI_location;
    private final String modelName = GEMINI_modelName;

    private GeminiModel() {

    }

    private static GeminiModel instance = new GeminiModel();
    public static GeminiModel getInstance() {
        return instance;
    }

    public String sendMessage(String prompt) {
        String output = "error";
        try {
            try (VertexAI vertexAI = new VertexAI(projectId, location)) {
                GenerativeModel model = new GenerativeModel(modelName, vertexAI);
                GenerateContentResponse response = model.generateContent(prompt);
                output = ResponseHandler.getText(response);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return output;
    }
}
