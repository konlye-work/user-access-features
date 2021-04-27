package com.features.api.controller;

import com.features.api.persistence.FeaturesRepository;
import com.features.api.persistence.entity.Features;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feature")
public class FeaturesController {

    @Autowired
    private FeaturesRepository repository;

    private Gson gson = new Gson();


    @GetMapping
    public ResponseEntity<String> doGet(@PathVariable String email, @PathVariable String featureName) {
        Features feature = repository.findByEmailAndFeatureName(email, featureName);
        return ResponseEntity.ok().body(gson.toJson(feature.getEnable()));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<ResponseModel> doGet(@PathVariable int id) throws PostException{
//        return ResponseEntity.ok().body(execute(null, REST_URL+"posts/"+id, HttpMethod.GET));
//    }
//
//    @PostMapping
//    public ResponseEntity<ResponseModel> doPost(@RequestBody PostModel body) throws PostException{
//        return ResponseEntity.ok().body(execute(gson.toJson(body), REST_URL+"posts", HttpMethod.POST));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ResponseModel> doPut(@PathVariable int id, @RequestBody PostModel body) throws PostException{
//        return ResponseEntity.ok().body(execute(gson.toJson(body), REST_URL+"posts/"+id, HttpMethod.PUT));
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<ResponseModel> doPatch(@PathVariable int id, @RequestBody PostModel body) throws PostException{
//        return ResponseEntity.ok().body(execute(gson.toJson(body), REST_URL+"posts/"+id, HttpMethod.PATCH));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ResponseModel> doDelete(@PathVariable int id) throws PostException{
//        return ResponseEntity.ok().body(execute(null, REST_URL+"posts/"+id, HttpMethod.DELETE));
//    }
//
//    public ResponseModel execute(String jsonRequest, String url, HttpMethod httpMethod) throws PostException{
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        if (jsonRequest != null){
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//            entity = new HttpEntity<>(jsonRequest, headers);
//        }
//
//        ResponseModel model = new ResponseModel();
//
//        try {
//            ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, entity, String.class);
//            String json = response.getBody();
//            JsonElement e = JsonParser.parseString(json);
//
//            if (e.isJsonArray()){
//                model.setData(gson.fromJson(json, new TypeToken<List<PostModel>>() {}.getType()));
//            }else{
//                model.setData(gson.fromJson(json, PostModel.class));
//            }
//
//            model.setStatusCode(STATUS.OK.name());
//            model.setMessage("Success");
//            return model;
//        } catch (Exception e){
//            HttpStatus errorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//            String errorMessage = e.getMessage();
//
//            if (e instanceof HttpStatusCodeException){
//                HttpStatusCodeException httpException = (HttpStatusCodeException)e;
//                errorStatus = httpException.getStatusCode();
//                errorMessage = httpException.getResponseBodyAsString();
//
//                if (httpException.getStatusCode() == HttpStatus.NOT_FOUND && errorMessage.equals("{}")){
//                    errorMessage = "No post found";
//                }
//            }
//            PostException exception = new PostException();
//            exception.setErrorCode(errorStatus);
//            model.setData(null);
//            model.setStatusCode(STATUS.ERROR.name());
//            model.setMessage(errorMessage);
//            exception.setResponseModel(model);
//            throw exception;
//        }
//    }
//
//    @ExceptionHandler({ PostException.class })
//    public ResponseEntity<ResponseModel> handleException(PostException e){
//        return ResponseEntity.status(e.getErrorCode()).body(e.getResponseModel());
//    }

}
