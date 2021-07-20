package com.example.citiesapi.configuration;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;

import java.time.Duration;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public RateLimiter rateLimiter() {
        return RateLimiter.create(0.1d, Duration.ofSeconds(30));
    }
}

//Reference: https://github.com/ekim197711/springboot-guava-ratelimiter
