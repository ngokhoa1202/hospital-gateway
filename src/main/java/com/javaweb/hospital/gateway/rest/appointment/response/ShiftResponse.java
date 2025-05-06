package com.javaweb.hospital.gateway.rest.appointment.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ShiftResponse(
                UUID id,
                UUID doctorId,
                LocalDate shiftDate,
                LocalTime startTime,
                LocalTime endTime,
                OffsetDateTime createdAt,
                OffsetDateTime updatedAt) implements Serializable {
}