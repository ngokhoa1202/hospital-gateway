package com.javaweb.hospital.gateway.rest.prescription.request;

import java.io.Serializable;

public record PrescriptionCreateReq(
  Integer quantity,
  String instructions,
  Long duration,
  Integer medicationId
) implements Serializable {
}
