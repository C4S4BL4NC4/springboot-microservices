package com.java.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PatientRequestDTO(
        @NotBlank @Size(max = 100, message = "Name can't exceed 100 characters!") String name,
        @Email(message = "Email should be valid!") @NotBlank(message = "Email mustn't be empty!") String email,
        @NotBlank(message = "Address is required!") String address,
        @NotBlank(message = "Date of birth is required!") String dob,
        @NotBlank(message = "Register date is required") String registrationDate
) {
}
