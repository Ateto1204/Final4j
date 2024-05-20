package poateto.final4j.DB;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import poateto.final4j.Entity.LM;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class LMDBController {
    private static final String COLLECTION_NAME = "lms";

    public String saveLM(LM lm) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(lm.getName()).set(lm);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public List<LM> getAllLMs() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        Iterable<DocumentReference> documentReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator = documentReference.iterator();

        List<LM> LMList = new ArrayList<>();
        LM lm;
        while (iterator.hasNext()) {
            DocumentReference reference = iterator.next();
            ApiFuture<DocumentSnapshot> future = reference.get();
            DocumentSnapshot document = future.get();
            lm = document.toObject(LM.class);
            LMList.add(lm);
        }
        return LMList;
    }

    public String updateLM(LM lm) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(lm.getName()).set(lm);
        return collectionApiFuture.get().getUpdateTime().toString();
    }
}
