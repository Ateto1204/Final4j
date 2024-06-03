package poateto.final4j.Entity;

import poateto.final4j.UseCases.Components.LanguageModelType;

public class LMMessage extends MessageEntity {

    public LMMessage() { }
    public LMMessage(String model, String message) { super(model, message); }
}
