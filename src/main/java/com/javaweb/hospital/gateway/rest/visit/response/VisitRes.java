package com.javaweb.hospital.gateway.rest.visit.response;


import com.javaweb.hospital.gateway.rest.doctor.response.DoctorRes;
import com.javaweb.hospital.gateway.rest.patient.response.PatientRes;

import java.io.Serializable;
import java.time.LocalDateTime;

public record VisitRes(
    Long id,
    LocalDateTime visitTime,
    String symptoms,
    String treatment,
    String diagnosis,
    String notes,
    PatientRes patient,
    DoctorRes doctor
) implements Serializable {
}
