package poateto.final4j.DB;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import poateto.final4j.Entity.User;

import java.util.concurrent.ExecutionException;

@Service
public class UserDatabase {
    private static final String COLLECTION_NAME = "user";
    private static final String FIELD_NAME = "models";

    public String saveUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.getEmail()).set(user);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public User getUserByEmail(String email) throws ExecutionException, InterruptedException  {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(email);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        User user;
        if (document.exists()) {
            user = document.toObject(User.class);
        } else {
            return null;
        }
        return user;
    }

    public String notifyModel(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.getEmail()).set(user);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String sendMessage(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.getEmail()).set(user);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public String responseMessage(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.getEmail()).set(user);
        return collectionApiFuture.get().getUpdateTime().toString();
    }
}
