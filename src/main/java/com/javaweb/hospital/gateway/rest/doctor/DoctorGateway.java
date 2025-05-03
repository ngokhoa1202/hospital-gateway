package com.javaweb.hospital.gateway.rest.doctor;

import com.javaweb.hospital.gateway.rest.doctor.request.DoctorCreateReq;
import com.javaweb.hospital.gateway.rest.doctor.request.DoctorUpdateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(path = "doctors",
    consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
    produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
public class DoctorGateway {

    private final WebClient patientServiceClient;


    @PostMapping
    public Mono<ResponseEntity<String>> createDoctor(@RequestBody DoctorCreateReq req) {
        return this.patientServiceClient.post()
            .uri("/doctors")
            .bodyValue(req)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

    @PutMapping(path = "{id}")
    public Mono<ResponseEntity<String>> updateDoctor(@PathVariable("id") UUID id, @RequestBody DoctorUpdateReq req) {
        return this.patientServiceClient.put()
            .uri("/doctors/" + id)
            .bodyValue(req)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

    @DeleteMapping(path = "{id}")
    public Mono<ResponseEntity<Void>> deleteDoctor(@PathVariable("id") UUID id) {
        return this.patientServiceClient.delete()
            .uri("/doctors/" + id)
            .exchangeToMono((res) -> res.toEntity(Void.class));
    }

    @GetMapping(path = "page/{page}/limit/{limit}")
    public Mono<ResponseEntity<String>> getDoctors(@PathVariable("page") Integer page,
                                                   @PathVariable("limit") Integer limit) {
        return this.patientServiceClient.get()
            .uri("/doctors/page/" + page + "/limit/" + limit)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<String>> getDoctor(@PathVariable("id") UUID id) {
        return this.patientServiceClient.get()
            .uri("/doctors/" + id)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

}
