package poateto.final4j.UseCases;

import org.springframework.beans.factory.annotation.Autowired;
import poateto.final4j.Entity.LM;
import poateto.final4j.Repository.InMemoryLMRepository;
import poateto.final4j.Repository.LMRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class LMService {
    private LMRepository repository = new InMemoryLMRepository();

    public String saveLM(LM lm) throws ExecutionException, InterruptedException {
        return repository.saveLM(lm);
    }

    public List<LM> getAllLMs() throws ExecutionException, InterruptedException {
        return repository.getAllLMs();
    }

    public String updateLM(LM lm) throws ExecutionException, InterruptedException {
        // TODO
        int value = -1;
        return repository.modifyLMWeightByName(lm, value);
    }
}
