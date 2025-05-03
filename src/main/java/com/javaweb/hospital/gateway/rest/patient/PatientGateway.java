package com.javaweb.hospital.gateway.rest.patient;

import com.javaweb.hospital.gateway.rest.patient.request.PatientCreateReq;
import com.javaweb.hospital.gateway.rest.patient.request.PatientUpdateReq;
import com.javaweb.hospital.gateway.rest.prescription.request.PrescriptionCreateReq;
import com.javaweb.hospital.gateway.rest.prescription.request.PrescriptionUpdateReq;
import com.javaweb.hospital.gateway.rest.visit.request.VisitCreateReq;
import com.javaweb.hospital.gateway.rest.visit.request.VisitUpdateReq;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(path = "patients",
  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
  consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class PatientGateway {


    private final WebClient patientServiceClient;

    public PatientGateway(WebClient.Builder builder, @Value("${internal.service.patient-management}") String patientServiceUrl) {
        this.patientServiceClient = builder.baseUrl(patientServiceUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    @PostMapping
    public Mono<ResponseEntity<String>> createPatient(@RequestBody PatientCreateReq req) {
        return this.patientServiceClient.post()
            .uri("/patients")
            .bodyValue(req)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

    @PutMapping(path = "{id}")
    public Mono<ResponseEntity<String>> updatePatient(@PathVariable("id") UUID id, @RequestBody PatientUpdateReq req) {
        return this.patientServiceClient.put()
            .uri("/patients/" + id)
            .bodyValue(req)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

    @DeleteMapping(path = "{id}")
    public Mono<ResponseEntity<Void>> deletePatient(@PathVariable("id") UUID id) {
        return this.patientServiceClient.delete()
            .uri("/patients/" + id)
            .exchangeToMono((res) -> res.toEntity(Void.class));
    }

    @PostMapping(path = "{id}/visits")
    public Mono<ResponseEntity<String>> createVisit(@PathVariable("id") UUID id, @RequestBody VisitCreateReq req) {
        return this.patientServiceClient.post()
            .uri("/patients/" + id + "/visits")
            .bodyValue(req)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

    @PutMapping(path = "{patient-id}/visits/{visit-id}")
    public Mono<ResponseEntity<String>> updateVisit(@PathVariable("patient-id") UUID patientId,
                                                @PathVariable("visit-id") Long visitId,
                                                @RequestBody VisitUpdateReq req) {
        return this.patientServiceClient.put()
            .uri("/patients/" + patientId + "/visits/" + visitId)
            .bodyValue(req)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

    @DeleteMapping(path = "{patient-id}/visits/{visit-id}")
    public Mono<ResponseEntity<Void>> deleteVisit(@PathVariable("patient-id") UUID patientId,
                                            @PathVariable("visit-id") Long visitId) {
        return this.patientServiceClient.delete()
            .uri("/patients/" + patientId + "/visits/" + visitId)
            .exchangeToMono((res) -> res.toEntity(Void.class));
    }

    @PostMapping("{patient-id}/visits/{visit-id}/prescriptions")
    public Mono<ResponseEntity<String>> createPrescription(@PathVariable("patient-id") UUID patientId,
                                                              @PathVariable("visit-id") Long visitId,
                                                              @RequestBody PrescriptionCreateReq req) {
        return this.patientServiceClient.post()
            .uri("/patients/" + patientId + "/visits/" + visitId + "/prescriptions")
            .bodyValue(req)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }


    @PutMapping("{patient-id}/visits/{visit-id}/prescriptions/{prescription-id}")
    public Mono<ResponseEntity<String>> updatePrescription(@PathVariable("patient-id") UUID patientId,
                                                          @PathVariable("visit-id") Long visitId,
                                                          @PathVariable("prescription-id") Long prescriptionId,
                                                          @RequestBody PrescriptionUpdateReq req) {
        return this.patientServiceClient.put()
            .uri("/patients/" + patientId + "/visits/" + visitId + "/prescriptions/" + prescriptionId)
            .bodyValue(req)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

    @DeleteMapping("{patient-id}/visits/{visit-id}/prescriptions/{prescription-id}")
    public Mono<ResponseEntity<Void>> deletePrescription(@PathVariable("patient-id") UUID patientId,
                                                   @PathVariable("visit-id") Long visitId,
                                                   @PathVariable("prescription-id") Long prescriptionId) {
        return this.patientServiceClient.delete()
            .uri("/patients/" + patientId + "/visits/" + visitId + "/prescriptions/" + prescriptionId)
            .exchangeToMono((res) -> res.toEntity(Void.class));
    }

    @GetMapping("page/{page}/limit/{limit}")
    public Mono<ResponseEntity<String>> getPatients(@PathVariable("page") Integer page,
                                                        @PathVariable("limit") Integer limit) {
        return this.patientServiceClient.get()
            .uri("/page/" + page + "/limit/" + limit)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

    @GetMapping(path = "{id}/visits/page/{page}/limit/{limit}")
    public Mono<ResponseEntity<String>> getVisits(@PathVariable("id") UUID id,
                                                    @PathVariable("page") Integer page,
                                                    @PathVariable("limit") Integer limit) {
        return this.patientServiceClient.get()
            .uri(id + "/visits/page/" + page + "/limits/" + limit)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

    @GetMapping(path = "{patient-id}/visits/{visit-id}/page/{page}/limit/{limit}")
    public Mono<ResponseEntity<String>> getPrescriptions(@PathVariable("patient-id") UUID patientId,
                                                                  @PathVariable("visit-id") Long visitId,
                                                                  @PathVariable("page") Integer page,
                                                                  @PathVariable("limit") Integer limit) {
        return this.patientServiceClient.get()
            .uri(patientId + "/visits/" + visitId + "/page/" + page + "/limit/" + limit)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

    @GetMapping(path = "{patient-id}/page/{page}/limit/{limit}")
    public Mono<ResponseEntity<String>> getPrescriptions(@PathVariable("patient-id") UUID patientId,
                                                   @PathVariable("page") Integer page,
                                                   @PathVariable("limit") Integer limit) {
        return this.patientServiceClient.get()
            .uri(patientId + "/page/" + page + "/limit/" + limit)
            .exchangeToMono((res) -> res.toEntity(String.class));
    }

}
