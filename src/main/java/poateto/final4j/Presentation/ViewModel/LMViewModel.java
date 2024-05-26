//package poateto.final4j.Presentation.ViewModel;
//
//import org.springframework.web.bind.annotation.*;
//import poateto.final4j.Entity.LM;
//import poateto.final4j.UseCases.LMService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class LMViewModel {
//    private LMService service = new LMService();
//
//    @PostMapping("/lms")
//    public String saveLM(@RequestBody LM lm) {
//        return service.saveLM(lm);
//    }
//
//    @GetMapping("/lms")
//    public List<LM> getAllLMs() {
//        return service.getAllLMs();
//    }
//
//    @PutMapping("/lms")
//    public String updateLM(@RequestBody LM lm) {
//        return service.updateLM(lm);
//    }
//}
