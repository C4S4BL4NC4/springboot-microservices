package com.java.patientservice.service;

import com.java.patientservice.dto.PatientRequestDTO;
import com.java.patientservice.dto.PatientResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    List<PatientResponseDTO> getPatients();

    PatientResponseDTO getPatientById(UUID id);

    List<PatientResponseDTO> getPatientsByName(String name);

    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);

    PatientResponseDTO updatePatientById(UUID id, PatientRequestDTO patientRequestDTO);

    void deletePatientById(UUID id);
}
