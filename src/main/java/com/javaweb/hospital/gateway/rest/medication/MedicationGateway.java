package com.javaweb.hospital.gateway.rest.medication;

import com.javaweb.hospital.gateway.rest.medication.request.MedicationCreateReq;
import com.javaweb.hospital.gateway.rest.medication.request.MedicationUpdateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "medications",
    produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
    consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class MedicationGateway {

    private final WebClient patientServiceClient;

    @PostMapping
    public Mono<ResponseEntity<String>> createMedication(@RequestBody MedicationCreateReq req) {
        return this.patientServiceClient.post()
            .uri("/medications")
            .bodyValue(req)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<String>> updateMedication(@PathVariable("id") Integer id, @RequestBody MedicationUpdateReq req) {
        return this.patientServiceClient.put()
            .uri("/medications/" + id)
            .bodyValue(req)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

    @DeleteMapping("{id}")
    public Mono<ResponseEntity<Void>> deleteMedication(@PathVariable("id") Integer id) {
        return this.patientServiceClient.delete()
            .uri("/medications/" + id)
            .exchangeToMono((res) -> res.toEntity(Void.class));
    }
}
