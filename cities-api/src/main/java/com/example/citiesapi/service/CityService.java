package com.example.citiesapi.service;

import com.example.citiesapi.model.City;
import com.example.citiesapi.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findById(long id) {
        return cityRepository.findById(id);
    }

    public City findByName(String name) {
        return cityRepository.findByName(name);
    }

    public List<City> findByState(String state) {
        return cityRepository.findByState(state);
    }

    public List<City> findByYearFounded(int yearFounded) {
        return cityRepository.findByYearFounded(yearFounded);
    }

    public void create(City city) {
        cityRepository.save(city);
    }
}
