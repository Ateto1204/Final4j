package poateto.final4j.Repository;

import poateto.final4j.DB.UserDatabase;
import poateto.final4j.Entity.User;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class InMemoryUserRepository implements UserRepository {
    private User user;
    private UserDatabase db = new UserDatabase();

    @Override
    public String saveUser(User user) throws ExecutionException, InterruptedException {
        String msg = db.saveUser(user);
        getUserByEmail(user.getEmail());
        return msg;
    }
    @Override
    public User getUserByEmail(String email) throws ExecutionException, InterruptedException {
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
}
