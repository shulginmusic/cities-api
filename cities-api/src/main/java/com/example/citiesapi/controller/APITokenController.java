package com.example.citiesapi.controller;

import com.example.citiesapi.model.APIToken;
import com.example.citiesapi.model.APIResponse;
import com.example.citiesapi.service.APITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APITokenController {

    @Autowired
    APITokenService apiTokenService;

    @GetMapping("/api/new-token")
    public APIResponse<APIToken> newToken() {
        APIResponse<APIToken> apiResponse = new APIResponse<>();
        try {
            //Create new token
            APIToken apiToken = new APIToken();
            apiTokenService.create(apiToken);

            //Set valid and set hits initially at 0
            apiToken.setValid(true);
            apiToken.setHits(0);

            //Update api token
            apiTokenService.update(apiToken);

            //Set API Response
            apiResponse.setData(apiTokenService.findById(apiToken.getId()));

        } catch (Exception e) {
            apiResponse.setError(e.getMessage());
        }
        return apiResponse;
        //TODO limit number of api keys generated per minute
        //per incoming device??
    }
}
