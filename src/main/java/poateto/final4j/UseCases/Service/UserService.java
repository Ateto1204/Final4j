package poateto.final4j.UseCases.Service;

import poateto.final4j.Entity.Message.LMMessage;
import poateto.final4j.Entity.User.*;
import poateto.final4j.Repository.InMemoryUserRepository;
import poateto.final4j.Repository.UserRepository;
import poateto.final4j.UseCases.Components.LanguageModelType;
import poateto.final4j.UseCases.LanguageModel.LanguageModelHandler;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static poateto.final4j.UseCases.Components.LanguageModelType.*;
import static poateto.final4j.UseCases.Components.NotifyStatus.*;

public class UserService implements UserUseCase {
    private UserRepository repository = new InMemoryUserRepository();
    private LanguageModelHandler handler = new LanguageModelHandler();

    @Override
    public UserStorage saveUser(UserCreate user) throws ExecutionException, InterruptedException {
        UserStorage myUser = new UserStorage(user);
        return repository.saveUser(myUser);
    }

    @Override
    public UserStorage getUserByEmail(UserLogin user) throws ExecutionException, InterruptedException {
        if (!checkPwd(user.getEmail(), user.getPwd())) {
            UserStorage error = new UserStorage();
            return error;
        }
        return repository.getUserByEmail(user.getEmail());
    }

//    public User getUserByEmail(String email) throws ExecutionException, InterruptedException {
//        return repository.getUserByEmail(email);
//    }

    @Override
    public String notifyModel(UserModifyWeight myModify) throws ExecutionException, InterruptedException {
        if (!checkPwd(myModify.getEmail(), myModify.getPwd())) {
            return null;
        }
        if (myModify.getStatus() == INCREASE) {
            return repository.notifyModel(myModify.getEmail(), myModify.getModel(), 0.3);
        }
        if (myModify.getStatus() == DECREASE) {
            if (repository.getUserByEmail(myModify.getEmail()).getModels().get(myModify.getModel()) <= 0) {
                return offsetModels(myModify);
            }
            return repository.notifyModel(myModify.getEmail(), myModify.getModel(), -1);
        }
        return null;
    }

    public String offsetModels(UserModifyWeight modify) throws ExecutionException, InterruptedException {
        Map<String, Double> models = repository.getUserByEmail(modify.getEmail()).getModels();
        String msg = null;
        for (String model : models.keySet()) {
            msg = repository.notifyModel(modify.getEmail(), model, 1.0);
        }
        return msg;
    }

    @Override
    public LMMessage sendMessage(UserMessage prompt) throws ExecutionException, InterruptedException {
        if (!checkPwd(prompt.getEmail(), prompt.getPwd())) {
            LMMessage error = new LMMessage();
            return error;
        }
        repository.sendMessage(prompt.getEmail(), prompt.getMessage());

        LanguageModelType selectModel;
        Map<String, Double> allModels = repository.getUserByEmail(prompt.getEmail()).getModels();

        double sum = 0.0;
        for (Double weight : allModels.values()) {
            sum += weight;
        }
        double pick = new SecureRandom().nextDouble(sum);

        if (pick < allModels.get(OPENAI.name())) {
            selectModel = OPENAI;
        } else if (pick < allModels.get(OPENAI.name()) + allModels.get(COHERE.name())) {
            selectModel = COHERE;
        } else {
            selectModel = GEMINI;
        }

        String response = handler.sendMessage(selectModel, prompt.getMessage());
        LMMessage responseMsg = new LMMessage(selectModel.name(), response);
        repository.responseMessage(prompt.getEmail(), responseMsg);
        LMMessage output = new LMMessage(selectModel.toString(), response);

        System.out.println(output.getModel() + " " + output.getMessage());
        return output;
    }

    @Override
    public Boolean checkPwd(String email, String pwd) throws ExecutionException, InterruptedException {
        UserStorage user = repository.getUserByEmail(email);
        return user.checkPwd(pwd);
    }

    @Override
    public Boolean isUserExisted(String email) throws ExecutionException, InterruptedException {
        UserStorage user = repository.getUserByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}
