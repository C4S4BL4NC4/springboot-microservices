package com.java.patientservice.mapper;

import com.java.patientservice.dto.PatientRequestDTO;
import com.java.patientservice.dto.PatientResponseDTO;
import com.java.patientservice.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {

    @Mapping(source = "birthDate", target = "dob")
    PatientResponseDTO patientToPatientResponseDTO(Patient patient);

    @Mapping(source = "dob", target = "birthDate")
    Patient patientRequestDTOToPatient(PatientRequestDTO patientRequestDTO);

    @Mapping(source = "dob", target = "birthDate")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registrationDate", ignore = true)
    void updatePatientFromDTO(PatientRequestDTO dto, @MappingTarget Patient patient);
}
