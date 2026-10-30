package com.java.patientservice.controller;

import com.java.patientservice.dto.PatientRequestDTO;
import com.java.patientservice.dto.PatientResponseDTO;
import com.java.patientservice.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class PatientController {
    private static final String PATIENT_PATH = "/api/patients";
    private static final String PATIENT_PATH_ID = PATIENT_PATH + "/{id}";
    private final PatientService patientService;

    @GetMapping(PATIENT_PATH)
    public ResponseEntity<List<PatientResponseDTO>> getPatients(
            @RequestParam(required = false) String name) {
        List<PatientResponseDTO> patients = (name == null || name.isBlank())
                ? patientService.getPatients()
                : patientService.getPatientsByName(name);
        return ResponseEntity.ok(patients);
    }

    @GetMapping(PATIENT_PATH_ID)
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable UUID id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping(PATIENT_PATH)
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO created = patientService.createPatient(patientRequestDTO);
        URI location = URI.create(PATIENT_PATH + "/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping(PATIENT_PATH_ID)
    public ResponseEntity<PatientResponseDTO> updatePatientById(
            @PathVariable UUID id,
            @Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        return ResponseEntity.ok(patientService.updatePatientById(id, patientRequestDTO));
    }

    @DeleteMapping(PATIENT_PATH_ID)
    public ResponseEntity<Void> deletePatientById(@PathVariable UUID id) {
        patientService.deletePatientById(id);
        return ResponseEntity.noContent().build();
    }
}