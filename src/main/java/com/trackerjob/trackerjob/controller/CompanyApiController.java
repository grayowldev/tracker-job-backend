package com.trackerjob.trackerjob.controller;

import com.google.cloud.firestore.Firestore;
import com.trackerjob.trackerjob.entity.CompanyEntity;
import com.trackerjob.trackerjob.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

@CrossOrigin
@RestController
@RequestMapping("/company")
public class CompanyApiController {

    @Autowired
    FirebaseService firebaseService;

    public Collection<CompanyEntity> getAllCompanies() throws ExecutionException, InterruptedException {
        return firebaseService.getAllCompanies();
    }

    public CompanyEntity getCompanyById(@PathVariable String id) throws ExecutionException, InterruptedException {
        return firebaseService.getCompanyById(id);
    }

    public String addCompany(@RequestBody CompanyEntity companyEntity){
        return firebaseService.addCompany(companyEntity);
    }

    public String deleteCompany(@PathVariable String id) throws ExecutionException, InterruptedException {
        return firebaseService.deleteCompany(id);
    }


}
