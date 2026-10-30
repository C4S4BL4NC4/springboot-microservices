package com.java.patientservice.dto;

public record PatientResponseDTO(
        String id,
        String name,
        String email,
        String address,
        String dob,
        String registrationDate
) {
}
