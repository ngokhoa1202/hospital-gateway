package com.javaweb.hospital.gateway.rest.appointment;

import com.javaweb.hospital.gateway.rest.appointment.request.CreateAppointmentRequest;
import com.javaweb.hospital.gateway.rest.appointment.request.CreateShiftRequest;
import com.javaweb.hospital.gateway.rest.appointment.request.UpdateAppointmentRequest;
import com.javaweb.hospital.gateway.rest.appointment.request.UpdateAppointmentStatusRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(path = "appointments", produces = { MediaType.APPLICATION_JSON_VALUE })
public class AppointmentGateway {

        private final WebClient appointmentServiceClient;

        public AppointmentGateway(@Qualifier("appointmentServiceClient") WebClient appointmentServiceClient) {
                this.appointmentServiceClient = appointmentServiceClient;
        }

        @GetMapping("/doctor/{id}")
        public Mono<ResponseEntity<String>> findAllAppointmentsByDoctorId(
                        @PathVariable UUID id,
                        @RequestParam(defaultValue = "1") @Min(1) int page,
                        @RequestParam(defaultValue = "10") @Min(1) int limit) {
                return this.appointmentServiceClient.get()
                                .uri(uriBuilder -> uriBuilder
                                                .path("/appointments/doctor/{id}")
                                                .queryParam("page", page)
                                                .queryParam("limit", limit)
                                                .build(id))
                                .exchangeToMono(res -> res.toEntity(String.class));
        }

        @GetMapping("/patient/phone/{phoneNumber}")
        public Mono<ResponseEntity<String>> getAllAppointmentByPhonenumber(@PathVariable String phoneNumber) {
                return this.appointmentServiceClient.get()
                                .uri("/appointments/patient/phone/{phoneNumber}", phoneNumber)
                                .exchangeToMono(res -> res.toEntity(String.class));
        }

        @PostMapping
        public Mono<ResponseEntity<String>> createAppointment(@Valid @RequestBody CreateAppointmentRequest request) {
                return this.appointmentServiceClient.post()
                                .uri("/appointments")
                                .bodyValue(request)
                                .exchangeToMono(res -> res.toEntity(String.class));
        }

        @PutMapping("/{id}")
        public Mono<ResponseEntity<String>> updateAppointment(
                        @PathVariable UUID id,
                        @Valid @RequestBody UpdateAppointmentRequest request) {
                return this.appointmentServiceClient.put()
                                .uri("/appointments/{id}", id)
                                .bodyValue(request)
                                .exchangeToMono(res -> res.toEntity(String.class));
        }

        @DeleteMapping("/{id}")
        public Mono<ResponseEntity<Void>> deleteAppointment(@PathVariable UUID id) {
                return this.appointmentServiceClient.delete()
                                .uri("/appointments/{id}", id)
                                .exchangeToMono(res -> res.toEntity(Void.class));
        }

        @PatchMapping("/status")
        public Mono<ResponseEntity<String>> updateAppointmentStatus(
                        @Valid @RequestBody UpdateAppointmentStatusRequest request) {
                return this.appointmentServiceClient.patch()
                                .uri("/appointments/status")
                                .bodyValue(request)
                                .exchangeToMono(res -> res.toEntity(String.class));
        }

        @PostMapping("/shifts")
        public Mono<ResponseEntity<String>> createShift(@Valid @RequestBody CreateShiftRequest request) {
                return this.appointmentServiceClient.post()
                                .uri("/shifts")
                                .bodyValue(request)
                                .exchangeToMono(res -> res.toEntity(String.class));
        }
}