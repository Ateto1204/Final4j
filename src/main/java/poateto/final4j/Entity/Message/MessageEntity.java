package poateto.final4j.Entity.Message;


import poateto.final4j.Entity.Message.MessageDependency;

public class MessageEntity implements MessageDependency {
    private String model;
    private String message;

    public MessageEntity() {}
    public MessageEntity(String model, String message) {
        this.model = model;
        this.message = message;
    }

    @Override
    public String getModel() { return model; }
    @Override
    public String getMessage() { return message; }
}
