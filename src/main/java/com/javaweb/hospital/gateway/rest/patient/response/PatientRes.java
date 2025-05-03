package com.javaweb.hospital.gateway.rest.patient.response;

import com.javaweb.hospital.gateway.rest.enums.Gender;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record PatientRes(
  UUID id,
  String firstName,
  String lastName,
  Gender gender,
  LocalDateTime dateOfBirth,
  String email,
  String contactNumber
) implements Serializable {

}
