package poateto.final4j.UseCases;

import static poateto.final4j.Entity.NotifyStatus.*;

import poateto.final4j.Entity.NotifyStatus;
import poateto.final4j.Entity.User;
import poateto.final4j.Repository.InMemoryUserRepository;
import poateto.final4j.Repository.UserRepository;

import java.util.concurrent.ExecutionException;

public class UserService {
    private UserRepository repository = new InMemoryUserRepository();

    public String saveUser(User user) throws ExecutionException, InterruptedException {
        return repository.saveUser(user);
    }

    public User getUserByEmail(String email) throws ExecutionException, InterruptedException {
        return repository.getUserByEmail(email);
    }

    public String notifyModel(String email, String model, NotifyStatus status) throws ExecutionException, InterruptedException {
        if (status == INCREASE) {
            return repository.notifyModel(email, model, 0.3);
        }
        return repository.notifyModel(email, model, -1);
    }
}
