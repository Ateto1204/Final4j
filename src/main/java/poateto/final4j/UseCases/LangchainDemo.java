package poateto.final4j.UseCases;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import static java.time.Duration.ofSeconds;
public class LangChainDemo {
    public static void main(String[] args) {
        String apiKey = "demo";

        ChatLanguageModel model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-3.5-turbo")
                .temperature(1.0)
                .timeout(ofSeconds(60))
                .logRequests(true)
                .logResponses(true)
                .build();

        String prompt = "Explain in three lines how to make a beautiful painting";

        String response = model.generate(prompt);

        System.out.println(response);

    }
}
