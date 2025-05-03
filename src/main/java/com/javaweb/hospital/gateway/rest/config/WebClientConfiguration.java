package com.javaweb.hospital.gateway.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Value("${internal.service.patient-management}")
    private String patientServiceUrl;

    private final WebClient patientServiceClient;

    public WebClientConfiguration(WebClient.Builder builder) {
        this.patientServiceClient = builder.baseUrl(patientServiceUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    @Bean(name = "patientServiceClient")
    public WebClient getPatientServiceClient() {
        return patientServiceClient;
    }
}
