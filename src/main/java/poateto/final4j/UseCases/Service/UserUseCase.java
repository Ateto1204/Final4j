package poateto.final4j.UseCases.Service;

import poateto.final4j.Entity.*;
import poateto.final4j.UseCases.Components.NotifyStatus;

import java.util.concurrent.ExecutionException;

public interface UserUseCase {
    UserStorage saveUser(UserCreate user) throws ExecutionException, InterruptedException;
    UserStorage getUserByEmail(UserLogin user) throws ExecutionException, InterruptedException;
    String notifyModel(UserModifyWeight myModify) throws ExecutionException, InterruptedException;
    LMMessage sendMessage(UserMessage userMessage) throws ExecutionException, InterruptedException;
    Boolean checkPwd(String email, String pwd) throws ExecutionException, InterruptedException;
    Boolean isUserExisted(String email) throws ExecutionException, InterruptedException;
}
