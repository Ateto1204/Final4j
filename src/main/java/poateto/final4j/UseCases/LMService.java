package poateto.final4j.UseCases;

import poateto.final4j.Entity.LM;
import poateto.final4j.Repository.InMemoryLMRepository;
import poateto.final4j.Repository.LMRepository;

import java.util.List;

// Use Case of select the language model
public class LMService implements LMUseCase {
    private LMRepository repository = new InMemoryLMRepository();

    public String saveLM(LM lm) {
        String msg = repository.saveLM(lm);
        return msg;
    }

    public List<LM> getAllLMs() {
        return repository.getAllLMs();
    }

    public String updateLM(LM lm) {
        // TODO
        int value = -1;
        String msg = repository.modifyLMWeightByName(lm, value);
        return msg;
    }

    public LM selectLMByWeight() {
        // TODO
        LM selectLM = new LM();
        return selectLM;
    }
}
