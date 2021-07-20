package com.example.citiesapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor

@Entity
public class APIToken {

    @Id
    @GeneratedValue
    private Long id;

    private String token;
    private Boolean valid;
    private Integer hits;

    public APIToken() {
        this.token = UUID.randomUUID().toString();;
    }

    public void incrementHits() {
        hits++;
    }
}
