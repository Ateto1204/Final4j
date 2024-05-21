package poateto.final4j.Repository;

import poateto.final4j.Entity.LM;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface LMRepository {

    String saveLM(LM lm);
    List<LM> getAllLMs();
    String modifyLMWeightByName(LM lm, int value);
}
