package com.java.patientservice.service;

import com.java.patientservice.dto.PatientRequestDTO;
import com.java.patientservice.dto.PatientResponseDTO;
import com.java.patientservice.exception.EmailAlreadyExistsException;
import com.java.patientservice.exception.PatientNotFoundException;
import com.java.patientservice.mapper.PatientMapper;
import com.java.patientservice.model.Patient;
import com.java.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public List<PatientResponseDTO> getPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::patientToPatientResponseDTO)
                .toList();
    }

    @Override
    public PatientResponseDTO getPatientById(UUID id) {
        return patientMapper.patientToPatientResponseDTO(findPatientOrThrow(id));
    }

    @Override
    public List<PatientResponseDTO> getPatientsByName(String name) {
        return patientRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(patientMapper::patientToPatientResponseDTO)
                .toList();
    }

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmailIgnoreCase(patientRequestDTO.email())) {
            throw new EmailAlreadyExistsException(patientRequestDTO.email());
        }

        Patient patient = patientMapper.patientRequestDTOToPatient(patientRequestDTO);
        patient.setRegistrationDate(LocalDate.now());

        return patientMapper.patientToPatientResponseDTO(patientRepository.save(patient));
    }

    @Override
    public PatientResponseDTO updatePatientById(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = findPatientOrThrow(id);

        if (patientRepository.existsByEmailIgnoreCaseAndIdNot(patientRequestDTO.email(), id)) {
            throw new EmailAlreadyExistsException(patientRequestDTO.email());
        }

        patientMapper.updatePatientFromDTO(patientRequestDTO, patient);

        return patientMapper.patientToPatientResponseDTO(patientRepository.save(patient));
    }

    @Override
    public void deletePatientById(UUID id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient not found with id " + id);
        }
        patientRepository.deleteById(id);
    }

    // Loads the entity or throws 404
    private Patient findPatientOrThrow(UUID id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id.toString()));
    }
}