package com.example.citiesapi.service;

import com.example.citiesapi.model.APIToken;
import com.example.citiesapi.repository.APITokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class APITokenService {

    @Autowired
    APITokenRepository apiTokenRepository;

    public List<APIToken> findAll() {
        return apiTokenRepository.findAll();
    }

    public APIToken findByToken(String apiToken) {
        return apiTokenRepository.findByToken(apiToken);
    }

    public APIToken findById(long id) {
        return apiTokenRepository.findById(id);
    }

    public void create(APIToken apiToken) {
        apiTokenRepository.save(apiToken);
    }

    public void update(APIToken apiToken) {
        apiTokenRepository.save(apiToken);
    }
}
