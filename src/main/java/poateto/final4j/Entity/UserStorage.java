package poateto.final4j.Entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static poateto.final4j.UseCases.Components.LanguageModelType.*;

public class UserStorage extends UserEntity {
    private String name;
    private List<String> sentMsg;
    private List<LMMessage> responsedMsg;
    private Map<String, Double> models;

    public UserStorage() {
        super();
        init();
    }
    public UserStorage(UserCreate user) {
        super(user.getEmail(), user.getPwd());
        this.name = user.getName();
        init();
    }

    private void init() {
        sentMsg = new ArrayList<>();
        responsedMsg = new ArrayList<>();

        models = new HashMap<>();
        models.put(OPENAI.name(), 1.0);
        models.put(COHERE.name(), 1.0);
        models.put(GEMINI.name(), 1.0);
    }

    public String getName() {
        return name;
    }
    public List<String> getSentMsg() {
        return sentMsg;
    }
    public List<LMMessage> getResponsedMsg() {
        return responsedMsg;
    }
    public Map<String, Double> getModels() {
        return models;
    }

    public void notifyModel(String model, double value) {
        double origin = models.get(model);
        double latest = origin + value;
        models.replace(model, latest);
    }

    public void sentMsg(String msg) {
        sentMsg.add(msg);
    }
    public void responsedMsg(LMMessage msg) {
        responsedMsg.add(msg);
    }
    public boolean checkPwd(String pwd) { return getPwd().equals(pwd); }
}
