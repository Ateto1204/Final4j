package poateto.final4j.Entity;

import poateto.final4j.UseCases.Components.LanguageModelType;
import poateto.final4j.UseCases.Components.NotifyStatus;

public class UserModifyWeight extends UserOOP{
    private String model;
    private NotifyStatus status;

    public UserModifyWeight(String email, String pwd, String model, NotifyStatus status) {
        super(email, pwd);
        this.model=model;
        this.status=status;
    }

    public String getModel() {
        return model;
    }

    public NotifyStatus getStatus() {
        return status;
    }
}
