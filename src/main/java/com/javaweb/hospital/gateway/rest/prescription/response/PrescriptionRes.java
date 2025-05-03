package com.javaweb.hospital.gateway.rest.prescription.response;


import com.javaweb.hospital.gateway.rest.medication.response.MedicationRes;
import com.javaweb.hospital.gateway.rest.visit.response.VisitRes;

import java.io.Serializable;

public record PrescriptionRes(
  Long id,
  Integer quantity,
  String instructions,
  Long duration,
  VisitRes patientVisit,
  MedicationRes medication
) implements Serializable {

}
