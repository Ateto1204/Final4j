package poateto.final4j.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
    private String email;
    private String name;
    private String pwd;
    private List<String> sentMsg;
    private List<String> responsedMsg;
    private Map<String, Double> models;
    public User() {
        sentMsg = new ArrayList<>();
        responsedMsg = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getModels() {
        return models;
    }
    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void notifyModel(String model, double value) {
        double origin = models.get(model);
        double latest = origin + value;
        models.replace(model, latest);
    }

    public void sentMsg(String msg) {
        sentMsg.add(msg);
    }
    public List<String> getSentMsg() {
        return sentMsg;
    }

    public boolean checkPwd(String pwd) {
        return this.pwd.equals(pwd);
    }

    public void responsedMsg(String msg) {
        responsedMsg.add(msg);
    }
    public List<String> getResponsedMsg() {
        return responsedMsg;
    }
}
