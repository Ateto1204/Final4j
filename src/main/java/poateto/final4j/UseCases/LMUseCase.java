package poateto.final4j.UseCases;

import poateto.final4j.Entity.LM;

import java.util.List;

public interface LMUseCase {
    public String saveLM(LM lm);
    public List<LM> getAllLMs();
    public String updateLM(LM lm);
}
