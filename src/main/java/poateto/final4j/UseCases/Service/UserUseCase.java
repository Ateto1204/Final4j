package poateto.final4j.UseCases.Service;

import poateto.final4j.Entity.User;
import poateto.final4j.UseCases.Components.NotifyStatus;

import java.util.concurrent.ExecutionException;

public interface UserUseCase {
    User saveUser(User user) throws ExecutionException, InterruptedException;
    User getUserByEmail(String email) throws ExecutionException, InterruptedException;
    String notifyModel(String email, String model, NotifyStatus status) throws ExecutionException, InterruptedException;
    String sendMessage(String email, String message) throws ExecutionException, InterruptedException;
}
