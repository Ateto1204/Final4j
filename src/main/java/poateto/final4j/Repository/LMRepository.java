package poateto.final4j.Repository;

import poateto.final4j.Entity.LM;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface LMRepository {

    String saveLM(LM lm) throws ExecutionException, InterruptedException;
    List<LM> getAllLMs() throws ExecutionException, InterruptedException;
    String modifyLMWeightByName(LM lm, int value) throws ExecutionException, InterruptedException;
}
