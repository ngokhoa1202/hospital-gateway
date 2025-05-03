package com.javaweb.hospital.gateway.rest.medication.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record MedicationCreateReq(
    @NotBlank @Size(min = 1, max = 255) String name,

    String description,

    String dosage
) implements Serializable {
}
