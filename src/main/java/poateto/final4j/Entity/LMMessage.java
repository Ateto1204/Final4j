package poateto.final4j.Entity;

import poateto.final4j.UseCases.Components.LanguageModelType;

public class LMMessage {
    private LanguageModelType model;
    private String message;

    public LMMessage(LanguageModelType model, String message) {
        this.model = model;
        this.message = message;
    }
}
