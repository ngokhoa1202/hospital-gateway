package com.javaweb.hospital.gateway.rest.appointment.request;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.UUID;

public record CreateShiftRequest(
                @NotNull(message = "Staff ID cannot be null") UUID staffId,
                @NotNull(message = "Shift name cannot be null") String shiftName,
                @NotNull(message = "Start time cannot be null") LocalTime startTime,
                @NotNull(message = "End time cannot be null") LocalTime endTime) implements Serializable {
}