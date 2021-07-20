package com.example.citiesapi.controller;

import com.example.citiesapi.model.APIResponse;
import com.example.citiesapi.model.APIToken;
import com.example.citiesapi.model.City;
import com.example.citiesapi.service.APITokenService;
import com.example.citiesapi.service.CityService;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    //Rate limiter for throttling
    @Autowired
    RateLimiter rateLimiter;

    @Autowired
    CityService cityService;

    @Autowired
    APITokenService apiTokenService;

    @GetMapping("/api")
    public APIResponse<List<City>> allCities(@RequestParam String apiToken) {
        APIResponse<List<City>> apiResponse = new APIResponse<>();
        //Throttling using guava
//        boolean too_much = rateLimiter.tryAcquire();
//        if (!too_much) {
//            apiResponse.setError("Too many requests, please wait and try again later.");
//            return apiResponse;
//        }


        try {
            //Manual validation of api token

            //Return error if key doesn't exist
            if (apiTokenService.findByToken(apiToken) == null) {
                apiResponse.setError("Token " + apiToken + " doesn't exist! Please get a token at /api/new-token");
                return apiResponse;
            }

            //Return error if key is invalid
            if (!apiTokenService.findByToken(apiToken).getValid()) {
                apiResponse.setError("Token " + apiToken + " is invalid!");
                return apiResponse;
            }

            //Return error if more than 5 hits per minute (see Main method)
            if (apiTokenService.findByToken(apiToken).getHits() >= 5) {
                apiResponse.setError("Too many requests, please try again later");
                return apiResponse;
            }

            //If token exists and is valid, then proceed to show the data
            APIToken token = apiTokenService.findByToken(apiToken);
            apiResponse.setData(cityService.findAll());

            //Increase hits
            token.incrementHits();
            apiTokenService.update(token);

        } catch (Exception e) {
            apiResponse.setError(e.getMessage());
        }
        return apiResponse;
    }
}















