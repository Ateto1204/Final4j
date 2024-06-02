package poateto.final4j.UseCases.Service;

import poateto.final4j.Entity.*;
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
    public User saveUser(UserCreate user) throws ExecutionException, InterruptedException {
        User myUser = new User(user);
        return repository.saveUser(myUser);
    }

    @Override
    public User getUserByEmail(String email) throws ExecutionException, InterruptedException {
        return repository.getUserByEmail(email);
    }

    @Override
    public String notifyModel(UserModifyWeight myModify) throws ExecutionException, InterruptedException {
        if (!checkPwd(myModify.getEmail(), myModify.getPassword())) {
            return null;
        }
        if (myModify.getStatus() == INCREASE) {
            return repository.notifyModel(myModify.getEmail(), myModify.getModel(), 0.3);
        }
        if (myModify.getStatus() == DECREASE) {
            return repository.notifyModel(myModify.getEmail(), myModify.getModel(), -1);
        }
        return repository.notifyModel(myModify.getEmail(), myModify.getModel(), 0);
    }

//    @Override
//    public String sendMessage(String email, String message) throws ExecutionException, InterruptedException {
//        repository.sendMessage(email, message);
//
//        LanguageModelType selectModel;
//        Map<String,Double>allModels = repository.getUser().getModels();
//        double sum = allModels.get("OPENAI") + allModels.get("COHERE") + allModels.get("GEMINI");
//        Random random = new Random();
//        double pick = sum * random.nextDouble();
//
//        if (pick < allModels.get("OPENAI")) {
//            selectModel = OPENAI;
//        } else if (pick < allModels.get("OPENAI") + allModels.get("COHERE")) {
//            selectModel = COHERE;
//        } else {
//            selectModel = GEMINI;
//        }
//
//        String response = handler.sendMessage(selectModel, message);
//        return response;
//    }

    @Override
    public LMMessage sendMessage(UserMessage prompt) throws ExecutionException, InterruptedException {
        if (!checkPwd(prompt.getEmail(), prompt.getPassword())) {
            return null;
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
        repository.responseMessage(prompt.getEmail(), response);
        LMMessage output = new LMMessage(selectModel.toString(), response);

        System.out.println(output.getModel() + " " + output.getMessage());
        return output;
    }

    @Override
    public boolean checkPwd(String email, String pwd) throws ExecutionException, InterruptedException {
        User user = repository.getUserByEmail(email);
        return user.checkPwd(pwd);
    }
}
