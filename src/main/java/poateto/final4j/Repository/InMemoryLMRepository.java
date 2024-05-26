package poateto.final4j.Repository;

import poateto.final4j.DB.LMdatabase;
import poateto.final4j.Entity.LM;

import java.util.List;

public class InMemoryLMRepository implements LMRepository {
    private List<LM> LMs;
    private LMdatabase db = new LMdatabase();

    public InMemoryLMRepository() {
        loadLMs();
    }

    public void loadLMs() {
        try {
            LMs = db.getAllLMs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String saveLM(LM lm) {
        String msg;
        try {
            msg = db.saveLM(lm);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        loadLMs();
        return msg;
    }

    public List<LM> getAllLMs() {
        return LMs;
    }

    public String modifyLMWeightByName(LM lm, int value) {
        lm.modifyWeight(value);
        String msg;
        try {
            msg = db.updateLM(lm);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        loadLMs();
        return msg;
    }
}
