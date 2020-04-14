package com.trackerjob.trackerjob.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.trackerjob.trackerjob.entity.CompanyEntity;
import com.trackerjob.trackerjob.entity.JobAppEntity;
import com.trackerjob.trackerjob.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {

    public String saveUserDetails(UserEntity userEntity) throws ExecutionException, InterruptedException {
        Firestore firestoreDB = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture =firestoreDB.collection("users").document(userEntity.getName()).set(userEntity);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }



    public JobAppEntity getJobAppById(String id) throws ExecutionException, InterruptedException {
        Firestore firestoreDB = FirestoreClient.getFirestore();
        DocumentReference reference = firestoreDB.collection("jobApp").document(id);
        ApiFuture<DocumentSnapshot> future = reference.get();
        DocumentSnapshot snapshot = future.get();
        if (snapshot.exists()){
            return snapshot.toObject(JobAppEntity.class);
        }
        return null;
    }

    public Collection<JobAppEntity> getAllJobApp() throws ExecutionException, InterruptedException {
        Firestore firestoreDB = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = firestoreDB.collection("jobApp").get();

        List<QueryDocumentSnapshot> snapshots = future.get().getDocuments();
        List<JobAppEntity> returnList = new ArrayList<>();
        for (DocumentSnapshot snapshot: snapshots ){
            System.out.println(snapshot.getId() + " => " + snapshot.toObject(JobAppEntity.class));
            returnList.add(snapshot.toObject(JobAppEntity.class));
        }
        return returnList;
    }

    public String addJobApp(JobAppEntity jobAppEntity) throws ExecutionException, InterruptedException {
        jobAppEntity.setId(UUID.randomUUID().toString());
        Firestore firestoreDB = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture =
                firestoreDB.collection("jobApp").document(jobAppEntity.getId()).set(jobAppEntity);
        return "Application " + jobAppEntity.getId() + " was added at " + collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String updateJobApp(JobAppEntity jobAppEntity) throws ExecutionException, InterruptedException {
        Map<String,Object> updateMap;
        ObjectMapper mapper = new ObjectMapper();
        List<String> removeList = new ArrayList<>();

        updateMap = mapper.convertValue(jobAppEntity,Map.class);
        for(String s: updateMap.keySet()){
            if (updateMap.get(s) == null){
                removeList.add(s);
            }
        }
        for (String s: removeList){
            updateMap.remove(s);
        }

        Firestore firestoreDB = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture = firestoreDB.collection("jobApp").document(jobAppEntity.getId()).update(updateMap);
        return "Updated the application at " + apiFuture.get().getUpdateTime().toString();
    }

    public void deleteJobApp(){}

    /*
    * Company Collection Methods
    * */

    public String addCompany(CompanyEntity companyEntity){
        companyEntity.setId(UUID.randomUUID().toString());
        Firestore firestoreDB = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture =
                firestoreDB.collection("company").document(companyEntity.getId()).set(companyEntity);
        return "Company: " + companyEntity.getCompanyName() + " was added.";
    }

    public Collection<CompanyEntity> getAllCompanies() throws ExecutionException, InterruptedException {
        Firestore firestoreDB = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestoreDB.collection("company").get();
        List<QueryDocumentSnapshot> documentSnapshots = apiFuture.get().getDocuments();
        List<CompanyEntity> companyEntityList = new ArrayList<>();
        for (DocumentSnapshot snapshot : documentSnapshots ){
            companyEntityList.add(snapshot.toObject(CompanyEntity.class));
        }
        return companyEntityList;
    }

    public CompanyEntity getCompanyById(String id) throws ExecutionException, InterruptedException {
        Firestore firestoreDB = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestoreDB.collection("company").document(id);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        if (documentSnapshot.exists()){
            return documentSnapshot.toObject(CompanyEntity.class);
        }
        return null;
    }

    public String updateCompany(CompanyEntity companyEntity){
        Map<String,Object> updateMap;
        ObjectMapper mapper = new ObjectMapper();
        List<String> removeList = new ArrayList<>();

        updateMap = mapper.convertValue(companyEntity,Map.class);
        for(String s: updateMap.keySet()){
            if (updateMap.get(s) == null){
                removeList.add(s);
            }
        }
        for (String s: removeList){
            updateMap.remove(s);
        }

        Firestore firestoreDB = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture = firestoreDB.collection("company").document(companyEntity.getId()).update(updateMap);
        return "company updated";
    }

    public String deleteCompany(String id) throws ExecutionException, InterruptedException {
        DocumentReference documentReference = FirestoreClient.getFirestore().collection("company").document(id);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        if (documentSnapshot.exists()){
            documentSnapshot.getReference().delete();
        }
        return null;
    }


}
