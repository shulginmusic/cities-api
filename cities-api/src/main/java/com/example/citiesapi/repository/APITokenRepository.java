package com.example.citiesapi.repository;

import com.example.citiesapi.model.APIToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface APITokenRepository extends CrudRepository<APIToken, Long> {

    List<APIToken> findAll();

    APIToken findByToken(String apiToken);

    APIToken findById(long id);
}
