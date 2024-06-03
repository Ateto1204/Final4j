package poateto.final4j.Repository;


import poateto.final4j.Entity.UserStorage;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface UserRepository {
    UserStorage saveUser(UserStorage user) throws ExecutionException, InterruptedException;
    UserStorage getUserByEmail(String account) throws ExecutionException, InterruptedException;
    String notifyModel(String email, String model, double value) throws ExecutionException, InterruptedException;
    String sendMessage(String email, String msg) throws ExecutionException, InterruptedException;
    String responseMessage(String email, String msg) throws ExecutionException, InterruptedException;

}
