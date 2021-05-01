package com.features.api.controller;

import com.feature.api.model.FeatureModel;
import com.features.api.exception.FeaturesException;
import com.features.api.persistence.FeaturesRepository;
import com.features.api.persistence.entity.Features;
import com.features.api.utils.Validator;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feature")
public class FeaturesController {

    private final FeaturesRepository repository;

    private Gson gson = new Gson();

    @Autowired
    public FeaturesController(FeaturesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<String> doGet(@RequestParam String email, @RequestParam String featureName) {
        validateEmail(email);
        Features feature = getFeatureByEmailandFeatureName(email, featureName, HttpStatus.NO_CONTENT);
        JsonObject obj = new JsonObject();
        obj.addProperty("canAccess", feature.getEnable());
        return ResponseEntity.ok().body(gson.toJson(obj));
    }


    @PostMapping
    public ResponseEntity<String> doPost(@RequestBody FeatureModel body){
        String featureName = body.getFeatureName();
        String email = body.getEmail();
        boolean enable = body.isEnable();
        validateEmail(email);
        Features feature = getFeatureByEmailandFeatureName(email, featureName, HttpStatus.NOT_MODIFIED);
        feature.setEnable(enable);
        repository.save(feature);
        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    private Features getFeatureByEmailandFeatureName(String email, String featureName, HttpStatus errorStatus){
        Features feature = repository.findByEmailAndFeatureName(email, featureName);

        if (feature == null){
            throw new FeaturesException(errorStatus, "No record found");
        }

        return feature;
    }

    @SneakyThrows
    private void validateEmail(String email){
        if (!Validator.validEmail(email)){
            throw new FeaturesException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid email format");
        }
    }


    @ExceptionHandler({ FeaturesException.class })
    public ResponseEntity<String> handleException(FeaturesException e){
        JsonObject obj = new JsonObject();
        obj.addProperty("error", e.getMessage());
        return ResponseEntity.status(e.getErrorCode()).body(gson.toJson(obj));
    }

}
