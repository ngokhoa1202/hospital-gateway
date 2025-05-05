package com.javaweb.hospital.gateway.rest.appointment.request;

import com.javaweb.hospital.gateway.rest.appointment.model.AppointmentType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

public record CreateAppointmentRequest(
                @NotNull(message = "Patient ID cannot be null") UUID patientId,
                @NotNull(message = "Doctor ID cannot be null") UUID doctorId,
                @NotNull(message = "Appointment type cannot be null") AppointmentType appointmentType,
                String description,
                @NotNull(message = "Start date/time cannot be null") @Future(message = "Appointment start time must be in the future") OffsetDateTime fromDate, // Use
                                                                                                                                                                // OffsetDateTime
                @NotNull(message = "End date/time cannot be null") @Future(message = "Appointment end time must be in the future") OffsetDateTime toDate // Use
                                                                                                                                                         // OffsetDateTime
) implements Serializable {
}