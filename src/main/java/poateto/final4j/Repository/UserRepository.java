package poateto.final4j.Repository;


import poateto.final4j.Entity.User;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface UserRepository {
    User saveUser(User user) throws ExecutionException, InterruptedException;
    User getUser()throws ExecutionException, InterruptedException;
    User getUserByEmail(String account) throws ExecutionException, InterruptedException;
    String notifyModel(String email, String model, double value) throws ExecutionException, InterruptedException;
    String sendMessage(String email, String msg) throws ExecutionException, InterruptedException;
    String responseMessage(String email, String msg) throws ExecutionException, InterruptedException;

}
