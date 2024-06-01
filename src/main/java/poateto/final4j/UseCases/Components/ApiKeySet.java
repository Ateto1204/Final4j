package poateto.final4j.UseCases.Components;
import java.io.FileInputStream;
import java.lang.System;
import java.util.Properties;
import java.io.IOException;

public class ApiKeySet {
    private static Properties prop = new Properties();
    public static String OPENAI_API_KEY;
    public static String COHERE_API_KEY;
    public static String GEMINI_projectId;
    public static String GEMINI_location;
    public static String GEMINI_modelName;

    public static void init() {
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            prop.load(fis);
            OPENAI_API_KEY = "demo";
            COHERE_API_KEY = prop.getProperty("COHERE_API_KEY");
            GEMINI_projectId = prop.getProperty("GEMINI_projectId");
            GEMINI_location = prop.getProperty("GEMINI_location");
            GEMINI_modelName = prop.getProperty("GEMINI_modelName");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
