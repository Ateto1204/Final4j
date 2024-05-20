package poateto.final4j.Presentation.ViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poateto.final4j.DB.LMDBController;
import poateto.final4j.Entity.LM;
import poateto.final4j.Repository.LMRepository;
import poateto.final4j.UseCases.LMService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class LMViewModel {
    private LMService service = new LMService();

    @PostMapping("/lms")
    public String saveLM(@RequestBody LM lm) throws ExecutionException, InterruptedException {
        return service.saveLM(lm);
    }

    @GetMapping("/lms")
    public List<LM> getAllLMs() throws ExecutionException, InterruptedException {
        return service.getAllLMs();
    }

    @PutMapping("/lms")
    public String updateLM(@RequestBody LM lm) throws ExecutionException, InterruptedException {
        return service.updateLM(lm);
    }
}
