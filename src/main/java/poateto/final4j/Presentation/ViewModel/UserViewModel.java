package poateto.final4j.Presentation.ViewModel;

import org.springframework.web.bind.annotation.*;
import poateto.final4j.UseCases.NotifyStatus;
import poateto.final4j.Entity.User;
import poateto.final4j.UseCases.UserService;
import poateto.final4j.UseCases.UserUseCase;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/user")
public class UserViewModel {
    private UserUseCase service;

    public UserViewModel() {
        this.service = new UserService();
    }

    @PostMapping
    public User saveUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return service.saveUser(user);
    }

    @PostMapping("/{email}")
    public String sendMessage(@PathVariable String email,
                              @RequestParam String msg) throws ExecutionException, InterruptedException {
        return service.sendMessage(email, msg);
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) throws ExecutionException, InterruptedException {
        return service.getUserByEmail(email);
    }

    @PutMapping("/{email}")
    public String increaseWeight(@PathVariable String email,
                                 @RequestParam String model,
                                 @RequestParam NotifyStatus status) throws ExecutionException, InterruptedException {
        return service.notifyModel(email, model, status);
    }
}