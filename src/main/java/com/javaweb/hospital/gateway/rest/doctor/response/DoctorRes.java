package com.javaweb.hospital.gateway.rest.doctor.response;

import java.io.Serializable;
import java.util.UUID;

public record DoctorRes(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String specialization,
    String contactNumber
) implements Serializable {
}
