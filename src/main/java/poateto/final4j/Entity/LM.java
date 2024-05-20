package poateto.final4j.Entity;

public class LM {
    private String name;
    private int weight;

    public LM() {
        this.weight = 1;
    }

    public LM(String name) {
        this.name = name;
        this.weight = 1;
    }
    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public void modifyWeight(int value) {
        weight += value;
    }
}
