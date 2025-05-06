package com.javaweb.hospital.gateway.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    private final WebClient patientServiceClient;
    private final WebClient appointmentServiceClient;

    // Inject values via constructor parameters
    public WebClientConfiguration(WebClient.Builder builder,
            @Value("${internal.service.patient-management}") String patientServiceUrlValue, // Use descriptive parameter
                                                                                            // names
            @Value("${internal.service.appointment-management}") String appointmentServiceUrlValue) {

        // Use the injected constructor parameters to build the WebClients
        this.patientServiceClient = builder.clone()
                .baseUrl(patientServiceUrlValue) // Use the parameter value
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        this.appointmentServiceClient = builder.clone()
                .baseUrl(appointmentServiceUrlValue) // Use the parameter value
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean(name = "patientServiceClient")
    public WebClient getPatientServiceClient() {
        return patientServiceClient;
    }

    @Bean(name = "appointmentServiceClient")
    public WebClient getAppointmentServiceClient() {
        return appointmentServiceClient;
    }
}
