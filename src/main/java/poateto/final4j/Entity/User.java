package poateto.final4j.Entity;

import java.util.Map;

public class User {
    private String email;
    private String name;
    private Map<String, Double> models;
    public User() {

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
    public void loadModels(Map<String, Double> models) {
        this.models = models;
    }

    public void notifyModel(String model, double value) {
        double origin = models.get(model);
        double latest = origin + value;
        models.replace(model, latest);
    }
}
