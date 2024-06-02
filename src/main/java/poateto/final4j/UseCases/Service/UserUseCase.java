package poateto.final4j.UseCases.Service;

import poateto.final4j.Entity.LMMessage;
import poateto.final4j.Entity.User;
import poateto.final4j.Entity.UserMessage;
import poateto.final4j.Entity.UserModifyWeight;
import poateto.final4j.UseCases.Components.NotifyStatus;

import java.util.concurrent.ExecutionException;

public interface UserUseCase {
    User saveUser(User user) throws ExecutionException, InterruptedException;
    User getUserByEmail(String email) throws ExecutionException, InterruptedException;
    String notifyModel(UserModifyWeight myModify) throws ExecutionException, InterruptedException;
    LMMessage sendMessage(UserMessage userMessage) throws ExecutionException, InterruptedException;
}
