package poateto.final4j.UseCases.Service;

import poateto.final4j.Entity.*;
import poateto.final4j.UseCases.Components.NotifyStatus;

import java.util.concurrent.ExecutionException;

public interface UserUseCase {
    User saveUser(UserCreate user) throws ExecutionException, InterruptedException;
    User getUserByEmail(String email) throws ExecutionException, InterruptedException;
    String notifyModel(UserModifyWeight myModify) throws ExecutionException, InterruptedException;
    LMMessage sendMessage(UserMessage userMessage) throws ExecutionException, InterruptedException;
    boolean checkPwd(String email, String pwd) throws ExecutionException, InterruptedException;
}
