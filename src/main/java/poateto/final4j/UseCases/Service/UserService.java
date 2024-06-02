package poateto.final4j.UseCases.Service;

import static poateto.final4j.UseCases.Components.NotifyStatus.*;

import opennlp.tools.languagemodel.LanguageModel;
import poateto.final4j.Entity.LMMessage;
import poateto.final4j.Entity.User;
import poateto.final4j.Entity.UserMessage;
import poateto.final4j.Entity.UserModifyWeight;
import poateto.final4j.Repository.InMemoryUserRepository;
import poateto.final4j.Repository.UserRepository;
import poateto.final4j.UseCases.Components.LanguageModelType;
import poateto.final4j.UseCases.Components.NotifyStatus;
import poateto.final4j.UseCases.LanguageModel.LanguageModelHandler;
import java.util.Map;
import java.util.Random;

import java.util.concurrent.ExecutionException;

import static poateto.final4j.UseCases.Components.LanguageModelType.*;

public class UserService implements UserUseCase {
    private UserRepository repository = new InMemoryUserRepository();
    private LanguageModelHandler handler = new LanguageModelHandler();

    @Override
    public User saveUser(User user) throws ExecutionException, InterruptedException {
        return repository.saveUser(user);
    }

    @Override
    public User getUserByEmail(String email) throws ExecutionException, InterruptedException {
        return repository.getUserByEmail(email);
    }

    @Override
    public String notifyModel(UserModifyWeight myModify) throws ExecutionException, InterruptedException {
        if (myModify.getStatus() == INCREASE) {
            return repository.notifyModel(myModify.getEmail(), myModify.getModel(), 0.3);
        }
        if (myModify.getStatus() == DECREASE) {
            return repository.notifyModel(myModify.getEmail(), myModify.getModel(), -1);
        }
        return repository.notifyModel(myModify.getEmail(), myModify.getModel(), 0);
    }

    @Override
    public String sendMessage(String email, String message) throws ExecutionException, InterruptedException {
        repository.sendMessage(email, message);

        LanguageModelType selectModel;
        Map<String,Double>allModels = repository.getUser().getModels();
        double weightOfOpenai = allModels.get("OPENAI");
        double weightOfCohere = allModels.get("COHERE");
        double weightOfGemini = allModels.get("GEMINI");
        double mn=999;

        if(weightOfGemini < mn) mn = weightOfGemini;
        if(weightOfCohere < mn) mn = weightOfCohere;
        if(weightOfOpenai < mn) mn = weightOfOpenai;

        if(mn<0.0){
            mn =- mn;
            weightOfGemini += mn;
            weightOfOpenai += mn;
            weightOfCohere += mn;
            repository.notifyModel(email,"OPENAI",mn);
            repository.notifyModel(email,"COHERE",mn);
            repository.notifyModel(email,"GEMINI",mn);
        }

        double sum = weightOfOpenai + weightOfCohere + weightOfGemini;
        Random random = new Random();
        double pick = sum * random.nextDouble();
        if (pick < weightOfOpenai) {
            selectModel = OPENAI;
        } else if(pick < weightOfOpenai + weightOfCohere){
            selectModel = COHERE;
        }else selectModel = GEMINI;

        String response = handler.sendMessage(selectModel, message);
        return response;
    }

    @Override
    public LMMessage sendMessage(UserMessage prompt) throws ExecutionException, InterruptedException {
        repository.sendMessage(prompt.getEmail(), prompt.getMessage());

        LanguageModelType selectModel;
        selectModel = OPENAI;

        String response = handler.sendMessage(selectModel, prompt.getMessage());
        repository.responseMessage(prompt.getEmail(), response);
        LMMessage output = new LMMessage(selectModel, response);

        return output;
    }
}
