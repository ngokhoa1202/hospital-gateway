package com.javaweb.hospital.gateway.rest.patient.request;

import com.javaweb.hospital.gateway.rest.enums.Gender;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDateTime;

public record PatientCreateReq(
    @NotBlank @Size(max = 255) String firstName,
    @NotBlank @Size(max = 255) String lastName,
    @NotNull Gender gender,
    @Past LocalDateTime dateOfBirth,
    @Email String email,
    @Size(max = 20) String contactNumber,
    @Size(max = 255) String address
) implements Serializable {

}
