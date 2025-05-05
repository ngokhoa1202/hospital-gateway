package com.javaweb.hospital.gateway.rest.appointment.request;

import com.javaweb.hospital.gateway.rest.appointment.model.AppointmentStatus;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

public record UpdateAppointmentStatusRequest(
                @NotNull(message = "Doctor ID cannot be null") UUID doctorId,
                @NotNull(message = "Appointment ID cannot be null") UUID appointmentId,
                @NotNull(message = "New status cannot be null") AppointmentStatus newStatus) implements Serializable {
}