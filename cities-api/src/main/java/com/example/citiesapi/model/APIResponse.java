package com.example.citiesapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponse<T> {
    T data;
    String error;
}
