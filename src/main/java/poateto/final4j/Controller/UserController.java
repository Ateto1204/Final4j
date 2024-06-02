package poateto.final4j.Controller;

import org.springframework.web.bind.annotation.*;
import poateto.final4j.Entity.LMMessage;
import poateto.final4j.Entity.UserMessage;
import poateto.final4j.Entity.UserModifyWeight;
import poateto.final4j.UseCases.Components.NotifyStatus;
import poateto.final4j.Entity.User;
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

    @PostMapping
    public User saveUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return service.saveUser(user);
    }

//    @GetMapping("/send/{email}") // request body: email, pwd, prompt; return: response, model
//    public String sendMessage(@PathVariable String email,
//                              @RequestParam String msg) throws ExecutionException, InterruptedException {
//        return service.sendMessage(email, msg);
//    }

    @GetMapping("/{email}")
    public User getUserByEmail(@PathVariable String email) throws ExecutionException, InterruptedException {
        return service.getUserByEmail(email);
    }

//    @PutMapping("/{email}") // request body: email, pwd, model, status
//    public String increaseWeight(@PathVariable String email,
//                                 @RequestParam String model,
//                                 @RequestParam NotifyStatus status) throws ExecutionException, InterruptedException {
//        return service.notifyModel(email, model, status);
//    }

    @GetMapping("/send") // request body: email, pwd, prompt; return: response, model
    public LMMessage sendMessage(UserMessage userMessage) throws ExecutionException, InterruptedException {
        return service.sendMessage(userMessage);
    }

    @PutMapping("/modify") // request body: email, pwd, model, status
    public String modifyWeight(@RequestBody UserModifyWeight myModify) throws ExecutionException, InterruptedException {
        return service.notifyModel(myModify);
    }
}