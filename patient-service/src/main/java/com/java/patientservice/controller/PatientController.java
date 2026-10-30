package com.java.patientservice.controller;

import com.java.patientservice.dto.PatientResponseDTO;
import com.java.patientservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PatientController {
    private static final String PATIENT_PATH = "/api/patients";
    private static final String PATIENT_PATH_ID = PATIENT_PATH + "/id";
    private final PatientService patientService;

    @GetMapping(PATIENT_PATH)
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }

}
