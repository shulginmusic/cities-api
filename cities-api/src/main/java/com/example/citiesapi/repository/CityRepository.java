package com.example.citiesapi.repository;

import com.example.citiesapi.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

    List<City> findAll();

    City findById(long id);

    City findByName(String name);

    List<City> findByState(String state);

    List<City> findByYearFounded(int yearFounded);
}
