package poateto.final4j.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import poateto.final4j.DB.LMDBController;
import poateto.final4j.Entity.LM;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class InMemoryLMRepository implements LMRepository {
    private LMDBController dbController = new LMDBController();

    public String saveLM(LM lm) throws ExecutionException, InterruptedException {
        return dbController.saveLM(lm);
    }

    public List<LM> getAllLMs() throws ExecutionException, InterruptedException {
        return dbController.getAllLMs();
    }

    public String modifyLMWeightByName(LM lm, int value) throws ExecutionException, InterruptedException {
        lm.modifyWeight(value);
        return dbController.updateLM(lm);
    }
}
