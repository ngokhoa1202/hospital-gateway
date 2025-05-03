package com.javaweb.hospital.gateway.rest.doctor.request;

import java.io.Serializable;

public record DoctorUpdateReq(
    String firstName,
    String lastName,
    String email,
    String specialization,
    String contactNumber
) implements Serializable {
}
