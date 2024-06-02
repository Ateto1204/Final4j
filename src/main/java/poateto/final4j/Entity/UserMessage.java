package poateto.final4j.Entity;

public class UserMessage extends UserOOP{
    private String message;

    public UserMessage(String email, String pwd, String message) {
        super(email, pwd);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
