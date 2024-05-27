package poateto.final4j.UseCases;

import static poateto.final4j.UseCases.NotifyStatus.*;

import poateto.final4j.Entity.User;
import poateto.final4j.Repository.InMemoryUserRepository;
import poateto.final4j.Repository.UserRepository;

import java.util.concurrent.ExecutionException;

public class UserService implements UserUseCase {
    private UserRepository repository = new InMemoryUserRepository();

    @Override
    public String saveUser(User user) throws ExecutionException, InterruptedException {
        return repository.saveUser(user);
    }

    @Override
    public User getUserByEmail(String email) throws ExecutionException, InterruptedException {
        return repository.getUserByEmail(email);
    }

    @Override
    public String notifyModel(String email, String model, NotifyStatus status) throws ExecutionException, InterruptedException {
        if (status == INCREASE) {
            return repository.notifyModel(email, model, 0.3);
        }
        if (status == DECREASE) {
            return repository.notifyModel(email, model, -1);
        }
        return repository.notifyModel(email, model, 0);
    }

    @Override
    public String sendMessage(String email, String message) throws ExecutionException, InterruptedException {
        repository.sendMessage(email, message);
        String response = "demo response";
        return response;
    }
}
