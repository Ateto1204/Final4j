package poateto.final4j.Entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static poateto.final4j.UseCases.Components.LanguageModelType.*;

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

        models = new HashMap<>();
        models.put(OPENAI.name(), 1.0);
        models.put(COHERE.name(), 1.0);
        models.put(GEMINI.name(), 1.0);
    }

    public User(UserCreate user) {
        this();
        this.email = user.getEmail();
        this.name = user.getName();
        this.pwd = user.getPassword();
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

    public String getPwd() {
        return pwd;
    }

    public boolean checkPwd(String password) {
        return pwd.equals(password);
    }

    public void responsedMsg(String msg) {
        responsedMsg.add(msg);
    }
    public List<String> getResponsedMsg() {
        return responsedMsg;
    }
}
