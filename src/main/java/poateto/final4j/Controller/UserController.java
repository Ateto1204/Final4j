package poateto.final4j.Controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import poateto.final4j.Entity.*;
import poateto.final4j.UseCases.Components.NotifyStatus;
import poateto.final4j.UseCases.Service.UserService;
import poateto.final4j.UseCases.Service.UserUseCase;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserUseCase service;

    public UserController() {
        this.service = new UserService();
    }

    @PostMapping // request body: email, name, pwd
    public User saveUser(@RequestBody UserCreate user) throws ExecutionException, InterruptedException {
        return service.saveUser(user);
    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) throws ExecutionException, InterruptedException {
        return service.getUserByEmail(email);
    }

    // request body: email, pwd, prompt; return: response, model
    @PostMapping("/send")
    public LMMessage sendMessage(@RequestBody UserMessage send) throws ExecutionException, InterruptedException {
        return service.sendMessage(send);
    }

    @PostMapping("/modify") // request body: email, pwd, model, status
    public String modifyWeight(@RequestBody UserModifyWeight modify) throws ExecutionException, InterruptedException {
        return service.notifyModel(modify);
    }
}