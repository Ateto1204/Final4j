package poateto.final4j.Presentation.ViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poateto.final4j.Entity.NotifyStatus;
import poateto.final4j.Entity.User;
import poateto.final4j.UseCases.UserService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class UserViewModel {
    private UserService service = new UserService();

    @PostMapping("/user")
    public String saveUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return service.saveUser(user);
    }

    @GetMapping("/user/{email}")
    public User getUserByEmail(@PathVariable String email) throws ExecutionException, InterruptedException {
        return service.getUserByEmail(email);
    }

    @PutMapping("/user/{email}/{model}/{status}")
    public String increaseWeight(@PathVariable String email, @PathVariable String model, @PathVariable NotifyStatus status) throws ExecutionException, InterruptedException {
        return service.notifyModel(email, model, status);
    }
}