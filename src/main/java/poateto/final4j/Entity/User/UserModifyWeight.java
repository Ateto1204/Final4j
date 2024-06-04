package poateto.final4j.Entity.User;

import poateto.final4j.Entity.User.UserEntity;
import poateto.final4j.UseCases.Components.NotifyStatus;

public class UserModifyWeight extends UserEntity {
    private String model;
    private NotifyStatus status;

    public UserModifyWeight() { }

    public String getModel() {
        return model;
    }
    public NotifyStatus getStatus() {
        return status;
    }
}
