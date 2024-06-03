package poateto.final4j.Entity;

import poateto.final4j.UseCases.Components.LanguageModelType;

public class LMMessage {
    private String model;
    private String message;

    public LMMessage() { }
    public LMMessage(String model, String message) {
        this.model = model;
        this.message = message;
    }

    public String getModel() {
        return model;
    }
    public String getMessage() {
        return message;
    }
}
