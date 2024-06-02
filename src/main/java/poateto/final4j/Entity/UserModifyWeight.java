package poateto.final4j.Entity;

import poateto.final4j.UseCases.Components.LanguageModelType;
import poateto.final4j.UseCases.Components.NotifyStatus;

public class UserModifyWeight {
    private String email;
    private String password;
    private String model;
    private NotifyStatus status;

    public UserModifyWeight() {

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getModel() {
        return model;
    }

    public NotifyStatus getStatus() {
        return status;
    }
}
