package poateto.final4j.Repository;

import poateto.final4j.DB.UserDatabase;
import poateto.final4j.Entity.LMMessage;
import poateto.final4j.Entity.UserStorage;
import poateto.final4j.Entity.UserStorage;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class InMemoryUserRepository implements UserRepository {
    private UserStorage user;
    private UserDatabase db = new UserDatabase();

    @Override
    public UserStorage saveUser(UserStorage user) throws ExecutionException, InterruptedException {
        String msg = db.saveUser(user);
        return getUserByEmail(user.getEmail());
    }
    @Override
    public UserStorage getUserByEmail(String email) throws ExecutionException, InterruptedException {
        user = db.getUserByEmail(email);
        return user;
    }
    @Override
    public String notifyModel(String email, String model, double value) throws ExecutionException, InterruptedException {
        user = db.getUserByEmail(email);
        user.notifyModel(model, value);
        String msg = db.notifyModel(user);
        return msg;
    }

    @Override
    public String sendMessage(String email, String message) throws ExecutionException, InterruptedException {
        user = db.getUserByEmail(email);
        user.sentMsg(message);
        String msg = db.sendMessage(user);
        return msg;
    }

    @Override
    public String responseMessage(String email, LMMessage message) throws ExecutionException, InterruptedException {
        user = db.getUserByEmail(email);
        user.responsedMsg(message);
        String msg = db.sendMessage(user);
        return msg;
    }
}
